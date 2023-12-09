package edu.njnu.nybike.controller;
import edu.njnu.nybike.exception.EntityArgException;
import edu.njnu.nybike.exception.InsertException;
import edu.njnu.nybike.exception.ServiceException;
import edu.njnu.nybike.util.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//全局异常处理器
//@ContrlollerAdvice表示将统一异常处理的逻辑注入到所有控制器方法中
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult<Void> handleException(Throwable t){
        JsonResult<Void> jsonResult = new JsonResult<Void>();
        jsonResult.setMsg("服务器出现位置错误，请稍后重试");
        jsonResult.setState(2000);//默认错误的状态码
        t.printStackTrace();
        return jsonResult;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public JsonResult<Void> handleCustomException(Throwable t){
        JsonResult<Void> jsonResult = new JsonResult<Void>();
        jsonResult.setMsg(t.getMessage());
        jsonResult.setState(2000);//默认错误的状态码
        if(t instanceof InsertException){
            jsonResult.setState(2001);//表示插入失败
        }
        if(t instanceof EntityArgException){
            jsonResult.setState(2002);
        }
        t.printStackTrace();
        return jsonResult;
    }
}
