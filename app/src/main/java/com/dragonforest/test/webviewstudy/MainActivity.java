package com.dragonforest.test.webviewstudy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_baidu:
                goDragonWebView("https://www.baidu.com");
                break;
            case R.id.btn_js:
                goDragonWebView("file:///android_asset/defaultPages/main.html");
                break;
        }
    }

    private void goDragonWebView(String url) {
//        Intent intent=new Intent(MainTestActivity.this,DragonWebViewActivity.class);
//        intent.putExtra("url","https://www.baidu.com");
//        startActivity(intent);
//        DragonWebViewActivity.setUrl("https://www.baidu.com");
//        DragonWebViewActivity.start(this, "https://www.baidu.com");
        try{
            Class<?> aClass = Class.forName("com.dragonforest.test.dragonwebview.DragonWebViewActivity");
            Method startMehod = aClass.getDeclaredMethod("start", Context.class, String.class);
            startMehod.invoke(null,this,url);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("MainTestActivity",e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
