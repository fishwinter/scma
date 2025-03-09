package com.company.scma.common.vo;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    /**
     * 响应代码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T Data;
    
    public static Result success(){
        Result result = success(null);
        return result;
    }
    
    public static Result success(Object data){
        Result result = getResult(ResultEnum.SUCCESS, data);
        return result;
    }
    
    public static Result getResult(ResultEnum resultEnum,Object data){
        Result result = new Result(resultEnum.getCode(), resultEnum.getMsg(), data);
        return result;
    }

    public static Result getResult(ResultEnum resultEnum){
        Result result = new Result(resultEnum.getCode(), resultEnum.getMsg(), null);
        return result;
    }
    
    public static Boolean isSuccess(Result result){
        Integer code = result.getCode();
        if(ObjectUtil.isNotEmpty(code) && code == ResultEnum.SUCCESS.getCode()){
            return true;
        }
        return false;
    }
}
