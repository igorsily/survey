package com.br.igorsily.api.service;

import com.br.igorsily.api.model.Survey;
import com.br.igorsily.api.model.SurveyUser;
import com.br.igorsily.api.model.User;

public interface EmailService {

    void sendEmail(SurveyUser surveyUser);
}
