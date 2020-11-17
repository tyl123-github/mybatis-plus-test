package com.tyl.user.controller;

import com.tyl.common.response.ApiResponse;
import com.tyl.common.response.Response;
import com.tyl.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestBody;
import com.tyl.user.service.JpaUserService;
import com.tyl.user.entity.JpaUser;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*  接口
* </p>
*
* @author tyl
* @since 2020-11-16
*/
@Api(tags = "")
@RestController
@RequestMapping("/user/jpaUser")
public class JpaUserController {

    @Autowired
    private JpaUserService jpaUserService;

    @PostMapping("add")
    @ApiOperation("添加或根据id修改")
    public Response mod(@RequestBody JpaUser jpaUser ){
        if (jpaUser == null){
            throw new ApiException("获取参数异常");
        }
        return ApiResponse.success(jpaUserService.saveOrUpdate(jpaUser));
    }

    @ApiOperation("根据id查看详情")
    @GetMapping("/findById")
    public Response<JpaUser> findById(@Parameter(description = "主键") @RequestParam(value="id",required = false) Long id) {
        if (id == null ) {
            throw new ApiException("获取参数异常");
        }
        return ApiResponse.success(jpaUserService.getById(id));
    }

}
