package mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.SpringSecurityApp;
import com.xiao.entity.Member;
import com.xiao.mapper.MemberMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xiao
 */
@SpringBootTest(classes = SpringSecurityApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void loadUserByUsername() {
        String username = "zhaosi";
        // 按账号从数据库中查询出指定会员信息
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Member member = memberMapper.selectOne(queryWrapper);
        if (null == member) {
            throw new UsernameNotFoundException("账号不存在");
        }

        System.out.println(member);
        // 对密码进行BCRYPT加密
        String password = passwordEncoder.encode(member.getPassword());
        System.out.println(password);
    }

    @Test
    public void selectByUsername() {
        String username = "zhaosi";
        // 按账号从数据库中查询出指定会员信息
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Member member = memberMapper.selectOne(queryWrapper);
        if (null == member) {
            throw new UsernameNotFoundException("账号不存在");
        }

        System.out.println(member);
        // 对密码进行BCRYPT加密
        String password = passwordEncoder.encode(member.getPassword());
        System.out.println(password);
    }


}
