package ${package.Controller};

import com.tyl.common.response.ApiResponse;
import com.tyl.common.response.Response;
import com.tyl.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
</#if>
import org.springframework.web.bind.annotation.RequestBody;
import ${package.Service}.${entity}Service;
import ${package.Entity}.${entity};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
* <p>
* ${table.comment!} 接口
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if swagger2>
@Api(tags = "${table.comment!}")
</#if>
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Autowired
    private ${entity}Service ${table.entityPath}Service;

    @PostMapping("add")
    <#if swagger2>
    @ApiOperation("添加或根据id修改")
    <#--@ApiImplicitParams({
    <#list table.fields as field>
        <#if field_index==0>
        @ApiImplicitParam(name = "${field.propertyName}",value = "${field.comment}",dataTypeClass = ${field.propertyType}.class,paramType = "query")
        <#else>
        ,@ApiImplicitParam(name = "${field.propertyName}",value = "${field.comment}",dataTypeClass = ${field.propertyType}.class,paramType = "query")
        </#if>
    </#list>
    })-->
    </#if>
    public Response mod(@RequestBody ${entity} ${table.entityPath} ){
        if (${table.entityPath} == null){
            throw new ApiException("获取参数异常");
        }
        return ApiResponse.success(${table.entityPath}Service.saveOrUpdate(${table.entityPath}));
    }

    @ApiOperation("根据id查看详情")
    @GetMapping("/findById")
    public Response<${entity}> findById(<#list table.fields as field><#if field.keyIdentityFlag>@Parameter(description = "主键") @RequestParam(value="${field.propertyName}",required = false) ${field.propertyType} ${field.propertyName}</#if></#list>) {
        if (<#list table.fields as field><#if field.keyIdentityFlag>${field.propertyName} == null</#if></#list> ) {
            throw new ApiException("获取参数异常");
        }
        return ApiResponse.success(jpaUserService.getById(id));
    }

}
</#if>