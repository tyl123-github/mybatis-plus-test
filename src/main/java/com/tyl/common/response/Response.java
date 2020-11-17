package com.tyl.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 自定义controller返回实体
 */
@Setter
@Getter
@ApiModel(value = "Response", description = "自定义Response返回体")
public class Response<T> implements Serializable {

    @ApiModelProperty(value = "响应code值", name = "code", required = true, example = "200")
    private String code;

    @ApiModelProperty(value = "返回数据", name = "date", example = "string")
    private T date;

    @ApiModelProperty(value = "返回msg", name = "msg", example = "成功")
    private String msg;

    @ApiModelProperty(value = "list返回总条数", name = "count", example = "0")
    private Integer count;

    public Response(String code, T date, String msg, Integer count) {
        this.code = code;
        this.date = date;
        this.msg = msg;
        this.count = count;
    }

    public Response() {
    }
}
