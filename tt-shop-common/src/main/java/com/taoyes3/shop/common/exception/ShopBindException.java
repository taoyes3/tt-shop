package com.taoyes3.shop.common.exception;

import com.taoyes3.shop.common.enums.Taoyes3HttpStatus;
import org.springframework.http.HttpStatus;

public class ShopBindException extends RuntimeException{

    private static final long serialVersionUID = 1156480117733539772L;

    private Integer httpStatusCode;
    private Object object;

    public ShopBindException(Taoyes3HttpStatus httpStatus) {
        //调用父类的构造函数，默认是隐式调用的
        super(httpStatus.getMsg());
        this.httpStatusCode = httpStatus.value();
    }

    public ShopBindException(Taoyes3HttpStatus httpStatus, String msg) {
        super(msg);
        this.httpStatusCode = httpStatus.value();
    }

    public ShopBindException(String msg) {
        super(msg);
        this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
    }

    public ShopBindException(String msg, Object object) {
        super(msg);
        this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
        this.object = object;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }
}
