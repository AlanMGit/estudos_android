package com.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    
    private WebView mWebViewWeb;
    private WebView mWebViewJavaScript;
    private WebView mWebViewHTML;
    private View mProgress;
    WebAppInterface JSInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebViewWeb = (WebView)findViewById(R.id.webview);
        mWebViewHTML = (WebView)findViewById(R.id.webview_html);
        mWebViewJavaScript = (WebView)findViewById(R.id.webview_javascript);
        mProgress = findViewById(R.id.progress);
        mWebViewWeb.loadUrl("http://www.google.com.br");

        mWebViewWeb.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgress.setVisibility(View.INVISIBLE);
            }
        });

        //Carregando HTML
        mWebViewHTML.loadDataWithBaseURL("", "<font color='blue'>HTML aqui <font>",
                "text/html", "UTF-8", "");

        //JavaScript
        JSInterface = new WebAppInterface(this);
        mWebViewJavaScript.addJavascriptInterface(JSInterface, "Android");
        mWebViewJavaScript.loadUrl("file:///android_asset/myPage.html");
    }
}
