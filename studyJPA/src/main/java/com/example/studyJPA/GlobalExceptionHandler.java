package com.example.studyJPA;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * グローバル例外処理
 * @author ビズコネクト株式会社
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * システムエラー
     *
     * @param request HttpServletRequest
     * @param e       Exception
     * @return HTTPResponseForm
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        return e.getMessage();
    }

    /**
     * ビジネスロジックでの例外処理
     *
     * @param request HttpServletRequest
     * @param e       BizException
     * @return HTTPResponseForm
     */
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBizSystemException(HttpServletRequest request, RuntimeException e) {
        return e.getMessage();
    }
}