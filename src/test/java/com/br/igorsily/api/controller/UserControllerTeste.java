package com.br.igorsily.api.controller;

import com.br.igorsily.api.controller.exception.FieldMessage;
import com.br.igorsily.api.controller.exception.ValidationError;
import com.br.igorsily.api.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yml")
public class UserControllerTeste {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void validationRequestUserIsEmptyThenStatus400() throws Exception {

        mvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(new User("", "")))).
                andExpect(status().isBadRequest());
    }

    @Test
    public void validationRequestUserThenStatus400() throws Exception {

        mvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(new User()))).
                andExpect(status().isBadRequest());
    }

    @Test
    public void validationRequestUserMessageThenStatus400() throws Exception {

        MvcResult mvcResult = mvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(new User("", "igorsily2")))).
                andExpect(status().isBadRequest()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        var validationError = objectMapper.readValue(content, ValidationError.class);

        assertNotNull(validationError.getErrors());

    }


    @Test
    public void validationRequestUserThenStatus200() throws Exception {
        mvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(new User("Igor Sily", "igorsily2@gmail.com")))).
                andExpect(status().isOk());
    }


}
