package top.yat.websocket.core;

import com.alibaba.fastjson.JSON;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ServerEncoder implements Encoder.Text<Object> {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(EndpointConfig arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public String encode(Object obj) {
        return JSON.toJSONString(obj);
    }
}
