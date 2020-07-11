package com.trcklst.subscription.ws.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "No subscription found with this user id")
public class InvalidTokenException extends RuntimeException {
}
