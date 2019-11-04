package com.dragonforest.test.dragonwebview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.dragonforest.test.dragonwebview.webCallback.DragonWebViewCallback;
import com.dragonforest.test.dragonwebview.webChromeClient.DragonWebChromeClient;
import com.dragonforest.test.dragonwebview.webInterface.DragonInterfaceRules;
import com.dragonforest.test.dragonwebview.webInterface.DragonWebInterface;
import com.dragonforest.test.dragonwebview.webviewClient.DragonWebViewClient;

public class DragonWebViewActivity extends AppCompatActivity implements DragonWebViewCallback {


    // 界面元素
    private WebView webView;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragon_webview);
        initView();
        initData();
    }

    private void initView() {
        webView = findViewById(R.id.dragon_webview);
        toolbar = findViewById(R.id.dragon_webview_toobar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        progressBar = findViewById(R.id.dragon_webview_progressBar);
        progressBar.setMax(100);
        initWebView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initWebView() {
        webView.setWebViewClient(new DragonWebViewClient(this));
        webView.setWebChromeClient(new DragonWebChromeClient(this));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new DragonWebInterface(this), DragonInterfaceRules.INTERFACE_NAME);
    }

    /**
     * 接收参数 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if (url == null) {
            url = "file:///android_asset/defaultPages/error.html";
        }
        String title = intent.getStringExtra("title");
        if (title != null) {
            toolbar.setTitle(title);
        } else {
            toolbar.setTitle("加载中");
        }
        String titleTextColor = intent.getStringExtra("titleTextColor");
        if (titleTextColor != null) {
            toolbar.setTitleTextColor(Color.parseColor(titleTextColor));
        }
        String titleBgColor = intent.getStringExtra("titleBgColor");
        if (titleBgColor != null) {
            toolbar.setBackgroundColor(Color.parseColor(titleBgColor));
        }
        boolean showTitle = intent.getBooleanExtra("showTitle", true);
        if (showTitle) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
        boolean showProgress = intent.getBooleanExtra("showProgress", true);
        if (showProgress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
        webView.loadUrl(url);
    }


    /**
     * 处理回退事件
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


    // 参数设置=========================================

    public static void start(Context context, String url, String title, String titleTextColor, String titleBgColor, boolean showTitle, boolean showProgress) {
        Intent intent = new Intent(context, DragonWebViewActivity.class);
        if (url != null) {
            intent.putExtra("url", url);
        }
        if (title != null) {
            intent.putExtra("title", title);
        }
        if (titleTextColor != null) {
            intent.putExtra("titleTextColor", titleTextColor);
        }
        if (titleBgColor != null) {
            intent.putExtra("titleBgColor", titleBgColor);
        }
        intent.putExtra("showTitle", showTitle);
        intent.putExtra("showProgress", showProgress);
        context.startActivity(intent);
    }

    public static void start(Context context, String url) {
        start(context, url, "加载中...", "#FFFFFF", "#008577", true, true);
    }

    // 回调===============================================

    @Override
    public void onStart(String url) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinish(String url) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onProgressChanged(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void onReceivedTitle(String title) {
        toolbar.setTitle(title);
    }
}
