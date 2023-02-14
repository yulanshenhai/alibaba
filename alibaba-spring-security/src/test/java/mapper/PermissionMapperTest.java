package mapper;

import com.xiao.SpringSecurityApp;
import com.xiao.mapper.PermissionMapper;
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
public class PermissionMapperTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void testSelectPermissionsByMemberId() {
        permissionMapper.selectPermissionsByMemberId(1)
                .forEach(permission -> System.out.println("1号用户权限: " + permission));
        permissionMapper.selectPermissionsByMemberId(2)
                .forEach(permission -> System.out.println("2号用户权限: " + permission));
        permissionMapper.selectPermissionsByMemberId(3)
                .forEach(permission -> System.out.println("3号用户权限: " + permission));
        permissionMapper.selectPermissionsByMemberId(4)
                .forEach(permission -> System.out.println("4号用户权限: " + permission));
    }

}
