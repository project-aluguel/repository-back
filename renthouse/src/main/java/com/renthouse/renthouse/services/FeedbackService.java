package com.renthouse.renthouse.services;

import com.renthouse.renthouse.models.FeedbackModel;
import com.renthouse.renthouse.models.ItemModel;
import com.renthouse.renthouse.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public void delete(FeedbackModel feedbackModel) {
        feedbackRepository.delete(feedbackModel);
    }

    public Double getMediaAvaliacoesUsuario(UUID id) {
        return feedbackRepository.getMediaAvaliacoesUsuario(id);
    }

    public Double getMediaAvaliacoesItem(UUID id) {
        return feedbackRepository.getMediaAvaliacoesItem(id);
    }

    public int getTotalAvaliacoesUsuario(UUID id) {
        return feedbackRepository.getTotalAvaliacoesUsuario(id);
    }


    public int getTotalAvaliacoesItem(UUID id) {
        return feedbackRepository.getTotalAvaliacoesItem(id);
    }

}
