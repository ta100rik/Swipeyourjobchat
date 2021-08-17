package com.swipeyourjob.swipeyourjobchat.Controller;

import com.swipeyourjob.swipeyourjobchat.Controller.RequestHandlers.LoginRequest;
import com.swipeyourjob.swipeyourjobchat.Controller.RequestHandlers.MessageHandler;
import com.swipeyourjob.swipeyourjobchat.Service.RoomServices;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class chatsockethandler extends TextWebSocketHandler {
    private final List<WebSocketSession> webSocketSessionlist = new ArrayList<>();
    private final List<WebSocketSession> webSocketLogedInList = new ArrayList<>();
    private final RoomServices roomServices = new RoomServices();

    private final MessageHandler handler = new MessageHandler();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessionlist.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LoginRequest action =  handler.MessageParser(message.getPayload());
        /*
            Methods:


            Login
            Send message
        */
        switch (action.getAction()){
            case "Login":
                    this.webSocketLogedInList.add(session);
                    roomServices.addSocketToRooms(100,session);
                    System.out.println(roomServices.roomlist.size());
                break;
            case "SendMessage":

                break;
        }
        for (WebSocketSession websocketses : webSocketSessionlist){
            if(webSocketLogedInList.contains(websocketses)){
                websocketses.sendMessage(message);
            }
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessionlist.remove(session);
    }
}
