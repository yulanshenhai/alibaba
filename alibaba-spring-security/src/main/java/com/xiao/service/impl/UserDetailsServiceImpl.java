package com.xiao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.entity.Member;
import com.xiao.entity.Permission;
import com.xiao.entity.Role;
import com.xiao.mapper.MemberMapper;
import com.xiao.mapper.PermissionMapper;
import com.xiao.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiao
 * <p>负责重写登录逻辑的类 必须实现 `UserDetailsService` 接口并重写 `loadUserByUsername()` 方法 </p>
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = this.selectByUsername(username);
        Integer memberId = member.getMemberId();
        List<Role> roles = this.selectRolesByMemberId(memberId);
        List<Permission> permissions = this.selectPermissionsByMemberId(memberId);

        // 对密码进行BCRYPT加密
        String password = passwordEncoder.encode(member.getPassword());

        // 准备authorities权限集合: 包括该会员的全部权限和角色
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role ->
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleTitle())));
        permissions.forEach(
                permission -> authorities.add(new SimpleGrantedAuthority(permission.getPermissionTitle())));
        System.out.println("authorities: " + authorities);

        // org.springframework.security.core.userdetails.User
        return new User(username, password, authorities);
    }

    /**
     * 通过账号获取会员信息（包括角色和权限）
     *
     * @param username 账号
     * @return 会员信息
     */
    private Member selectByUsername(String username) {
        // 按账号从数据库中查询出指定会员信息
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Member member = memberMapper.selectOne(queryWrapper);
        if (null == member) {
            throw new UsernameNotFoundException("账号不存在");
        }
        return member;
    }

    /**
     * 通过账号获取会员的角色列表
     *
     * @param memberId 会员表主键
     * @return 会员的角色列表
     */
    private List<Role> selectRolesByMemberId(Integer memberId) {
        return roleMapper.selectRolesByMemberId(memberId);
    }

    /**
     * 通过账号获取会员的权限列表
     *
     * @param memberId 会员表主键
     * @return 会员的权限列表
     */
    private List<Permission> selectPermissionsByMemberId(Integer memberId) {
        return permissionMapper.selectPermissionsByMemberId(memberId);
    }

}