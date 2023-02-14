package com.xiao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.entity.Member;
import com.xiao.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = this.selectByUsername(username);
        Integer memberId = member.getMemberId();

        // 按账号从数据库中查询出指定会员信息
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
//        Member member = memberMapper.selectOne(queryWrapper);
        if (null == member) {
            throw new UsernameNotFoundException("账号不存在");
        }

        // 对密码进行BCRYPT加密
        String password = passwordEncoder.encode(member.getPassword());

        // 获取member的全部权限，并放入authorities集合中

        // 准备authorities权限集合: 包括该会员的全部权限和角色
        List<GrantedAuthority> authorities = new ArrayList<>();

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
        // 按账号从数据库中查询指定会员信息
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Member member = memberMapper.selectOne(queryWrapper);
        if (null == member) {
            throw new UsernameNotFoundException("账号不存在");
        }
        return member;
    }
}