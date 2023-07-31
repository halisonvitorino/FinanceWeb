package com.halisonvitorino.FinanceWeb.repository;

import com.halisonvitorino.FinanceWeb.models.Transacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransacaoRep extends CrudRepository<Transacao, String> {

    Transacao findById(long id);
    Iterable<Transacao> findAll();
    @Query("SELECT t.descricao, SUM(t.valor) FROM Transacao t GROUP BY t.descricao")
    List<Object[]> somarValoresPorDescricao();
    
}
