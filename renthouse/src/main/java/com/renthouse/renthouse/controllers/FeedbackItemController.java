package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.models.FeedbackItemModel;
import com.renthouse.renthouse.models.UsuarioModel;
import com.renthouse.renthouse.services.FeedbackItemService;
import com.renthouse.renthouse.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("renthouse/feedback-item")
public class FeedbackItemController {

    @Autowired
    FeedbackItemService feedbackItemService;



}
