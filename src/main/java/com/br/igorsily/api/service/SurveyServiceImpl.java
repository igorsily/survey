package com.br.igorsily.api.service;

import com.br.igorsily.api.model.Survey;
import com.br.igorsily.api.repository.SurveyRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public Survey createSurvey(Survey survey) {
        return this.surveyRepository.save(survey);
    }

    @Override
    public List<Survey> getAllSurvey() {
        return this.surveyRepository.findAll();
    }

    @Override
    public Survey findSurveyById(UUID id) {

        var surveyOptional = this.surveyRepository.findById(id);

        if (surveyOptional.isEmpty()) throw new ObjectNotFoundException("Enquete não encontrada", "Survey");

        return surveyOptional.get();
    }
}
