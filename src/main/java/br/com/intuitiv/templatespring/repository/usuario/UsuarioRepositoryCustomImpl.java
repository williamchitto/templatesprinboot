package br.com.intuitiv.templatespring.repository.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {

  private final UsuarioRepositoryBasic usuarioRepositoryBasic;

  @PersistenceContext private EntityManager em;

  public UsuarioRepositoryCustomImpl(final UsuarioRepositoryBasic usuarioRepositoryBasic) {
    this.usuarioRepositoryBasic = usuarioRepositoryBasic;
  }

 
}
