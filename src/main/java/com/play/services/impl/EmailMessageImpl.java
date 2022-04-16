package com.play.services.impl;

import com.play.configurations.FreeMarkerConfig;
import com.play.models.EmailMessage;
import com.play.repository.EmailMessageRepository;
import com.play.services.interfaces.EmailMessageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailMessageImpl implements EmailMessageService {
    private final EmailMessageRepository emailMessageRepository;

    private final JavaMailSender mailSender;
    private final Configuration freeMarkerConfig;

    @Override
    public EmailMessage getEmailMessageByTitle(String title) {
        return emailMessageRepository.findEmailMessageByTitle(title)
                .orElseGet(()-> emailMessageRepository.save(new EmailMessage("Welcome message", "Welcome to test mails", "the test mail body")));
    }


    public boolean sendTemplateMail(Map<String, Object> templateData){

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());
//            mimeMessageHelper.addAttachment("attachment.ext", new ClassPathResource("template.html"));

            Template mailTemplate = freeMarkerConfig.getTemplate("template.ftl");

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(mailTemplate, templateData);

            mimeMessageHelper.setTo("recipient");
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setFrom("sender");


            mailSender.send(mimeMessage);


        }catch (Exception exception){
            //...
            return false;
        }
        return true;
    }
}
