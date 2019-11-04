package com.dragonforest.test.dragonwebview.webChromeClient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.dragonforest.test.dragonwebview.webCallback.DragonWebViewCallback;

/**
 * @author DragonForest
 * @date 2019/11/4 11:29
 */
public class DragonWebChromeClient extends WebChromeClient {
    private String TAG = "DragonWebChromeClient";
    private DragonWebViewCallback callback;

    public DragonWebChromeClient(DragonWebViewCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        Log.e(TAG, "DragonWebChromeClient#onProgressChanged(),progress:" + newProgress);
        callback.onProgressChanged(newProgress);
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        Log.e(TAG, "DragonWebChromeClient#onReceivedTitle(),title:" +title );
        callback.onReceivedTitle(title);
        super.onReceivedTitle(view, title);
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.e(TAG, "DragonWebChromeClient#onConsoleMessage(),message:" +consoleMessage.message()+",lineNumber:"+consoleMessage.lineNumber()+",sourceId:"+consoleMessage.sourceId() );
        return super.onConsoleMessage(consoleMessage);
    }
}
