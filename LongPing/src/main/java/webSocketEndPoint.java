
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value = "/chat")
public class webSocketEndPoint {

    static Session sessionId ;

    private static ConcurrentHashMap<Session,ConcurrentHashMap<String,Session>> sessions = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(Session session){

        sessionId = session;

        System.out.println("On open: "+ session.getId());

        session.setMaxIdleTimeout(5000000);

        try {

            session.getBasicRemote().sendText("SEND");

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    @OnMessage
    public void onMessage(Session session,String ip){

        ConcurrentHashMap<String,Session> map = new ConcurrentHashMap<>();
        map.put(ip,session);

        sessions.put(session,map);
    }

    @OnClose
    public void onClose(Session session){

        sessions.remove();

        System.out.println("On close: "+ session.getId());

    }

    public static void getPingData(String ip,String summary){

        sessionId= sessions.get(ip);

        sendPingData(sessionId,summary);

    }

    public static void sendPingData(Session session,String summary){

        try {
            session.getBasicRemote().sendText(summary);
            System.out.println("ping data sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

