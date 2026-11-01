package com.mh3.database;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/** Abre a página do app (assets/index.html) dentro de um WebView. */
public class MainActivity extends Activity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        web = new WebView(this);
        setContentView(web);

        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(false);
        s.setAllowFileAccessFromFileURLs(false);
        s.setAllowUniversalAccessFromFileURLs(false);

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                if ("file".equals(uri.getScheme())) {
                    return false;
                }
                // Links externos (ex.: "Buscar na web") abrem no navegador do celular.
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } catch (Exception ignored) {
                    // Nenhum app para abrir o link.
                }
                return true;
            }
        });

        boolean night = (getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
        web.loadUrl("file:///android_asset/index.html?theme=" + (night ? "dark" : "light"));
    }

    /** Voltar fecha a ficha aberta na página; só sai do app quando já está na lista. */
    @Override
    public void onBackPressed() {
        web.evaluateJavascript(
                "window.__mh3Back ? window.__mh3Back() : false",
                value -> {
                    if (!"true".equals(value)) {
                        finish();
                    }
                });
    }
}
