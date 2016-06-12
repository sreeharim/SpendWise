package com.example.ms.moneysaver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ms on 10/6/16.
 */
public class Splash extends Activity{

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
     //   window.setStatusBarColor(0xff1a51cc);
        setContentView(R.layout.splash);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent i = new Intent("com.example.ms.moneysaver.STARTINGPOINT");
                    startActivity(i);
                }
            }
        };
        timer.start();
    }
}
