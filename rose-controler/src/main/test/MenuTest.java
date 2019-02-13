import com.rose.common.util.JsonUtil;
import com.rose.conf.Application;
import com.rose.data.entity.TbMenu;
import com.rose.repository.MenuRepositoryCustom;
import com.rose.repository.SysUserRepositoryCustom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Application.class) // 老版本配置
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MenuTest {

    @Inject
    private MenuRepositoryCustom menuRepositoryCustom;
    @Inject
    private SysUserRepositoryCustom sysUserRepositoryCustom;

    @Test
    public void getMenuByRoleGroupIdTest_1() throws Exception {
        System.out.println(JsonUtil.objectToJson(sysUserRepositoryCustom.list(null, 1, 10)));
    }

    @Test
    public void getMenuByRoleGroupIdTest_2() throws Exception {
        Long roleId = 1L;
        List<TbMenu> list_1 = menuRepositoryCustom.queryMenuListByRoleIdAndLevel(roleId, 1);
        if (list_1 != null && list_1.size() > 0) {
            List<TbMenu> list_2 = menuRepositoryCustom.queryMenuListByRoleIdAndLevel(roleId, 2);
            if (list_2 != null && list_2.size() > 0) {
                for (TbMenu m1 : list_1) {
                    m1.setChildren(new ArrayList<>());
                    for (TbMenu m2 : list_2) {
                        if (m2.getParentId().equals(m1.getId())) {
                            m1.getChildren().add(m2);
                        }
                    }
                }
            }
        }
        System.out.println(JsonUtil.objectToJson(list_1));
    }
}