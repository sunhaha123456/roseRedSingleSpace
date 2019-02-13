package others;

import com.rose.conf.Application;
import com.rose.data.to.request.UserLoginRequest;
import com.rose.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Application.class) // 老版本配置
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class SpringBootTestDemo {

    @Inject
    private LoginService loginUserService;

    @Test
    public void loginTest() {
        UserLoginRequest user = new UserLoginRequest();
        user.setUname("admin");
        user.setUpwd("admin1");
        try {
            loginUserService.verify(user);
        } catch (Exception e) {
        }
    }
}