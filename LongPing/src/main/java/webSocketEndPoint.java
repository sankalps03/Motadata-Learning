
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint(value = "/chat")
public class webSocketEndPoint {

    static Session sessionId ;


    @OnOpen
    public void onOpen(Session session){

        sessionId = session;

        System.out.println("On open: "+ session.getId());

        session.setMaxIdleTimeout(5000000);

    }

    @OnClose
    public void onClose(Session session){

        System.out.println("On close: "+ session.getId());

    }

    public static void getPingData(String summary){

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

