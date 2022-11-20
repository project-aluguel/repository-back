package com.renthouse.renthouse.services;

import com.renthouse.renthouse.models.NegociacaoModel;
import com.renthouse.renthouse.repositories.NegociacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NegociacaoService {
    @Autowired
    NegociacaoRepository negociacaoRepository;

    public NegociacaoModel save(NegociacaoModel negociacaoModel) {
        return negociacaoRepository.save(negociacaoModel);
    }

    public boolean existsById(UUID idNegociacao) {
        return negociacaoRepository.existsById(idNegociacao);
    }

    public boolean negociacaoEhVeridica(
            UUID idNegociacao,
            UUID idProprietario,
            UUID idAlugador,
            UUID idItem
    ) {
        NegociacaoModel negociacaoVeridica = negociacaoRepository.findById(idNegociacao).get();
        return negociacaoVeridica.getIdProprietario().getId().equals(idProprietario)
                && negociacaoVeridica.getIdItem().getId().equals(idItem)
                && negociacaoVeridica.getIdAlugador().getId().equals(idAlugador);
    }

    public Optional<NegociacaoModel> buscaPorId(UUID idNegociacao) {
        return negociacaoRepository.findById(idNegociacao);
    }

}
