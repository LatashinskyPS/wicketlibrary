package com.intexsoft;

import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.http.ssl.SslContextFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.ssl.SslSocketConnector;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

public class TaskLibraryApplication {
    public static void main(String[] args) throws Exception {
        int timeout = (int) Duration.ONE_HOUR.getMilliseconds();

        Server server = new Server();
        SocketConnector connector = new SocketConnector();

        connector.setMaxIdleTime(timeout);
        connector.setSoLingerTime(-1);
        connector.setPort(8080);
        server.addConnector(connector);



        WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath("/");
        bb.setWar("src/main/webapp");

        server.setHandler(bb);

        server.start();
    }
}
