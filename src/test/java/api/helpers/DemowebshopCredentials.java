package api.helpers;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/DemowebshopCredentials.properties")
public interface DemowebshopCredentials extends Config {
    String login();
    String password();
    String authCookieName();
}
