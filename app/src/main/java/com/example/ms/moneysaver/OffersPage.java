package com.example.ms.moneysaver;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by ms on 6/6/16.
 */
public class OffersPage  extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.offers_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent intent = getIntent();

        String [] results = intent.getStringArrayExtra("result");
        String updated =intent.getStringExtra("updated");
        String key = intent.getStringExtra("siteKey");
        String pck = getApplicationContext().getPackageName();
        int abc= R.drawable.paytm_logo;
        int logo = getApplicationContext().getResources().getIdentifier(key+"_logo","drawable",getApplicationContext().getPackageName());

        results= rearrangeResults(results);
        TextView toolBarDate =  (TextView) findViewById(R.id.toolbarTime);
        toolBarDate.setText("  Updated on "+updated);
        ImageView keyImage = (ImageView) findViewById(R.id.keyImage);
        keyImage.setImageResource(logo);
        LinearLayout l = (LinearLayout) findViewById(R.id.offerPage);

        for(String result:results){
            if(result != null){
            TextView tv = createTextView(result);
            l.addView(tv);}
        }

        Toast.makeText(getApplicationContext(),"Tap to copy code",Toast.LENGTH_SHORT).show();

    }
    private String[] rearrangeResults(String [] results){

        List<String> templist = new ArrayList<String>();
        List<String> list = new ArrayList<String>(Arrays.asList(results));
        List<String> listFinal = new ArrayList<String>();
        for(String res: list){
            if(res!=null && "NO COUPON".equals(res.split("#COUPON#")[1])){
                templist.add(res);
               }
            }
        for(String res:templist)
          list.remove(res);
        listFinal.addAll(list);
        listFinal.addAll(templist);
        results = listFinal.toArray(new String[0]);
        return results;
    }
    protected TextView createTextView(String result){
        TextView tv=  new TextView(this);
        final String coupon = result.split("#COUPON#")[1];
        String desc = result.split("#COUPON#")[0];
        SpannableString span1 = new SpannableString(coupon);
        SpannableString span2 = new SpannableString(desc);
        span1.setSpan(new AbsoluteSizeSpan(40), 0, coupon.length(),0);
        span1.setSpan(new ForegroundColorSpan(0xff117D22), 0, coupon.length(),0);
        span2.setSpan(new AbsoluteSizeSpan(35), 0, desc.length(),0);
        CharSequence finalText = TextUtils.concat(span1, "\n", span2);
        tv.setText(finalText);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(5,5,5,15);
        tv.setBackgroundResource(R.drawable.roundbutton);
        tv.setLayoutParams(params);
        tv.setPadding(20,10,10,10);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"NO COUPON".equals(coupon)){
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("coupon",coupon);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(),"Code copied",Toast.LENGTH_SHORT).show();
                }


            }
        });
        return tv;
    }
}
