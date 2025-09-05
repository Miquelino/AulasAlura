package br.com.alura.screenmatch.frases.service;

import br.com.alura.screenmatch.frases.controller.FraseDTO;
import br.com.alura.screenmatch.frases.entity.Frases;
import br.com.alura.screenmatch.frases.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FraseService {

    @Autowired
    private FraseRepository repository;

    public FraseDTO obterFraseAleatoria() {
        Frases frase = repository.buscaFraseAleatoria();
        return new FraseDTO(frase.getTitulo(), frase.getFrase(), frase.getPersonagem(), frase.getPoster());
    }
}
