package dev.jcputney.keycloak.extensions.theme.login;

import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.forms.login.freemarker.FreeMarkerLoginFormsProviderFactory;
import org.keycloak.models.KeycloakSession;

public class CustomFreeMarkerLoginFormsProviderFactory extends FreeMarkerLoginFormsProviderFactory {
  @Override
  public LoginFormsProvider create(KeycloakSession session) {
    return new CustomFreeMarkerLoginFormsProvider(session);
  }
}
