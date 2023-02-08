import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootTest
public class MyTest {

    @Test
    public void passwordTest(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123");
        System.out.println(password);
        boolean matches = passwordEncoder.matches("123", "$2a$10$tuF5tf9QfiIl66bs.0rauuf9rDFqRnRVlH2iowuOZRuISGpV1xUvi");
        System.out.println(matches);
        System.out.println(new Date());
    }
}
