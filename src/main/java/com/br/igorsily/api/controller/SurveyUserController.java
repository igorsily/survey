package com.br.igorsily.api.controller;

import com.br.igorsily.api.model.SurveyUserCreateDTO;
import com.br.igorsily.api.service.SurveyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(SurveyUserController.REQUEST_MAPPING)
public class SurveyUserController {

    public static final String REQUEST_MAPPING = "surveyusers";

    private final SurveyUserService surveyUserService;

    @Autowired
    public SurveyUserController(SurveyUserService surveyUserService) {
        this.surveyUserService = surveyUserService;
    }

    @PostMapping
    public void create(@Valid @RequestBody SurveyUserCreateDTO surveyUserCreateDTO) {
        this.surveyUserService.createSurveyUser(surveyUserCreateDTO);
    }

    @GetMapping("/answers/{nota}")
    public void getNota(@PathVariable("nota") Integer nota, @RequestParam("u") UUID userId, @RequestParam("su") UUID surveyUserID) {
        this.surveyUserService.setAnswers(nota, userId, surveyUserID);

    }
}
