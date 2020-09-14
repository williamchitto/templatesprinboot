package br.com.intuitiv.templatespring.core.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity implements IEntity {

  private static final long serialVersionUID = -7896075878781537311L;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Date dataCriacao;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Date dataAtualizacao;

  @PrePersist
  protected void onCreate() {
    this.setDataCriacao(new Date());
  }

  @PreUpdate
  protected void onUpdate() {
    this.setDataAtualizacao(new Date());
  }
}
