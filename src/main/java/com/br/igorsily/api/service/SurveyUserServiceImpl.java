package com.br.igorsily.api.service;

import com.br.igorsily.api.model.SurveyUser;
import com.br.igorsily.api.model.SurveyUserCreateDTO;
import com.br.igorsily.api.repository.SurveyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SurveyUserServiceImpl implements SurveyUserService {

    private final SurveyUserRepository surveyUserRepository;

    private final UserService userService;

    private final SurveyService surveyService;

    private final EmailService emailService;

    @Autowired
    public SurveyUserServiceImpl(UserService userService, SurveyService surveyService, SurveyUserRepository surveyUserRepository, EmailService emailService) {
        this.surveyService = surveyService;
        this.userService = userService;
        this.surveyUserRepository = surveyUserRepository;
        this.emailService = emailService;
    }

    @Override
    public void createSurveyUser(SurveyUserCreateDTO surveyUserCreateDTO) {

        var user = this.userService.findUserByEmail(surveyUserCreateDTO.getEmail());

        var survey = this.surveyService.findSurveyById(surveyUserCreateDTO.getSurveyId());

        var surveyUser = new SurveyUser(user, survey);

        var surveyUserCreated = this.surveyUserRepository.save(surveyUser);

        this.emailService.sendEmail(surveyUserCreated);

    }

    @Override
    public void setAnswers(Integer nota, UUID userId, UUID surveyUserID) {


        var surveyUser = this.surveyUserRepository.getSurveyUserByIdAndUserIdAndValueIsNull(surveyUserID, userId);

        surveyUser.setValue(nota);

        this.surveyUserRepository.save(surveyUser);

    }
}
