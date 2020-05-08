package com.example.myapplication;


import android.util.Base64;
import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketExtension;
import com.neovisionaries.ws.client.WebSocketFactory;

import org.json.JSONObject;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


interface SocketListener {
    public void onConnected();

    public void onNotification();

    public void onClosed();
}

public class Socket extends WebSocketAdapter {
    private static final String TAG = "Socket";

    private static final int TIMEOUT = 1000;

    private String url;
    private JSONObject params;
    private WebSocket ws;

    public SocketListener listener = null;

    public Socket(String url, JSONObject params) {
        this.url = url;
        this.params = params;
    }

    private String getFullURL() {
        String url = null;
        try {
            Log.i(TAG, this.params.toString());
            String params = encryptBase64(this.params.toString());
            String encode = URLEncoder.encode(params, "UTF-8");

            //        "ws://192.168.31.254:8800?params=" + encode;
            url = String.format("%s?params=%s", this.url, encode);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public void connect() {
        try {
            this.ws = new WebSocketFactory()
                    .setConnectionTimeout(TIMEOUT)
                    .createSocket(getFullURL()).addListener(this).connect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTextMessage(WebSocket websocket, byte[] data) throws Exception {

    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        if (listener != null) {
            listener.onConnected();
        }
    }

    public static String encryptBase64(String str) {
        String res = null;
        try {
            res = new String(Base64.encode(str.getBytes("UTF-8"), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //todo : encode error
        }
        return res;
    }


}
