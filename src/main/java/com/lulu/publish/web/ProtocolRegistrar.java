package com.lulu.publish.web;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class that handles the registration of {@link Protocol} with a {@link ProtocolSocketFactory}.
 */
public final class ProtocolRegistrar {

    private static final Logger LOG = LoggerFactory.getLogger(ProtocolRegistrar.class); // NOPMD
    private static final String SSL_SCHEME = "https";
    private static final int SSL_PORT = 443;

    private ProtocolRegistrar() {
    }

    public static void registerTrustAllProtocolSocketFactory() {
        registerProtocolSocketFactory(SSL_SCHEME, SSL_PORT, new TrustAllSSLProtocolSocketFactory());
    }

    private static void registerProtocolSocketFactory(String scheme, int port, ProtocolSocketFactory factory) {
        Protocol https = new Protocol(scheme, factory, port);
        Protocol.registerProtocol(scheme, https);
    }

}
