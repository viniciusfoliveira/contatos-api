package br.com.ibm.contatos.repository;

import br.com.ibm.contatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatosRespository extends JpaRepository<Contato, Long> {
}
