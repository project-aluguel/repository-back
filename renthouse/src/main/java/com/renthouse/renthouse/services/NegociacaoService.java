package com.renthouse.renthouse.services;

import com.renthouse.renthouse.models.NegociacaoModel;
import com.renthouse.renthouse.repositories.NegociacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class NegociacaoService {
    @Autowired
    NegociacaoRepository negociacaoRepository;

    public NegociacaoModel save(NegociacaoModel negociacaoModel) {
        return negociacaoRepository.save(negociacaoModel);
    }

}
