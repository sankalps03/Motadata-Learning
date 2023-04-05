package webSocket;

import com.google.gson.Gson;
import webSocket.messageModel;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class messageDecoder implements Decoder.Text<messageModel> {

    Gson gson = new Gson();

    @Override
    public messageModel decode(String s) throws DecodeException {
        return gson.fromJson(s, messageModel.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

}