package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.entity.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author xiao
 * @since 2023-02-13
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**ROLE表全查SQL片段*/
    String ROLE_LIST_ALL = "SELECT r.role_id, r.role_title, " +
            "r.role_info, r.create_time, r.last_modify " +
            "FROM alibaba.role r ";

    /**
     * 按用户表主键查询该用户的全部角色
     *
     * @param memberId 用户表主键
     * @return 该用户的全部角色
     */
    @Select(ROLE_LIST_ALL +
            "WHERE r.role_id IN ( " +
            "    SELECT role_id FROM alibaba.member_role mr " +
            "    WHERE mr.member_id = #{param1} " +
            ")")
    List<Role> selectRolesByMemberId(Integer memberId);
}
