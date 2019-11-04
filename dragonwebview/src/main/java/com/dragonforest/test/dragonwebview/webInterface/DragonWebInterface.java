package com.dragonforest.test.dragonwebview.webInterface;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import static com.dragonforest.test.dragonwebview.webInterface.DragonInterfaceRules.TYPE_NAVIGATE_ACTIVITY;
import static com.dragonforest.test.dragonwebview.webInterface.DragonInterfaceRules.TYPE_SHOW_ALERT;
import static com.dragonforest.test.dragonwebview.webInterface.DragonInterfaceRules.TYPE_SHOW_TOAST;

/**
 * @author DragonForest
 * @date 2019/11/4 12:47
 */
public class DragonWebInterface {
    private Context context;

    public DragonWebInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void forwardWebAction(String type, String command) {
        // 命令分发
        switch (type) {
            case TYPE_SHOW_TOAST:
                showToast(command);
                break;
            case TYPE_SHOW_ALERT:
                showAlert(command);
                break;
            case TYPE_NAVIGATE_ACTIVITY:
                navigateActivity(command);
                break;
            default:
                showToast(type+" 不是可识别的命令！");
                break;
        }
    }

    private void showToast(String command) {
        Toast.makeText(context, command, Toast.LENGTH_SHORT).show();
    }

    private void showAlert(String command) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage(command);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void navigateActivity(String command) {
        try {
            Class<?> aClass = Class.forName(command);
            context.startActivity(new Intent(context, aClass));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            showToast(e.getMessage());
        }
    }
}
