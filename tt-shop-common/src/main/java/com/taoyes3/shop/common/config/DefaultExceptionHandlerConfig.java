package com.taoyes3.shop.common.config;

import com.taoyes3.shop.common.exception.ShopBindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandlerConfig {

    @ExceptionHandler(ShopBindException.class)
    public ResponseEntity<String> unauthorizedExceptionHandler(ShopBindException e) {
        log.error("ShopBindException message：{}", e.getMessage());
        return ResponseEntity.status(e.getHttpStatusCode()).body(e.getMessage());
    }
}
