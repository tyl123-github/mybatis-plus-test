package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* <p>
    * ${table.comment!} 服务类
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${entity}Service : ${superServiceClass}<${entity}>
<#else>
public interface ${entity}Service extends ${superServiceClass}<${entity}> {

}
</#if>