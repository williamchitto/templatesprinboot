package br.com.intuitiv.templatespring.core.persistence;

import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import com.google.common.base.CaseFormat;

public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

  private static final long serialVersionUID = 1951809166809344577L;

  protected static Identifier convertIdentifierToSnakeCase(final Identifier identifier) {
    if (identifier == null || identifier.getText() == null) {
      return null;
    }
    return Identifier.toIdentifier(convertStringToSnakeCase(identifier.getText()));
  }

  protected static String convertStringToSnakeCase(final String name) {
    if (name == null) {
      return null;
    }
    return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name).toLowerCase(Locale.ROOT);
  }

  @Override
  public Identifier toPhysicalCatalogName(
      final Identifier identifier, final JdbcEnvironment jdbcEnv) {
    return convertIdentifierToSnakeCase(identifier);
  }

  @Override
  public Identifier toPhysicalColumnName(
      final Identifier identifier, final JdbcEnvironment jdbcEnv) {
    return new Identifier(convertStringToSnakeCase(identifier.getText()), identifier.isQuoted());
  }

  @Override
  public Identifier toPhysicalSchemaName(
      final Identifier identifier, final JdbcEnvironment jdbcEnv) {
    return convertIdentifierToSnakeCase(identifier);
  }

  @Override
  public Identifier toPhysicalSequenceName(
      final Identifier identifier, final JdbcEnvironment jdbcEnv) {
    return new Identifier(convertStringToSnakeCase(identifier.getText()), identifier.isQuoted());
  }

  @Override
  public Identifier toPhysicalTableName(
      final Identifier identifier, final JdbcEnvironment jdbcEnv) {
    return new Identifier(convertStringToSnakeCase(identifier.getText()), identifier.isQuoted());
  }
}
