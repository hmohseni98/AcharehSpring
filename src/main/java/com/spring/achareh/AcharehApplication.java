package com.spring.achareh;


import com.spring.achareh.model.User;
import com.spring.achareh.repository.UserRepository;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


@SpringBootApplication
@EnableWebSecurity
public class AcharehApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.yahoo.com");
        mailSender.setPort(465);

        mailSender.setUsername("mohseni.7798@yahoo.com");
        mailSender.setPassword("rjaaibpjeqjswtaw");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtps.ssl.checkserveridentity", "true");
//        props.put("mail.smtps.ssl.trust", "*");
        props.put("mail.debug", "true");
        return mailSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(AcharehApplication.class, args);
    }

}


@Component
class Start implements ApplicationRunner {

    private final UserService userService;

    public Start(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws MessagingException, UnsupportedEncodingException {
        User user = userService.findById(1).get();
        userService.generateOneTimePassword(user);
    }
}

