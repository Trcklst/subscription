package com.trcklst.getsubscription.ws.getsubscription.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No subscription found with this user id")
public class NoSubscriptionForUserIdException extends RuntimeException {
}
