package com.whx.rabbitmq;

import com.whx.utils.CaptchaUtil;
import com.whx.utils.RedisCache;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MqReceive {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedisCache redisCache;

    @RabbitListener(queues = "sendEmail")
    public void sendEmail(String email){
        //创建消息对象
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //设置发送人
        mailMessage.setFrom("1978938887@qq.com");
        //设置接收人
        mailMessage.setTo(email);
        //设置邮件标题
        mailMessage.setSubject("图书馆管理系统注册");
        //获取验证码
        String code = CaptchaUtil.generatedCode(4);
        //将验证码保存到redis
        redisCache.setCacheObject(email+":register",code,60, TimeUnit.SECONDS);
        //设置内容
        mailMessage.setText("注册的验证码为："+code+"\n如您未获取该验证码，请忽略该信息");
        //发送邮件
        javaMailSender.send(mailMessage);
    }
}
