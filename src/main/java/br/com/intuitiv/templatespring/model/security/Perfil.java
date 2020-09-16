package br.com.intuitiv.templatespring.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import br.com.intuitiv.templatespring.core.persistence.BaseEntity;
import br.com.intuitiv.templatespring.model.dominio.DominioSituacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Audited
public class Perfil extends BaseEntity {

	private static final long serialVersionUID = -876261514802170891L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotEmpty
	@Column(nullable = false)
	private String nome;

	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DominioSituacao situacao;

	/*
	 * @ElementCollection(targetClass = DominioPermissaoAcesso.class, fetch =
	 * FetchType.EAGER)
	 * 
	 * @Fetch(FetchMode.SUBSELECT)
	 * 
	 * @CollectionTable( name = "perfil_permissoes", foreignKey = @ForeignKey(name =
	 * "fk_perfil_permissoes"), joinColumns = @JoinColumn(name = "perfil_id"))
	 * 
	 * @Enumerated(EnumType.STRING) private List<DominioPermissaoAcesso> permissoes;
	 */
}
