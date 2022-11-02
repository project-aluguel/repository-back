package com.renthouse.renthouse.services;

import com.renthouse.renthouse.models.EnderecoModel;
import com.renthouse.renthouse.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Transactional
    public EnderecoModel save(EnderecoModel enderecoModel) {
        return enderecoRepository.save(enderecoModel);
    }

    public List<EnderecoModel> findEnderecoByUsuario(UUID idUsuario) {
        return enderecoRepository.findEnderecoModelByUsuarioModelId(idUsuario);
    }

    public EnderecoModel buscaEnderecoPorId(UUID idEndereco) {
        return enderecoRepository.findEnderecoModelById(idEndereco);
    }

    public Boolean existePorId(UUID idEndereco){
        return enderecoRepository.existsById(idEndereco);
    }

    public void deletaEndereco(UUID idEndereco) {
        enderecoRepository.deleteById(idEndereco);
    }
}
