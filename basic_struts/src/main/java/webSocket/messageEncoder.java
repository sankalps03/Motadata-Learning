package webSocket;

import com.google.gson.Gson;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


public class messageEncoder implements Encoder.Text<messageModel>{

    Gson gson = new Gson();

    @Override
    public String encode(messageModel message) throws EncodeException {
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

}

