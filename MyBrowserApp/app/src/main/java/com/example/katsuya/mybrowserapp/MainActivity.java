package com.example.katsuya.mybrowserapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends Activity {

    private WebView myWebView;
    private EditText urlText;

    private static final String INITIAL_WEBSITE ="http://dotinstall.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.myWebView);
        urlText = (EditText) findViewById(R.id.urlText);

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                getActionBar().setSubtitle(view.getTitle());
                urlText.setText(url);
            }
        });

        myWebView.loadUrl(INITIAL_WEBSITE);
    }

    public void clearUrl(View view){
        urlText.setText("");
    }

    public void showWebsite(View view){
        String url = urlText.getText().toString().trim();

        if(!Patterns.WEB_URL.matcher(url).matches()){
            urlText.setError("Invalid URL");
        } else {
            if(!url.startsWith("http://") && !url.startsWith("https://")){
                url = "http://" + url;
            }
            myWebView.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myWebView != null) {
            myWebView.stopLoading();
            myWebView.setWebViewClient(null);
            myWebView.destroy();
        }
        myWebView = null;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem forwardItem = menu.findItem(R.id.action_forward);
        MenuItem backItem = menu.findItem(R.id.action_back);
        forwardItem.setEnabled(myWebView.canGoForward());
        backItem.setEnabled(myWebView.canGoBack());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_reload:
                myWebView.reload();
                return true;
            case R.id.action_forward:
                myWebView.goForward();
                return true;
            case R.id.action_back:
                myWebView.goBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
