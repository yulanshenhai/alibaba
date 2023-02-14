package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.entity.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author xiao
 * @since 2023-02-13
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    /**ROLE表全查SQL片段*/
    String PERMISSION_LIST_ALL = "SELECT p.permission_id, p.permission_title, " +
            "p.permission_info, p.create_time, p.last_modify " +
            "FROM alibaba.role_permission rp ";

    /**
     * 按用户表主键查询该用户的全部权限
     *
     * @param memberId 用户表主键
     * @return 该用户的全部权限
     */
    @Select(PERMISSION_LIST_ALL + "join permission p ON rp.permission_id = p.permission_id " +
            "WHERE rp.role_id IN ( " +
            "    SELECT r.role_id FROM alibaba.role r " +
            "    WHERE r.role_id IN ( " +
            "        SELECT role_id FROM alibaba.member_role mr " +
            "        WHERE mr.member_id = #{param1}" +
            "    )" +
            ") GROUP BY p.permission_id")
    List<Permission> selectPermissionsByMemberId(Integer memberId);
}
