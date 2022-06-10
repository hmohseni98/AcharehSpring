package com.spring.achareh.service.impl;

import com.spring.achareh.exceptionHandler.customException.OldPasswordIsIncorrectException;
import com.spring.achareh.model.User;
import com.spring.achareh.model.enumration.Role;
import com.spring.achareh.util.UserGridSearch;
import com.spring.achareh.repository.UserRepository;
import com.spring.achareh.service.UserService;
import com.spring.achareh.service.base.BaseServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;


@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer, UserRepository>
        implements UserService {
    private final UserGridSearch userGridSearch;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;


    public UserServiceImpl(UserRepository repository, UserGridSearch userGridSearch, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
        super(repository);
        this.userGridSearch = userGridSearch;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Override
    public User login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public void changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = repository.findById(userId).get();
        if (!user.getPassword().equals(oldPassword))
            throw new OldPasswordIsIncorrectException();
        user.setPassword(newPassword);
        repository.save(user);
    }

    @Override
    public List<User> gridSearch(Integer id, String email, String firstName, String lastName, Role role) {
        Specification<User> specification = userGridSearch.gridSearch(id, email, firstName, lastName, role);
        return repository.findAll(specification);
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return repository.existsUserByEmail(email);
    }

    public void generateOneTimePassword(User user) throws MessagingException, UnsupportedEncodingException {
        String OTP = RandomString.make(8);
        String encodedOTP = passwordEncoder.encode(OTP);

        user.setOneTimePassword(encodedOTP);
        user.setOtpRequestedTime(new Date());

        repository.save(user);

        sendOTPEmail(user, OTP);
    }

    public void sendOTPEmail(User user, String OTP) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("mohseni.7798@yahoo.com", "achareh Support");
        helper.setTo(user.getEmail());

        String subject = "Here's your One Time Password (OTP) - Expire in 3 minutes!";

        String content = "<p>Hello " + user.getFirstName() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + OTP + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 3 minutes.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    public void clearOTP(User user) {
        user.setOneTimePassword(null);
        user.setOtpRequestedTime(null);
        repository.save(user);
    }

}
