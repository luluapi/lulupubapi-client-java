package com.lulu.publish.web;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class that handles the registration of an ssl context with a client.
 */
public final class ProtocolRegistrar {

    private static final Logger LOG = LoggerFactory.getLogger(ProtocolRegistrar.class); // NOPMD
    private static final String SSL_SCHEME = "https";
    private static final int SSL_PORT = 443;

    private ProtocolRegistrar() {
    }

    public static void registerTrustAllSslContextWithHttpClient(HttpClient httpClient) throws IOException {
        SSLContext sslcontext;
        try {
            sslcontext = TrustAllSSLContextFactory.createTrustAllSSLContext();
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        } catch (KeyManagementException e) {
            throw new IOException(e);
        }
        SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = httpClient.getConnectionManager();
        SchemeRegistry schemeRegistry = ccm.getSchemeRegistry();
        schemeRegistry.register(new Scheme(SSL_SCHEME, sf, SSL_PORT));
    }

}
