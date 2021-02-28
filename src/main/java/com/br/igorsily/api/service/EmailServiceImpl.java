package com.br.igorsily.api.service;

import com.br.igorsily.api.model.Survey;
import com.br.igorsily.api.model.SurveyUser;
import com.br.igorsily.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(SurveyUser surveyUser) {

        final Context context = new Context(new Locale("pt"));
        context.setVariable("name", surveyUser.getUser().getName());
        context.setVariable("title", surveyUser.getSurvey().getTitle());
        context.setVariable("description", surveyUser.getSurvey().getDescription());
        context.setVariable("link", "http://localhost:8080/surveyusers/answers");
        context.setVariable("userId", surveyUser.getUser().getId());
        context.setVariable("surveyUserId", surveyUser.getId());

        final var body = this.templateEngine.process("email", context);

        MimeMessagePreparator messagePreparator = mimeMessage -> {

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false);
            messageHelper.setFrom("NPS <noreplay@nps.com.br>");
            messageHelper.setTo(surveyUser.getUser().getEmail());
            messageHelper.setSubject(surveyUser.getSurvey().getTitle());
            messageHelper.setText(body, true);

        };

        this.javaMailSender.send(messagePreparator);
    }

}
