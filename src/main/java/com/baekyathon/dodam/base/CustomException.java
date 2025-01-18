package com.baekyathon.dodam.base;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorInterface errorInterface;

    public CustomException(ErrorInterface errorInterface) {
        super(errorInterface.getMessage());
        this.errorInterface = errorInterface;
    }
}
