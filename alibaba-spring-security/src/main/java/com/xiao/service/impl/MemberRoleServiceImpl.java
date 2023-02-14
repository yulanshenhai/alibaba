package com.xiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.entity.MemberRole;
import com.xiao.mapper.MemberRoleMapper;
import com.xiao.service.MemberRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员-角色中间表 服务实现类
 * </p>
 *
 * @author xiao
 * @since 2023-02-13
 */
@Service
public class MemberRoleServiceImpl extends ServiceImpl<MemberRoleMapper, MemberRole> implements MemberRoleService {

}
