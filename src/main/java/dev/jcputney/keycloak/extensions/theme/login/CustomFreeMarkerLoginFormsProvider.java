package dev.jcputney.keycloak.extensions.theme.login;

import jakarta.ws.rs.core.UriBuilder;
import java.util.Locale;
import java.util.Properties;
import org.keycloak.forms.login.LoginFormsPages;
import org.keycloak.forms.login.freemarker.FreeMarkerLoginFormsProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.theme.Theme;

public class CustomFreeMarkerLoginFormsProvider extends FreeMarkerLoginFormsProvider {

  public CustomFreeMarkerLoginFormsProvider(KeycloakSession session) {
    super(session);
  }

  @Override
  protected void createCommonAttributes(Theme theme, Locale locale, Properties messagesBundle,
      UriBuilder baseUriBuilder, LoginFormsPages page) {
    super.createCommonAttributes(theme, locale, messagesBundle, baseUriBuilder, page);

    this.attributes.put("passwordPolicies", new PasswordPoliciesBean(this.realm.getPasswordPolicy()));
    this.attributes.put("realmAttributes", this.realm.getAttributes());
  }
}
