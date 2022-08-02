package br.com.infnet.cerveja.service.impl;

import br.com.infnet.cerveja.model.Cerveja;
import br.com.infnet.cerveja.repository.CervejaRepository;
import br.com.infnet.cerveja.service.CervejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CervejaServiceImpl implements CervejaService {
    @Autowired
    CervejaRepository cervejaRepository;
    @Override
    public List<Cerveja> findAll() {
        return this.cervejaRepository.findAll();
    }

    @Override
    public Cerveja findById(Long id) {
        return cervejaRepository.findCervejaById(id);
    }
}

