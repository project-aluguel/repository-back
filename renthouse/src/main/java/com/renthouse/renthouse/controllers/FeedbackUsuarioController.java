package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.models.FeedbackItemModel;
import com.renthouse.renthouse.models.FeedbackUsuarioModel;
import com.renthouse.renthouse.services.FeedbackUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class FeedbackUsuarioController {

    @Autowired
    FeedbackUsuarioService feedbackUsuarioService;


}
