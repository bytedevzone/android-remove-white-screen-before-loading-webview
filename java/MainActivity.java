
public class MainActivity extends Activity {

    WebView webView, webView2;
    WebAppInterface mWebAppInterface;
    AdView adView;
    int screenHeight;
    ProgressBar mProgress;
    final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        mWebAppInterface = new WebAppInterface(this);


        // the init state of progress dialog
         mProgress = (ProgressBar) findViewById(R.id.progressBar);
         mProgress.setVisibility(View.VISIBLE);

        webView = (WebView) findViewById(R.id.webView1);
        webView.clearHistory();
        webView.clearCache(true);
        webView.setClickable(false);
        webView.setFocusable(false);
        webView.setFocusableInTouchMode(false);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLightTouchEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        //webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);


        webView.setWebChromeClient(new WebChromeClient() {

            // this will be called on page loading progress

            @Override

            public void onProgressChanged(WebView view, int newProgress) {

                //super.onProgressChanged(view, newProgress);


                mProgress.setProgress(newProgress);

                // hide the progress bar if the loading is complete

                if (newProgress == 100) {

  				/* call after laoding splash.html  */
                    webView.loadUrl("javascript:_fully_loaded()");
                    webView.setVisibility(View.VISIBLE);
                    mProgress.setVisibility(View.GONE);

                }

            }

        });

        webView.setWebViewClient(new WebViewClient());

		/* load splash screen */
        webView.loadUrl("file:///android_asset/splash.html");


}
