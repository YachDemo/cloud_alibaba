package com.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 固定返回值
 *
 * @author YanCh
 * Create by 2019-12-30 15:31
 **/

// 比如对这个类进行注解，然后通过注解的方式获取类
public class Result<T> implements Serializable {

    /**
     * 错误码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体的内容
     */
    private T data;

    /**
     * 成功时候的调用
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 根据返回的状态对象， 构建返回结果
     *
     * @param resultEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> build(ResultEnum resultEnum) {
        return new Result<>(resultEnum);
    }

    /**
     * 根据 code， 和  msg 构建返回结果
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> build(String code, String msg) {
        return new Result<>(code, msg);
    }

    /**
     * 根据 code， 和  msg, 和 data 构建返回结果
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> build(String code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

    public static <T> Result<T> build(ResultEnum resultEnum, T data) {
        return build(resultEnum.getCode(), resultEnum.getMessage(), data);
    }


    /**
     * 失败的调用
     *
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String codeMsg) {
        return new Result<>(codeMsg);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResultEnum.ERROR);
    }

    /**
     * 失败的调用 将返回结果传入
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(T data) {
        return new Result<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage(), data);
    }

    private Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMessage();
        this.data = data;
    }

    private Result(String msg) {
        this.code = ResultEnum.ERROR.getCode();
        this.data = null;
        this.msg = msg;
    }

    private Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

    private Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    /**
     * 判断是否成功
     * @return
     */
    @JsonIgnore
    public boolean isSuccess(){
        return this.getCode().equals(ResultEnum.SUCCESS.getCode());
    }

    /**
     * 服务器当前时间
     * @return
     */
    //@JsonIgnore
    /*public Long getTimestamp(){
        return System.currentTimeMillis();
    }*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
