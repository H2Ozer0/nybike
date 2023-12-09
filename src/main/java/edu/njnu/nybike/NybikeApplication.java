package edu.njnu.nybike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//添加@ServletComponentScan扫描应用的监听器所在的包
@ServletComponentScan("edu.njnu.nybike.listener")
/*//添加包扫描注解，扫描控制器类所在的包；但是此时springboot的注解会自动配置此项
@ComponentScan("edu.njnu.nybike.controller")*/
@MapperScan("edu.njnu.nybike.mapper")
public class NybikeApplication {

    public static void main(String[] args) {

        SpringApplication.run(NybikeApplication.class, args);
    }

}
