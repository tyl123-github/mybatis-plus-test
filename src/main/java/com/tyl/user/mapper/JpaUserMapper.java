package com.tyl.user.mapper;

import com.tyl.user.entity.JpaUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* <p>
    *  Mapper 接口
    * </p>
*
* @author tyl
* @since 2020-11-16
*/
@Mapper
public interface JpaUserMapper extends BaseMapper<JpaUser> {

}
