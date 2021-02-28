package com.br.igorsily.api.service;

import com.br.igorsily.api.model.SurveyUserCreateDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface SurveyUserService {

    void createSurveyUser(SurveyUserCreateDTO surveyUserCreateDTO);

    void setAnswers(Integer nota, UUID userId, UUID surveyUserID);
}
