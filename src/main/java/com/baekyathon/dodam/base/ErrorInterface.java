package com.baekyathon.dodam.base;

import org.springframework.http.HttpStatus;

public interface ErrorInterface {
    String getMessage();
    HttpStatus getStatus();
}

