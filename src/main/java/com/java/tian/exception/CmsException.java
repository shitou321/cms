package com.java.tian.exception;

/*
 * 自定义异常
 *Create By LiXiaoTian on 2019/11/11 17:56
 */
public class CmsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public CmsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CmsException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CmsException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CmsException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
