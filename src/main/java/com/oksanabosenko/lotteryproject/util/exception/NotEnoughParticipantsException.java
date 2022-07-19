package com.oksanabosenko.lotteryproject.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NotEnoughParticipantsException extends RuntimeException {
    public NotEnoughParticipantsException(String message) {
        super(message);
    }
}
