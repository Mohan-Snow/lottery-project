package com.oksanabosenko.lotteryproject.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NoDataRetrieved extends RuntimeException {

    public NoDataRetrieved(String message) {
        super(message);
    }
}
