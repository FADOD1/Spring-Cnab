package spring.boot.desafio.cnab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.desafio.cnab.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {


}