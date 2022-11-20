package com.renthouse.renthouse.services;

import com.renthouse.renthouse.dtos.respostas.FeedbacksItem;
import com.renthouse.renthouse.dtos.respostas.FeedbacksUsuario;
import com.renthouse.renthouse.models.FeedbackModel;
import com.renthouse.renthouse.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Transactional
    public FeedbackModel save(FeedbackModel feedbackModel) {
        return feedbackRepository.save(feedbackModel);
    }

    public Optional<FeedbackModel> findById(UUID uuid) {
        return feedbackRepository.findById(uuid);
    }

    public List<FeedbackModel> findAll() { return feedbackRepository.findAll();}

    @Transactional
    public void delete(FeedbackModel feedbackModel) {
        feedbackRepository.delete(feedbackModel);
    }

    public FeedbacksUsuario buscarFeedbacksUsuario(UUID idUsuario) {
        return feedbackRepository.getFeedbacksUsuario(idUsuario);
    }

    public FeedbacksItem buscarFeedbacksItem(UUID idItem) {
        return feedbackRepository.getFeedbacksItem(idItem);
    }
}
