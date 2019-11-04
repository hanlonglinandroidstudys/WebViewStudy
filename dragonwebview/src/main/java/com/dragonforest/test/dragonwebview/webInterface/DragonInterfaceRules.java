package com.dragonforest.test.dragonwebview.webInterface;

/**
 * 定义Android 和 js 通信的接口规则
 *
 * @author DragonForest
 * @date 2019/11/4 14:10
 */
public class DragonInterfaceRules {
    /**
     * 接口名
     * Android 端WebView.addJavaScriptInterface（）时的第二个参数
     * JS 端调用的接口名，用来代表DragonWebInterface对象
     */
    public static final String INTERFACE_NAME = "DragonWebInterface";

    // 定义命令和规则
    /**
     * 规则1：
     * 显示Toast命令
     * 此类型下所有command为Toast显示的内容
     */
    public static final String TYPE_SHOW_TOAST = "showToast";
    /**
     * 规则2：
     * 显示alert命令
     * 此类型下所有command为Toast显示的内容
     */
    public static final String TYPE_SHOW_ALERT = "showAlert";

    /**
     * 规则3：
     * 跳转Activity
     * 此类型下所有command为要跳转的Activity的全类名
     */
    public static final String TYPE_NAVIGATE_ACTIVITY = "navigateActivity";
}
