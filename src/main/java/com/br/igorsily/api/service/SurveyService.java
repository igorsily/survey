package com.br.igorsily.api.service;


import com.br.igorsily.api.model.Survey;

import java.util.List;
import java.util.UUID;

public interface SurveyService {

    Survey createSurvey(Survey survey);

    List<Survey> getAllSurvey();

    Survey findSurveyById(UUID id);
}
