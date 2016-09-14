package hck.irbot;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import hck.irbot.common.FixData;

public class webViewActivity extends AppCompatActivity {
    Context context;

    WebView webView;
    TextView loading;

    boolean pageFinish;
    boolean javascriptFinish;
    String mainUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        context = this;

        webView = (WebView) findViewById(R.id.webView);
        loading = (TextView) findViewById(R.id.loading);

        mainUrl = FixData.URL_Main;
    }

    public void clearCookie(){
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
        CookieManager.getInstance().removeSessionCookie();
    }

    public void loadJavaScript(WebView view, String javaScript){
        view.loadUrl("javascript:(function() {$( document ).ready(function() { " +
                javaScript +
                "});})()");
    }

    public void injectScriptFile(WebView view, String scriptFile) {
        InputStream input;
        try {
            input = getAssets().open(scriptFile);
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            input.close();

            // String-ify the script byte-array using BASE64 encoding !!!
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            view.loadUrl("javascript:(function() {" +
                    "})()");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setWebView() {
        webView.setVisibility(View.VISIBLE);
        webView.loadUrl(mainUrl);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                String url = view.getUrl();

                if (newProgress == 100) {
//                    int count = loadingCount.get(url);
//                    if (count == 0) {
//                        javascriptFinish = true;
//                        if (pageFinish) {
//                            addJavaScript(view, url);
//                        }
//                    }
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pageFinish = false;
                javascriptFinish = false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pageFinish = true;
                if (javascriptFinish) {
                    addJavaScript(view, url);
                }
                super.onPageFinished(view, url);
            }

//            @Override
//            public void onReceivedError (WebView view, WebResourceRequest request, WebResourceError error){
////                Toast.makeText(context, "Conflict! Last web is not complete close!", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
////                handler.proceed() ;
//            }
        });
    }

    private void addJavaScript(WebView view, String url){
//        int count = loadingCount.get(url);
//        if (count == 0) {
            loading.setVisibility(View.VISIBLE);
            if (url.equalsIgnoreCase(mainUrl)) {
                loadJavaScript(view, "");
            }  else {
                Log.w("hck newUrl", url);
//                        Toast.makeText(context, url, Toast.LENGTH_LONG).show();
            }
//            loadingCount.put(url, ++count);
//        }
    }
}
