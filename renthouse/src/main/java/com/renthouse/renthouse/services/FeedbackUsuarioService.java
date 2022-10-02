package com.renthouse.renthouse.services;

import com.renthouse.renthouse.models.FeedbackUsuarioModel;
import com.renthouse.renthouse.models.UsuarioModel;
import com.renthouse.renthouse.repositories.FeedbackUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FeedbackUsuarioService {

    @Autowired
    FeedbackUsuarioRepository feedbackUsuarioRepository;

}
