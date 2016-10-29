package com.example.a360safty.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.a360safty.R;

/**
 * Created by snwfnh on 2016/10/23.
 */
public class SearchActivity extends Activity {
    private TextView tv_search_title;
    private WebView web_view_serach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initView();
        initWeb();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(web_view_serach.canGoBack())
            {
                web_view_serach.goBack();
                return true;
            }
            else
            {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initWeb() {
        web_view_serach.loadUrl("https://m.so.com");
        web_view_serach.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });
    }

    private void initView() {

        tv_search_title = (TextView) findViewById(R.id.tv_search_title);
        tv_search_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
        web_view_serach = (WebView) findViewById(R.id.web_view_serach);

    }

    private void setView() {
        setContentView(R.layout.activity_search);
    }
}
