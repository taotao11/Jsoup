package com.jsoup.demo.uitls;


import org.springframework.http.HttpStatus;

/**
 * 统一返回结果
 */
public class Result {
    public Object data;
    public String message;
    public Integer code;

    public Result(Object data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    /**
     * 成功返回结果
     * @param data
     * @param message
     * @return
     */
    public static Result success(Object data,String message){
        Result result = new Result(data,message, HttpStatus.OK.value());
        return result;
    }

    /**
     * 错误返回结果
     * @param message
     * @return
     */
    public static Result error(String message){
        Result result = new Result(null,message,HttpStatus.INTERNAL_SERVER_ERROR.value());
        return result;
    }
}
