package com.renthouse.renthouse.controllers;

import com.renthouse.renthouse.dtos.EnderecoDto;
import com.renthouse.renthouse.models.EnderecoModel;
import com.renthouse.renthouse.services.EnderecoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

}
