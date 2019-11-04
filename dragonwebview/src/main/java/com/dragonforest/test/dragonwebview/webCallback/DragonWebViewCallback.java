package com.dragonforest.test.dragonwebview.webCallback;

/**
 * @author DragonForest
 * @date 2019/11/4 11:25
 */
public interface DragonWebViewCallback {
    /**
     * 开始加载网页
     * @param url
     */
    void onStart(String url);

    /**
     * 结束加载网页
     * @param url
     */
    void onFinish(String url);

    /**
     * 加载网页进度变化
     * @param progress
     */
    void onProgressChanged(int progress);

    /**
     * 获取网页标题
     * @param title
     */
    void onReceivedTitle(String title);
}
