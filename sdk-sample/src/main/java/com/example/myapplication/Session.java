package com.example.myapplication;

import android.util.Log;

import org.json.JSONObject;

import java.util.Map;

public class Session implements SocketListener {

    private static final String TAG = "Session";

    private String url;
    private String sessionId;
    private String tokenId;
    private Map<String, String> metadata;

    private Socket socket = null;
    private boolean pub = false;
    private boolean sub = false;

    public Session(String url, String sessionId, String tokenId, Map<String, String> metadata) {
        this.url = url;
        this.sessionId = sessionId;
        this.tokenId = tokenId;
        this.metadata = metadata;

    }

    public void connect() {
        connect(true, true);
    }

    public void connect(boolean pub, boolean sub) {
        JSONObject params = new JSONObject();
        JSONObject metadata = new JSONObject(this.metadata);
        try {
            params.put("sessionId", sessionId);
            params.put("tokenId", tokenId);
            params.put("metadata", metadata);
        }catch (Exception e){
            //TODO(CC): error
        }

        this.socket = new Socket(this.url, params);
        this.socket.listener = this;
        this.socket.connect();
    }


    @Override
    public void onConnected() {
        Log.d(TAG,"connected");
    }

    @Override
    public void onNotification() {

    }

    @Override
    public void onClosed() {

    }
}
