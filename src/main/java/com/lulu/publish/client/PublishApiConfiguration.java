package com.lulu.publish.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holder for publish API configuration values.
 */
public class PublishApiConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(PublishApiConfiguration.class); // NOPMD

    private String apiKey;
    private String email;
    private String password;

    /**
     * Construct the client with the default configuration file.
     */
    public PublishApiConfiguration() {
    }

    /**
     * Construct with the specified configuration file.

     * Also sets up SSL to accept <b>all</b> certificates, even self-signed. This is not recommended for production use.
     *
     * @param configFile properties file
     * @throws IOException if the properties file could not be located or parsed
     */
    public PublishApiConfiguration(String configFile) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(configFile));

        apiKey = properties.getProperty("authentication.apiKey", "");
        email = properties.getProperty("authentication.email", "");
        password = properties.getProperty("authentication.password", "");
    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PublishApiConfiguration{"
                + "apiKey='" + apiKey + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
