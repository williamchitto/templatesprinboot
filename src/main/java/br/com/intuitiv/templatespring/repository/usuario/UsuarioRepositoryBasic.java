package br.com.intuitiv.templatespring.repository.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.intuitiv.templatespring.model.dominio.DominioSimNao;
import br.com.intuitiv.templatespring.model.dominio.DominioSituacao;
import br.com.intuitiv.templatespring.model.security.Usuario;

@RepositoryRestResource(collectionResourceRel = "retorno", path = "usuario")
public interface UsuarioRepositoryBasic extends PagingAndSortingRepository<Usuario, Long> {

  @Query(
      "select entity from Usuario entity inner join fetch entity.perfil as perfil inner join fetch entity.municipio as municipio where entity.login = :login and entity.situacao = :situacao")
  Optional<Usuario> findByLoginAndSituacao(
      @Param("login") String login, @Param("situacao") DominioSituacao situacao);

  List<Usuario> findBySituacao(DominioSituacao situacao);

  Optional<Usuario> findByCpf(String cpf);

  @Query(
      "select entity from Usuario entity inner join fetch entity.perfil as perfil inner join fetch entity.municipio as municipio where entity.login like %:login%")
  Optional<Usuario> findByLogin(@Param("login") String login);

  List<Usuario> findByReceberNotificacaoEquals(DominioSimNao receberNotificacao);
}
