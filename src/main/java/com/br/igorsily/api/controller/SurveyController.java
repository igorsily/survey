package com.br.igorsily.api.controller;

import com.br.igorsily.api.model.Survey;
import com.br.igorsily.api.model.User;
import com.br.igorsily.api.service.EmailService;
import com.br.igorsily.api.service.SurveyService;
import com.br.igorsily.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(SurveyController.REQUEST_MAPPING)
public class SurveyController {

    public static final String REQUEST_MAPPING = "surveys";

    private final SurveyService surveyService;


    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public ResponseEntity<List<Survey>> index() {
        var surveyList = this.surveyService.getAllSurvey();

        return new ResponseEntity<List<Survey>>(surveyList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Survey> create(@Valid @RequestBody Survey survey) {

        var surveyCreated = this.surveyService.createSurvey(survey);

        return new ResponseEntity<Survey>(surveyCreated, HttpStatus.CREATED);

    }
}
