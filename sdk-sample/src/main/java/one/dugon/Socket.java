package one.dugon;


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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;


interface SocketListener {
    public void onConnected();

    public void onNotification();

    public void onClosed();
}

interface RequestCallback {
    void cb(JSONObject data);
}


public class Socket extends WebSocketAdapter {
    private static final String TAG = "Socket";

    private static final int TIMEOUT = 1000;

    private String url;
    private JSONObject params;
    private WebSocket ws;

    public SocketListener listener = null;

    Map<Integer, RequestCallback> callbacks = new HashMap<>();

    public Socket(String url, JSONObject params) {
        this.url = url;
        this.params = params;
    }

    public static int randInt(int n) {
        int max = (int) Math.pow(10, n + 1);
        int min = (int) Math.pow(10, n);
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    void request(String event, Map<String, Object> data, RequestCallback callback) {
        int id = randInt(8);

        callbacks.put(id, callback);

        JSONObject requestJson = new JSONObject();

        JSONObject params = new JSONObject();
        JSONObject dataJson = new JSONObject(data);


        try {
            params.put("event",event);
            params.put("data",dataJson);

            requestJson.put("id", id);
            requestJson.put("method", "request");
            requestJson.put("params", params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ws.sendText(requestJson.toString());
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

    public static String encryptBase64(String str) {
        String res = null;
        try {
            res = new String(Base64.encode(str.getBytes("UTF-8"), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //todo : encode error
        }
        return res;
    }

    // -- WebSocketAdapter


    @Override
    public void onTextMessage(WebSocket websocket, String text) throws Exception {
        Log.i(TAG, text);

        try {
            JSONObject response = new JSONObject(text);
            int id = response.getInt("id");
            String method = response.getString("method");
            JSONObject params = response.getJSONObject("params");

            if (method.equals("response")){
                RequestCallback callback = callbacks.get(id);
                callback.cb(params);
                callbacks.remove(id);
            }else{
                //TODO(CC): notification
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        if (listener != null) {
            listener.onConnected();
        }
    }


}
