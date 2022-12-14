package com.szc.exception;


import com.szc.result.Result;
import com.szc.result.ResultEnum;
import com.szc.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder M
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
//        String[] abd = new String[]{"class.*", "Class.*", "*.class.*", "*.Class.*"};
//        binder.setDisallowedFields(abd);
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model M
     */
    @ModelAttribute
    public void modelAttribute(Model model) {

    }

    /**
     * 判断错误是否是 *自定义* 的已知错误，
     * 不是则由未知错误代替，同时记录在log中
     * <p>
     * 控制返回的状态码 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     */
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResultUtil.error("请求方式错误！😳");
        }

        //校验类异常
        if (e instanceof BindException) {
            BindException exception = (BindException) e;
            List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
            return ResultUtil.error(allErrors.get(0).getDefaultMessage());
        }

        //运行异常
        if (e instanceof PoorException) {
            PoorException exception = (PoorException) e;
            return ResultUtil.error(exception.getCode(), exception.getMessage());
        }

        log.error("【系统异常】: {}", e.getMessage(), e);
        return ResultUtil.error(ResultEnum.ERROR);
    }


}