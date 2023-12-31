package com.halisonvitorino.FinanceWeb.repository;

import com.halisonvitorino.FinanceWeb.models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRep extends CrudRepository<Usuario, String> {

    Usuario findById(long id);
    UserDetails findByLogin(String login);
}
