package com.renthouse.renthouse.services;

import com.renthouse.renthouse.models.CarteiraModel;
import com.renthouse.renthouse.repositories.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarteiraService {

    @Autowired
    CarteiraRepository repository;

    public void save(CarteiraModel carteiraModel) {
        repository.save(carteiraModel);
    }
}
