package br.com.intuitiv.templatespring.repository.usuario;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "retorno", path = "usuario")
public interface UsuarioRepository extends UsuarioRepositoryBasic, UsuarioRepositoryCustom {}
