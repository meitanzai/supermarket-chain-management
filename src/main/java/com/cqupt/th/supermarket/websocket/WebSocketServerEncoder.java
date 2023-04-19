package com.cqupt.th.supermarket.websocket;

import com.alibaba.fastjson.JSONObject;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author th
 * @date 2023/4/19 13:56
 */
public class WebSocketServerEncoder implements Encoder.Text<Object> {
    @Override
    public String encode(Object o) throws EncodeException {
        return JSONObject.toJSONString(o);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
