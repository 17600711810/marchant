package com.acui.merchant.web.listener;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;


@Component
public class EmbeddedServletListener implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();

    }

    public int getPort() {
        return this.serverPort;
    }

    public String  getHost() throws Exception{
        return InetAddress.getLocalHost().getHostAddress();
    }
}