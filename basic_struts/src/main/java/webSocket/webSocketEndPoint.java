package webSocket;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat", decoders = messageDecoder.class, encoders = messageEncoder.class)
public class webSocketEndPoint {


    private static ConcurrentHashMap<String,Session> sessions = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Session,String> usernames = new ConcurrentHashMap<>();


    @OnMessage
    public String onMessage(Session session, messageModel message){

        System.out.println("handling message: " + message);

        String username =message.getUserName();

        System.out.println(username);




        switch (message.getType())
        {
            case "setUserName":

                sessions.put(username,session);

                usernames.put(session,username);

                System.out.println("sessions:" +usernames.toString());

                break;

            case "privateMessage":

                Session session2 =sessions.get(username);

                try {
                    message.setUserName(usernames.get(session));

                    session2.getBasicRemote().sendObject(message);


                } catch (Exception e) {

                    e.printStackTrace();

                }
                break;

            case "broadcast":
                for (String key : sessions.keySet()){

                    Session session1 = sessions.get(key);


                    if (session1 == session)
                    {
                        continue;
                    }

                    try {
                        message.setUserName(usernames.get(session));

                        session1.getBasicRemote().sendObject(message);


                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

                break;
            default:
        }
        return null;
    }



    @OnOpen
    public void onOpen(Session session){

        System.out.println("On open: "+ session.getId());

        session.setMaxIdleTimeout(5000000);

    }

    @OnClose
    public void onClose(Session session){

        System.out.println("On close: "+ session.getId());

        sessions.remove(session);
    }

}
