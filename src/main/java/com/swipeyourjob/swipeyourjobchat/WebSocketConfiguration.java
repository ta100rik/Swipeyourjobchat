package com.swipeyourjob.swipeyourjobchat;


import com.swipeyourjob.swipeyourjobchat.Controller.chatsockethandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
    private final static String endpoint = "/chat";
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getWebsockethandler(),endpoint).setAllowedOrigins("*");
    }
    @Bean
    public WebSocketHandler getWebsockethandler(){
        return  new chatsockethandler();
    }
}
