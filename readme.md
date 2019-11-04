# 独立进程的，支持命令分发的WebView

## 独立进程
    启动的DragonWeActivity 在单独进程中，不会占用主进程资源
    
## 命令分发
*  com.dragonforest.test.dragonwebview.webInterface.DragonInterfaceRules中定义了Android与JavaScript的通信规则，两者都应遵循此规则
*  现有规则：
* * 1.显示Toast命令
* * 2.跳转Activity
* * 3.显示alert命令  
* JS例子：
```
    function showAndroidToast(showAndroidToast) {
        // 在android中调用Toast显示
        DragonWebInterface.forwardWebAction('showToast',showAndroidToast);
        console.log("正在调试：showAndroidToast（）"+showAndroidToast);
    }

    function gotoError() {
        // 在android中跳转Activity
        DragonWebInterface.forwardWebAction('navigateActivity',"com.dragonforest.test.dragonwebview.ErrorTestActivity");
        console.log("正在调试：gotoError（）");
    }

    function showAlert() {
        // 在android 中显示AlertDialog对话框
        DragonWebInterface.forwardWebAction('showAlert',"你正在访问不安全网站！！");
        console.log("正在调试：showAlert（）");
    }
```

## 使用
    可调用两个接口使用：
    * 1.DragonWebViewActivity.start(Context context, String url)
    * 2.DragonWebViewActivity.start(Context context, String url, String title, String titleTextColor, String titleBgColor, boolean showTitle, boolean showProgress)
    
    简单使用两个接口即可完成WebView的启动