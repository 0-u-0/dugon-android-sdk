package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";


    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private Session session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Map<String, String> metadata = new HashMap<>();
        session = new Session("ws://192.168.31.254:8800", "test", "bob", metadata);
        executor.execute(() -> session.connect());
    }


}
