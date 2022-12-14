package com.szc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szc.entity.ManageUser;
import com.szc.mapper.ManageUserMapper;
import com.szc.service.ManageUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台管理用户 服务实现类
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Service
public class ManageUserServiceImpl extends ServiceImpl<ManageUserMapper, ManageUser> implements ManageUserService {

}
