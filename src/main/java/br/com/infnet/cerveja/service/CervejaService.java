package br.com.infnet.cerveja.service;

import br.com.infnet.cerveja.model.Cerveja;

import java.util.List;

public interface CervejaService {
    List<Cerveja> findAll();
    Cerveja findById(Long id);
}
