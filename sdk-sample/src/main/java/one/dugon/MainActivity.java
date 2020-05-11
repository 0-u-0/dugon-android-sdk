package one.dugon;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";


    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private Session session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String j = "{  \"channels\": 1,  \"clockRate\": 90000,  \"cname\": null,  \"codecFullName\": \"H264-CONSTRAINED-BASELINE\",  \"codecName\": \"H264\",  \"dtx\": false,  \"extensions\": [    {      \"id\": 1,      \"recv\": true,      \"send\": true,      \"uri\": \"urn:ietf:params:rtp-hdrext:sdes:mid\"    },    {      \"id\": 2,      \"recv\": true,      \"send\": false,      \"uri\": \"urn:ietf:params:rtp-hdrext:sdes:rtp-stream-id\"    },    {      \"id\": 3,      \"recv\": true,      \"send\": false,      \"uri\": \"urn:ietf:params:rtp-hdrext:sdes:repaired-rtp-stream-id\"    },    {      \"id\": 4,      \"recv\": true,      \"send\": true,      \"uri\": \"http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\"    },    {      \"id\": 5,      \"recv\": true,      \"send\": true,      \"uri\": \"http://www.ietf.org/id/draft-holmer-rmcat-transport-wide-cc-extensions-01\"    },    {      \"id\": 6,      \"recv\": true,      \"send\": true,      \"uri\": \"http://tools.ietf.org/html/draft-ietf-avtext-framemarking-07\"    },    {      \"id\": 7,      \"recv\": true,      \"send\": true,      \"uri\": \"urn:ietf:params:rtp-hdrext:framemarking\"    },    {      \"id\": 11,      \"recv\": true,      \"send\": true,      \"uri\": \"urn:3gpp:video-orientation\"    },    {      \"id\": 12,      \"recv\": true,      \"send\": true,      \"uri\": \"urn:ietf:params:rtp-hdrext:toffset\"    }  ],  \"kind\": \"video\",  \"mid\": null,  \"mux\": true,  \"parameters\": {    \"level-asymmetry-allowed\": \"1\",    \"packetization-mode\": \"1\",    \"profile-level-id\": \"42e01f\"  },  \"payload\": 107,  \"reducedSize\": true,  \"rtcpFeedback\": [    {      \"parameter\": \"\",      \"type\": \"nack\"    },    {      \"parameter\": \"pli\",      \"type\": \"nack\"    },    {      \"parameter\": \"fir\",      \"type\": \"ccm\"    },    {      \"parameter\": \"\",      \"type\": \"goog-remb\"    },    {      \"parameter\": \"\",      \"type\": \"transport-cc\"    }  ],  \"rtx\": null,  \"senderPaused\": false,  \"ssrc\": null}";
        Gson gson = new Gson();
        Codec codec = gson.fromJson(j, Codec.class);

//        Map<String, String> metadata = new HashMap<>();
//        session = new Session("ws://192.168.31.254:8800", "test", "bob", metadata);
//        executor.execute(() -> session.connect());
    }


}
