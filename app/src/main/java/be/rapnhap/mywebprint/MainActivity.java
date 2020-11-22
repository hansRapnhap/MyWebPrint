package be.rapnhap.mywebprint;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Log Tag";

    private WebView mWebView;

    Button buttonOtherActivity, buttonMap, buttonWebsite, buttonViewIntent, buttonIntentPro;
    Button buttonWebPrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // web page - open with browser
        //openWebPage("http://www.rapnhap.be");

        // Intent and open other activities - example
        buttonOtherActivity = findViewById(R.id.buttonOtherActivity);
        buttonOtherActivity.setOnClickListener(view -> OnClick(view));
        buttonMap = findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(view -> OnClick(view));
        buttonWebsite = findViewById(R.id.buttonWebsite);
        buttonWebsite.setOnClickListener(view -> OnClick(view));
        buttonViewIntent = findViewById(R.id.buttonViewIntent);
        buttonViewIntent.setOnClickListener(view -> OnClick(view));
        buttonIntentPro = findViewById(R.id.buttonIntentPro);
        buttonIntentPro.setOnClickListener(view -> OnClick(view));

        // Web print example
        buttonWebPrint = findViewById(R.id.buttonWebPrint);
        buttonWebPrint.setOnClickListener(view -> OnClick(view));
        }


    // web print example
    // ----------------------------------------
    private void doWebViewPrint() {
        // Create a WebView object specifically for printing
        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "page finished loading " + url);
                createWebPrintJob(view);
                mWebView = null;
            }
        });
        // Generate an HTML document on the fly:
        String htmlDocument = "<html><body><h1>Rap n Hap</h1>"+
                "<table>" +

                "<tr><td>1</td><td> A Bieslook</td><td style=\"text-align: right;\">2,00</td></tr>" +
                "<tr><td>3</td><td> A Zalm</td><td style=\"text-align: right;\">9,00</td></tr>" +
                "<tr><td>1</td><td> B Champignon</td><td style=\"text-align: right;\">3,00</td></tr>" +
                "<tr><td>4</td><td> B Appel</td><td style=\"text-align: right;\">12,00</td></tr>" +
                "<tr><td> </td><td> </td><td style=\"text-align: right;\">------</td></tr>" +
                "<tr><td></td><td> <strong>TOTAAL</strong></td><td style=\"text-align: right;\">26,00</td></tr>" +

                "</table></body></html>";
        webView.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null);

        // Keep a reference to WebView object until you pass the PrintDocumentAdapter
        // to the PrintManager

        mWebView = webView;
    }
        private void createWebPrintJob(WebView webView) {

            // Get a PrintManager instance
            PrintManager printManager = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                printManager = (PrintManager) this
                        .getSystemService(Context.PRINT_SERVICE);
            }

            String jobName = getString(R.string.app_name) + " Document";

            // Get a print adapter instance
            PrintDocumentAdapter printAdapter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                printAdapter = webView.createPrintDocumentAdapter(jobName);
            }

            // Create a print job with name and adapter instance
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                PrintAttributes.Builder builder = new PrintAttributes.Builder();
                builder.setMediaSize(PrintAttributes.MediaSize.ISO_A4);
                builder.setColorMode(PrintAttributes.COLOR_MODE_MONOCHROME);
                PrintJob printJob = printManager.print(jobName, printAdapter,
                        builder.build());
            }

            // Save the job object for later status checking
            //printJobs.add(printJob);
        }




    // image print helper
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void OnClick(View view) {
        switch(view.getId()) {
            case R.id.buttonOtherActivity :
                Intent intent = new Intent(MainActivity.this, ExplicitActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonMap :
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:51.013909,3.658498"));
                startActivity(intent1);
                break;
            case R.id.buttonWebsite :
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.rapnhap.be"));
                startActivity(intent2);
                break;
            case R.id.buttonViewIntent :
                Intent intent3 = new Intent(Intent.ACTION_VIEW);
                // the data is needed in the other app !!
                intent3.putExtra("KEY", "Key value to pass");
                startActivity(intent3);
                break;
            case R.id.buttonIntentPro :
                Intent intent4 = new Intent("be.rapnhap.mywebprint.ExplicitActivity");
                intent4.putExtra("KEY", "Key value to pass");
                startActivity(intent4);
                break;
            case R.id.buttonWebPrint :
                doWebViewPrint();
                break;

        }
    }
}