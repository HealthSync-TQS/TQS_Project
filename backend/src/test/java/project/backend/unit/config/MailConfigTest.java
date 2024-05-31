package project.backend.unit.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MailConfigTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testMailSenderConfiguration() {
        assertThat(javaMailSender).isNotNull();
        JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) javaMailSender;
        assertThat(mailSenderImpl.getHost()).isEqualTo("sandbox.smtp.mailtrap.io");
        assertThat(mailSenderImpl.getPort()).isEqualTo(2525);
        assertThat(mailSenderImpl.getUsername()).isEqualTo("e4839449cf4c33");
    }
}
