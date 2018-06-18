package com.example.fragment.subcontechs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameFragment extends Fragment {


    private static final String TAG = MainActivity.class.getCanonicalName();
    private WebView mWebView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.game, container, false);
        mWebView = view.findViewById(R.id.wvPortal);

        String game_url = getArguments().getString("game");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        mWebView.loadUrl(game_url);
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);

        return view;

    }
}