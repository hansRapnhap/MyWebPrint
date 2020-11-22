package be.rapnhap.mywebprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonOtherActivity, buttonMap, buttonWebsite, buttonViewIntent, buttonIntentPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //openWebPage("http://www.rapnhap.be");

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
    }

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
        }
    }
}