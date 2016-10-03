package com.tompee.utilities.knowyourmeds.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tompee.utilities.knowyourmeds.R;
import com.tompee.utilities.knowyourmeds.view.base.BaseActivity;

public class SPLDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String NAME = "name";
    public static final String URL = "url";

    private WebView mWebview;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spl_detail);
        setToolbar(R.id.toolbar, true);

        TextView title = (TextView) findViewById(R.id.toolbar_text);
        title.setText(getIntent().getStringExtra(NAME));

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.container);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mWebview = (WebView) findViewById(R.id.webview);
        mWebview.setWebViewClient(new GenericWebClient());
        mWebview.setWebChromeClient(new GenericWebChromeClient());
        mWebview.clearCache(true);
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        mWebview.loadUrl(getIntent().getStringExtra(URL));
    }

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        if (mWebview != null) {
            mWebview.reload();
        }
    }

    private class GenericWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mProgressBar.setProgress(0);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.INVISIBLE);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private class GenericWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int progress) {
            mProgressBar.setProgress(progress);
        }
    }
}