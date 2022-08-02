package br.com.infnet.cerveja.controller;

import br.com.infnet.cerveja.model.Cerveja;
import br.com.infnet.cerveja.service.CervejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class CervejaController {
    @Autowired
    CervejaService cervejaService;

    @Secured("ROLE_ADM")
    @GetMapping
    public ResponseEntity getAll(){
        List<Cerveja> all = cervejaService.findAll();
        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Cerveja byId = cervejaService.findById(id);
        return ResponseEntity.ok(byId);
    }
}
