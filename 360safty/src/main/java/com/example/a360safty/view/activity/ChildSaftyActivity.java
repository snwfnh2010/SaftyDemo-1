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
 * Created by snwfnh on 2016/10/29.
 */
public class ChildSaftyActivity extends Activity {
    private TextView tv_child_title;
    private WebView web_view_child;

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
            if(web_view_child.canGoBack())
            {
                web_view_child.goBack();
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
        web_view_child.loadUrl("https://baby.360.cn/topic/sjws");
        web_view_child.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });
    }

    private void setView() {
        setContentView(R.layout.activity_childsafty);
    }

    private void initView() {
        tv_child_title = (TextView) findViewById(R.id.tv_child_title);
        tv_child_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildSaftyActivity.this.finish();
            }
        });
        web_view_child = (WebView) findViewById(R.id.web_view_child);
    }
}
