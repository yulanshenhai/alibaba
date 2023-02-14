package com.xiao.mapper;

import com.xiao.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author xiao
 * @since 2023-02-13
 */
@Repository
public interface MemberMapper extends BaseMapper<Member> {

}
