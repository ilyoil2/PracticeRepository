package practice.Practice.domain.user.service;

import practice.Practice.domain.user.presentation.dto.request.SendEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(SendEmailRequest request) throws Exception {

        //User user = userRepository.findByEmail(request.getAddress())
          //      .orElseThrow(() -> new Exception("입력한 이메일에 해당하는 회원이 없습니다."));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getAddress());
        message.setSubject(request.getTitle());
        message.setText(request.getMessege());


        try {
            mailSender.send(message);
            System.out.println("이메일이 전송되었습니다.");
        } catch (MailException e) {
            System.err.println("이메일 전송 중 오류가 발생했습니다: " + e.getMessage());
            throw new Exception("이메일 전송 중 오류가 발생했습니다.");
        }

    }
}