package com.lgm.project.common;

public class Result<T> {
    /*响应码*/
    private String code;
    /*响应消息*/
    private String msg;
    /*数据单元*/
    private T data;


    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public Result(){}
    public Result(T data){this.data = data;}

    /**
     * 成功时候的调用
     */
    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }


    /**
     * 失败时候的调用
     */
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}

