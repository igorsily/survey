package com.br.igorsily.api.repository;

import com.br.igorsily.api.model.SurveyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SurveyUserRepository extends JpaRepository<SurveyUser, UUID> {

    SurveyUser getSurveyUserByIdAndUserIdAndValueIsNull(UUID surveyUserId, UUID userId);
}
