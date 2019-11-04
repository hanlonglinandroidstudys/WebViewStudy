package com.dragonforest.test.dragonwebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_baidu) {
            goDragonWebView("https://www.baidu.com");

        } else if (i == R.id.btn_js) {
            goDragonWebView("file:///android_asset/defaultPages/main.html");

        }
    }

    private void goDragonWebView(String url) {
//        Intent intent=new Intent(MainTestActivity.this,DragonWebViewActivity.class);
//        intent.putExtra("url","https://www.baidu.com");
//        startActivity(intent);
//        DragonWebViewActivity.setUrl("https://www.baidu.com");
//        DragonWebViewActivity.start(this, "https://www.baidu.com");
        DragonWebViewActivity.start(this, url);
    }
}
