package br.com.intuitiv.templatespring.model.security;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.intuitiv.templatespring.core.persistence.BaseEntity;
import br.com.intuitiv.templatespring.model.dominio.DominioSexo;
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
@Entity
@Builder
@Audited
public class Usuario extends BaseEntity implements UserDetails {

	public static final String SEQUENCE_NAME = "SEQUENCE_USUARIO";
	private static final long serialVersionUID = 6268173241893523455L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotEmpty
	@Column(nullable = false)
	private String nome;

	@NotEmpty
	@Column(nullable = false)
	private String cpf;

	@NotNull
	@Email
	private String email;

	private String telefone;

	@NotNull
	@JoinColumn(name = "perfil_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_usuario_perfil"))
	@ManyToOne(fetch = FetchType.EAGER)
	// @RestResource(exported = false)
	private Perfil perfil;

	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DominioSexo sexo;

	@NotEmpty
	@Column(nullable = false)
	@Length(min = 3, max = 30)
	private String login;

	@JsonIgnore
	@Column(length = 60)
	@Length(min = 8)
	private String senha;

	@Override
	@JsonIgnore
	public String getPassword() {
		return this.getSenha();
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return this.getLogin();
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}
