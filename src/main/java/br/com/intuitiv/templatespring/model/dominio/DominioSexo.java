package br.com.intuitiv.templatespring.model.dominio;

import org.apache.commons.lang3.StringUtils;

import br.com.intuitiv.templatespring.core.model.DominioInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public enum DominioSexo implements DominioInterface<DominioSexo> {
  FEMININO("Feminino"),
  MASCULINO("Masculino");

  @NonNull @Getter private final String desc;

  @Getter private String longDesc;

  @Override
  public DominioSexo valueOf(final int ord) {
    return values()[ord];
  }

  @Override
  public String getLongDesc() {
    return (StringUtils.isBlank(this.longDesc)) ? this.desc : this.longDesc;
  }

  @Override
  public Integer getSize() {
    return values().length;
  }

  @Override
  public boolean isValido(final int cdItemDominio) {
    return this.getSize() >= cdItemDominio;
  }

  @Override
  public Integer getOrdinal() {
    return this.ordinal();
  }

  @Override
  public String getName() {
    return this.name();
  }
}
