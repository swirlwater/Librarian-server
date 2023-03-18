import com.whx.LibrarianApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
@SpringBootTest(classes = LibrarianApplication.class)
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void passwordTest(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("1234");
        System.out.println(password);
        boolean matches = passwordEncoder.matches("123", "$2a$10$tuF5tf9QfiIl66bs.0rauuf9rDFqRnRVlH2iowuOZRuISGpV1xUvi");
        System.out.println(matches);
        System.out.println(new Date());
    }

    @Test
    public void sendSimpleMail(){
        //创建消息对象
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //设置发送人
        mailMessage.setFrom("1978938887@qq.com");
        //设置接收人
        mailMessage.setTo("2565631562@qq.com");
        //设置邮件标题
        mailMessage.setSubject("小伙子");
        //设置内容
        mailMessage.setText("起床了");
        //发送邮件
        javaMailSender.send(mailMessage);
    }
}
