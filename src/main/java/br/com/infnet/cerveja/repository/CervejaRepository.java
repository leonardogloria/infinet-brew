package br.com.infnet.cerveja.repository;

import br.com.infnet.cerveja.model.Cerveja;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CervejaRepository extends MongoRepository<Cerveja,String> {
    Cerveja findCervejaById(Long id);
}
