package br.com.intuitiv.templatespring.core.model;

public interface DominioInterface<T> {

  @Override
  String toString();

  String getLongDesc();

  Integer getOrdinal();

  String getName();

  String getDesc();

  Integer getSize();

  boolean isValido(int cdItemDominio);

  T valueOf(int ord);
}
