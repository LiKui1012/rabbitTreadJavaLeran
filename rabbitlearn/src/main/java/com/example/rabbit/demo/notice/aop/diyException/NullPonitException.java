package com.example.rabbit.demo.notice.aop.diyException;

public class NullPonitException extends RuntimeException {
    private final String xiaoxi;
    public NullPonitException(String xiaoxi) {
        super("");
        this.xiaoxi = xiaoxi;
        System.out.println(
                "++++++++++++++xiaoxi"+xiaoxi+"+++++++++++++++nullpoint"
        );
    }
    @Override
    public String getMessage() {
        return "Required " + this.xiaoxi + " xiaoxi \'" + this.xiaoxi + "\' must be not null !";
    }

}
