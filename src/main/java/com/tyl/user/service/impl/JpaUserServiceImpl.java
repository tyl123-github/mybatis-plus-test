package com.tyl.user.service.impl;

import com.tyl.user.entity.JpaUser;
import com.tyl.user.mapper.JpaUserMapper;
import com.tyl.user.service.JpaUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* <p>
    *  服务实现类
    * </p>
*
* @author tyl
* @since 2020-11-16
*/
@Service
public class JpaUserServiceImpl extends ServiceImpl<JpaUserMapper, JpaUser> implements JpaUserService {

}
