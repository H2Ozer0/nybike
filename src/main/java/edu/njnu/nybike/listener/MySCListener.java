package edu.njnu.nybike.listener;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;
import java.util.concurrent.*;

@WebListener
@Slf4j
public class MySCListener implements ServletContextListener{
    //定时执行服务器
    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();


    @Override
    public void contextInitialized(ServletContextEvent sce){
        log.debug("监听器生效");
        //获取application作用域
        ServletContext sc = sce.getServletContext();
        Runnable runnable=new MyRunnable(sc);
        //启动一个周期性任务：参数一 任务runnable，参数二 首次延迟时间，参数三 间隔时间，参数四 时间单位
        ScheduledFuture<?> scheduledFuture = service.scheduleAtFixedRate(runnable,0,10, TimeUnit.SECONDS);
    }
    //官方站点信息url
    public static final String INFO_URL="https://gbfs.citibikenyc.com/gbfs/en/station_information.json";
    //官方站点状态url
    public static final String STATUS_URL="https://gbfs.citibikenyc.com/gbfs/en/station_status.json";
    //要通过application作用域共享的infoData & statusData 数据的key
    public static final String INFO_DATA="infoData";
    public static final String STATUS_DATA="statusData";
    /*
    * 定义一个内部类--线程用于执行：每一次访问任务的执行逻辑
    * @Slf4j为日志服务
    * @Data为构建通用方法服务
    * @NoArgsConstructor自动生成无参构造
    * */
    @Data
    @NoArgsConstructor
//定义一个线程用于执行
    class MyRunnable implements Runnable{
        //声明全局共享的application作用域对象
        private ServletContext sc;
        //声明全局共享的RestTemplate对象
        private RestTemplate restTemplate;
        public MyRunnable(ServletContext sc){
            this.sc = sc;
            //需要指明超时时间
            SimpleClientHttpRequestFactory requestFactory=new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(3000);
            requestFactory.setReadTimeout(3000);
            this.restTemplate = new RestTemplate();
        }
        @Override
        public void run(){
            log.debug("周期性任务"+ LocalDateTime.now());
            //请求站点状态信息
            String statusData = null;
            //请求官方站点状态数据，并将放回的数据封装成String类型
            //请求出现超时异常（未获取到数据），则自动重试3次
            for(int i=0;i<3;i++){
                try{

                    statusData = restTemplate.getForObject(STATUS_URL,String.class);
                    log.debug("获取到了站点状态数据:statusData:{}",statusData.length());
                    //获取到的数据存到全局共享的应用作用域当中
                    sc.setAttribute(STATUS_DATA,statusData);
                }catch(Exception e){
                    log.error("出现异常，获取数据失败:e：{}",e.getMessage());
                }
                //获取到数据，无需循环
                if(statusData!=null){
                    break;
                }

            }

            //请求站点数据
            String infoData = null;
            for(int i=0;i<3;i++) {
                try {
                    infoData = restTemplate.getForObject(INFO_URL, String.class);
                    log.debug("获取到了站点信息数据:infoData:{}", infoData.length());
                    sc.setAttribute(INFO_DATA,infoData);
                } catch (Exception e) {
                    log.error("出现异常，获取数据失败:e：{}", e.getMessage());
                }
                if(infoData!=null){
                    break;
                }
            }

        }
    }
}
