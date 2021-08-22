package com.swipeyourjob.swipeyourjobchat.Controller;

import com.google.gson.Gson;
import com.swipeyourjob.swipeyourjobchat.Controller.RequestHandlers.LoginRequest;
import com.swipeyourjob.swipeyourjobchat.Controller.RequestHandlers.MessageHandler;
import com.swipeyourjob.swipeyourjobchat.Controller.Views.ChatMessageView;
import com.swipeyourjob.swipeyourjobchat.Domain.Room;
import com.swipeyourjob.swipeyourjobchat.Domain.WebUser;
import com.swipeyourjob.swipeyourjobchat.Service.AuthenticationService;
import com.swipeyourjob.swipeyourjobchat.Service.RoomServices;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class chatsockethandler extends TextWebSocketHandler {
    private final List<WebSocketSession> webSocketSessionlist   = new ArrayList<>();
    private final List<WebSocketSession> webSocketLogedInList   = new ArrayList<>();
    private final RoomServices roomServices                     = new RoomServices();
    private final AuthenticationService authServices            = new AuthenticationService();

    private final MessageHandler handler = new MessageHandler();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessionlist.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
            LoginRequest action =  handler.MessageParser(message.getPayload());
            boolean sendmessage = true;
            /*
                Methods:

                Login
                Send message
            */
            switch (action.getAction()){
                case "Login":
                        int userid = this.authServices.getUserid(action.getJwt()); // this will return 0 if the token is invalid
                        if(userid != 0) {
                            this.webSocketLogedInList.add(session);
                            roomServices.addSocketToRooms(userid, session);
                        }else{
                            message = new TextMessage("Sorry that token isn't valid");
                            if(session.isOpen()){
                                session.sendMessage(message);
                            }
                            sendmessage = false;
                        }
                    break;
                case "SendMessage":
                        if(webSocketLogedInList.contains(session)){
                            String textmessage = handler.getJsonObject(message.getPayload(),"message");
                            int roomid = Integer.parseInt(handler.getJsonObject(message.getPayload(),"roomid"));
                            int userid_current = this.authServices.getUserid(action.getJwt()); // this will return 0 if the token is invalid
                            Room messsageroom = this.roomServices.getMessageRoom(roomid,userid_current);
                            WebUser currentuser = this.authServices.getWebuserByUserid(userid_current);
                            ChatMessageView messageclass = new ChatMessageView(textmessage,roomid,currentuser.getFirstName());
                            if(messsageroom != null){
                                this.roomServices.uploadMessage(userid_current,textmessage,roomid);
                                for (WebSocketSession roomuser : messsageroom.getSessionlist()){
                                        Gson gson = new Gson();
                                        TextMessage RESULT = new TextMessage(gson.toJson(messageclass));
                                        if(roomuser.isOpen()){
                                            roomuser.sendMessage(RESULT);
                                        }
                                }
                            }
                        }else{
                            message = new TextMessage("Sorry you are not logged in");
                            if(session.isOpen()){
                                session.sendMessage(message);
                            }
                            sendmessage = false;
                        }
                    break;
                default:
                    sendmessage = false;
            }




    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessionlist.remove(session);
        webSocketLogedInList.remove(session);
        if (session != null) {
            session.close(status);
        }

    }


}
