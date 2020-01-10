package com.example.demo.controller;

import com.example.demo.entity.ErrorInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
class GlobalExceptionHandler {

    public static final String DEFAUL_REEOR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("url", request.getRequestURI());
        modelAndView.setViewName(DEFAUL_REEOR_VIEW);
        return  modelAndView;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> stringErrorInfo(HttpServletRequest request, MyException e) throws Exception{
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(request.getRequestURL().toString());
        return r;


    }

}
