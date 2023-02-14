package mapper;

import com.xiao.SpringSecurityApp;
import com.xiao.mapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xiao
 */
@SpringBootTest(classes = SpringSecurityApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testSelectRolesByMemberId() {
        roleMapper.selectRolesByMemberId(1)
                .forEach(role -> System.out.println("1号用户角色: " + role));
        roleMapper.selectRolesByMemberId(2)
                .forEach(role -> System.out.println("2号用户角色: " + role));
        roleMapper.selectRolesByMemberId(3)
                .forEach(role -> System.out.println("3号用户角色: " + role));
        roleMapper.selectRolesByMemberId(4)
                .forEach(role -> System.out.println("4号用户角色: " + role));
    }

}
