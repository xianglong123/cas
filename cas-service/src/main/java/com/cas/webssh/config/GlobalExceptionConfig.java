package com.cas.components.beanAware.webssh.config;

import com.cas.components.beanAware.webssh.constant.BaseEnums;
import com.cas.components.beanAware.webssh.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * 全局异常处理
 * 异常增强，以JSON的形式返回给客服端
 * 异常增强类型：NullPointerException,RunTimeException,ClassCastException,
 * 　　　　　　　　 NoSuchMethodException,IOException,IndexOutOfBoundsException
 * 　　　　　　　　 以及自定义异常等，如下：
 * SpringMVC自定义异常对应的status code
 * Exception                       HTTP Status Code
 * ConversionNotSupportedException         500 (Internal Server Error)
 * HttpMessageNotWritableException         500 (Internal Server Error)
 * HttpMediaTypeNotSupportedException      415 (Unsupported Media Type)
 * HttpMediaTypeNotAcceptableException     406 (Not Acceptable)
 * HttpRequestMethodNotSupportedException  405 (Method Not Allowed)
 * NoSuchRequestHandlingMethodException    404 (Not Found)
 * TypeMismatchException                   400 (Bad Request)
 * HttpMessageNotReadableException         400 (Bad Request)
 * MissingServletRequestParameterException 400 (Bad Request)
 *
 * @author luo jin jiang
 * @version 1.0
 * @date 2018-08-21
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionConfig {
    //private final static Logger log = LoggerFactory.getLogger(GlobalExceptionConfig.class);

    /**
     * 处理 NoHandlerFoundException 异常. <br/>
     * 需配置 [spring.mvc.throw-exception-if-no-handler-found=true]
     * 需配置 [spring.resources.add-mappings=false]
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleNotFoundException(NoHandlerFoundException e) {
        log.info(e.getHttpMethod(), e);
        return Result.fail(BaseEnums.NOT_FOUND.code(), BaseEnums.NOT_FOUND.desc());
    }


    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return Result.fail(BaseEnums.RUNTIME_ERROR.code(), ex.getMessage());
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerExceptionHandler(NullPointerException ex) {
        log.error(ex.getMessage(), ex);
        return Result.fail(BaseEnums.NULL_POINTER_ERROR.code(), BaseEnums.NULL_POINTER_ERROR.desc());
    }

    /**
     *  类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    public Result classCastExceptionHandler(ClassCastException ex) {
        log.error(ex.getMessage(), ex);
        return Result.fail(BaseEnums.CLASS_CAST_ERROR.code(), ex.getMessage());
    }

    /**
     *  IO异常
     */
    @ExceptionHandler(IOException.class)
    public Result iOExceptionHandler(IOException ex) {
        log.error(ex.getMessage(),ex);
        return Result.fail(BaseEnums.IO_ERROR.code(), ex.getMessage());
    }

    /**
     * 未知方法异常
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public Result noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        log.error(ex.getMessage(),ex);
        return Result.fail(BaseEnums.NO_SUCH_METHOD.code(), ex.getMessage());
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Result indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        log.error(ex.getMessage(),ex);
        return Result.fail(BaseEnums.INDEX_OUT_OF_BOUNDS_ERROR.code(), ex.getMessage());
    }

    /**
     *  400错误
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Result requestNotReadable(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(),ex);
        return Result.fail(BaseEnums.BAD_REQUEST.code(), ex.getMessage());
    }

    /**
     *  400错误
     */
    @ExceptionHandler({TypeMismatchException.class})
    public Result requestTypeMismatch(TypeMismatchException ex) {
        log.error(ex.getMessage(),ex);
        return Result.fail(BaseEnums.BAD_REQUEST.code(), ex.getMessage());
    }

//    /**
//     * 400错误
//     */
//    @ExceptionHandler({MissingServletRequestParameterException.class})
//    public Result requestMissingServletRequest(MissingServletRequestParameterException ex) {
//        log.error(ex.getMessage(),ex);
//        return Result.fail(BaseEnums.BAD_REQUEST.code(), ex.getMessage());
//    }

    /**
     *   500错误
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public Result server500(RuntimeException ex) {
        log.error(ex.getMessage(),ex);
        return Result.fail(BaseEnums.ERROR.code(), ex.getMessage());
    }

    /**
     * 处理 远程调用 异常
     */
    @ExceptionHandler(RemoteException.class)
    public Result handleRemoteException(RemoteException e) {
        log.error(e.getMessage(), e);
        return Result.fail(BaseEnums.REMOTE_ERROR.code(), e.getMessage());
    }

    /**
     * 处理 远程调用 异常
     */
    @ExceptionHandler(SQLException.class)
    public Result handleSQLException(SQLException e) {
        log.error(e.getMessage(), e);
        return Result.fail(BaseEnums.SQL_ERROR.code(), BaseEnums.SQL_ERROR.desc());
    }


    /**
     * 处理 Exception 异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        Result result = Result.fail(BaseEnums.ERROR.code(), BaseEnums.ERROR.desc());
        log.error(e.getMessage(), e);
        return result;
    }
}
