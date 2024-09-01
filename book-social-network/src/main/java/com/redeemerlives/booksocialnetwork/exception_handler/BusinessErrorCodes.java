package com.redeemerlives.booksocialnetwork.exception_handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum BusinessErrorCodes {

    NO_CODE(0, NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Current password is incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "The new password does not match"),
    ACCOUNT_DISABLED(302, FORBIDDEN, "User account is disabled"),
    ACCOUNT_LOCKED(303, FORBIDDEN, "User account is locked"),
    BAD_CREDENTIALS(304, FORBIDDEN, "Username and or password is incorrect")
    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String description;

}
