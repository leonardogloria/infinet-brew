package br.com.infnet.cerveja.repository;

import br.com.infnet.cerveja.model.Perfil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends CrudRepository<Perfil, Integer> {
}
