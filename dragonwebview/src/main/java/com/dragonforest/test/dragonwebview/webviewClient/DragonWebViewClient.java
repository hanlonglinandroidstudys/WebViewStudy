package com.dragonforest.test.dragonwebview.webviewClient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dragonforest.test.dragonwebview.webCallback.DragonWebViewCallback;

/**
 * @author DragonForest
 * @date 2019/11/4 11:20
 */
public class DragonWebViewClient extends WebViewClient {
    private final String TAG = "DragonWebViewClient";
    private DragonWebViewCallback callback;

    public DragonWebViewClient(DragonWebViewCallback callback) {
        this.callback = callback;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e(TAG, "DragonWebViewClient#shouldOverrideUrlLoading(),url:" + url);
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.e(TAG, "DragonWebViewClient#onPageStarted(),url:" + url);
        callback.onStart(url);
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.e(TAG, "DragonWebViewClient#onPageFinished(),url:" + url);
        callback.onFinish(url);
        super.onPageFinished(view, url);
    }
}
