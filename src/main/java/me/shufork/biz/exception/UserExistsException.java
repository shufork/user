package me.shufork.biz.exception;

import me.shufork.common.exceptions.BaseException;

public class UserExistsException extends BaseException {

    public UserExistsException(String who) {
        super("user already exists", who);
    }

    @Override
    public String errorCode() {
        return "user_exists";
    }

}
