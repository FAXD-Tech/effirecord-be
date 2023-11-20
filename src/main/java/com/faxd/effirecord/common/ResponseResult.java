package com.faxd.effirecord.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.faxd.effirecord.constant.State;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    private Integer state;
    private String message;
    private T data;


    private ResponseResult(){}

    public static ResponseResult<Void> ok( ){
        return ok(null);
    }

    public static <T> ResponseResult<T> ok(T data){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setState(State.OK.getValue());
        responseResult.setData(data);
        return responseResult;
    }

    public static ResponseResult<Void> fail(State state, String message){
        ResponseResult<Void> responseResult = new ResponseResult<>();
        responseResult.setState(state.getValue());
        responseResult.setMessage(message);
        return responseResult;
    }

}
