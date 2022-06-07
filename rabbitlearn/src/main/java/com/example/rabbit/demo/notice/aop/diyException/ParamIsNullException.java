package com.example.rabbit.demo.notice.aop.diyException;

public class ParamIsNullException  extends RuntimeException {
    private final String parameterName;
    private final String parameterType;
    public ParamIsNullException(String parameterName, String parameterType) {
        super("");
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        System.out.println(
                "++++++++++++++parameterType"+parameterName+"+++++++++++++++parameterType"+parameterType
        );
    }
    @Override
    public String getMessage() {
        return "Required " + this.parameterType + " parameter \'" + this.parameterName + "\' must be not null !";
    }

}
