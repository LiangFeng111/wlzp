package com.xxx.wlzp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result("200", "", null);
    }

    public static Result success(Object data) {
        return new Result("200", "", data);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result error() {
        return new Result("500", "系统错误", null);
    }
}
