package com.example.ms.moneysaver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class MainPage extends AppCompatActivity {
    private Button paytmBtn;
    private Button fcBtn;
    private Button mkBtn;
    private Button goibBtn;
    private Button grfBtn;
    private Button meruBtn;
    private Button olaBtn;
    private Button redbBtn;
    private Button tfsBtn;
    private Button uberBtn;
    private Button fpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(R.string.app_name);

        }

        paytmBtn = (Button) findViewById(R.id.paytm_btn);
        fcBtn = (Button) findViewById(R.id.fc_btn);
        fpBtn = (Button) findViewById(R.id.fp_btn);
        uberBtn = (Button) findViewById(R.id.uber_btn);
        tfsBtn = (Button) findViewById(R.id.tfs_btn);
        mkBtn = (Button) findViewById(R.id.mk_btn);
        meruBtn = (Button) findViewById(R.id.meru_btn);
        goibBtn = (Button) findViewById(R.id.goibibo_btn);
        grfBtn = (Button) findViewById(R.id.grofers_btn);
        olaBtn = (Button) findViewById(R.id.ola_btn);
        redbBtn = (Button) findViewById(R.id.redbus_btn);



        paytmBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                      if(isNetworkConnected()){
                       new WebFetcher().execute("paytm");

                    }
                    else{
                          showCachedContent("paytm");
                    }
                }
                catch (Exception e){
                e.printStackTrace();
                }

            }
        });

        fpBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    if(isNetworkConnected()){
                        new WebFetcher().execute("foodpanda");

                    }
                    else{
                        showCachedContent("foodpanda");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        fcBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    if(isNetworkConnected()){
                        new WebFetcher().execute("freecharge");

                    }
                    else{
                        showCachedContent("freecharge");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        goibBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    if(isNetworkConnected()){
                        new WebFetcher().execute("goibibo");

                    }
                    else{
                        showCachedContent("goibibo");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        grfBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    if(isNetworkConnected()){
                        new WebFetcher().execute("grofers");

                    }
                    else{
                        showCachedContent("grofers");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        meruBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    if(isNetworkConnected()){
                        new WebFetcher().execute("merucabs");

                    }
                    else{
                        showCachedContent("merucabs");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        mkBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    if(isNetworkConnected()){
                        new WebFetcher().execute("mobikwik");

                    }
                    else{
                        showCachedContent("mobikwik");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        olaBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    if(isNetworkConnected()){
                        new WebFetcher().execute("olacabs");

                    }
                    else{
                        showCachedContent("olacabs");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        redbBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    if(isNetworkConnected()){
                        new WebFetcher().execute("redbus");

                    }
                    else{
                        showCachedContent("redbus");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        tfsBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    if(isNetworkConnected()){
                        new WebFetcher().execute("taxiforsure");

                    }
                    else{
                        showCachedContent("taxiforsure");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        uberBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    if(isNetworkConnected()){
                        new WebFetcher().execute("uber");

                    }
                    else{
                        showCachedContent("uber");
                        }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });



    }

    private void showCachedContent(String key){
        //Toast.makeText(getApplicationContext(),"You dont have internet, idiot.",Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = getSharedPreferences(getString(R.string.localDB), MODE_PRIVATE);
        String dateUpdated = prefs.getString(key+"_updated", null);
        if (dateUpdated != null) {
        //  Toast.makeText(getApplicationContext(),"Anyways, showing cached content.",Toast.LENGTH_SHORT).show();
            Set<String> cachedResults = prefs.getStringSet(key,null);
            String[] resultArr = cachedResults.toArray(new String[cachedResults.size()]);
            Intent offer = new Intent("android.intent.action.OFFER");
            offer.putExtra("result", resultArr);
            offer.putExtra("updated",dateUpdated);
            offer.putExtra("siteKey",key);
            startActivity(offer);
        }
        else{
            Toast.makeText(getApplicationContext(),"No content to show",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isNetworkConnected(){
        //Checking internet availability
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        return false;
    }



    class WebFetcher extends AsyncTask<String, Void, HashMap>{


        private Exception exception;


        @Override
        protected HashMap doInBackground(String... params) {
            try {
                String url = "http://www.grabon.in/"+params[0]+"-coupons/";
                Document doc = Jsoup.connect(url).get();
                Elements offrs = doc.getElementsByClass("h3_click");
                Set<String> resultSet= new HashSet<String>();
                HashMap result = new HashMap();
                result.put("siteKey",params[0]);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy h:mm a");
                Date date = new Date();
                result.put("updated",dateFormat.format(date));
                int i = 1;
                for (Element offr : offrs) {
                    String offrString="";
                    if (i == 22 )
                        break;
                    offrString = offr.text();
                    Element parent=offr.parent().parent();
                    try{
                        Element link=parent.getElementsByClass("cpn_click_redir").first();
                        Element	ele=link.getElementsByTag("small").first();
                        offrString+="#COUPON#"+ele.text();
                    }catch(Exception e){
                        offrString+="#COUPON#NO COUPON";
                    }
                    resultSet.add(offrString);
                    i++;
                }
                result.put("resultSet",resultSet);
                return result;
            }
            catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(HashMap result) {

            Intent offer = new Intent("android.intent.action.OFFER");
            Set<String> resultSet =(Set<String>) result.get("resultSet");
            String[] resultArr = resultSet.toArray(new String[resultSet.size()]);
            String siteKey=(String) result.get("siteKey");
            String updated =  (String) result.get("updated");

            offer.putExtra("result", resultArr);
            offer.putExtra("siteKey",siteKey);
            offer.putExtra("updated",updated);

            SharedPreferences.Editor editor =  getSharedPreferences(getString(R.string.localDB), MODE_PRIVATE).edit();
            editor.putStringSet(siteKey,resultSet);
            editor.putString(siteKey+"_updated",updated);
            editor.commit();
            startActivity(offer);
        }
    }
}
