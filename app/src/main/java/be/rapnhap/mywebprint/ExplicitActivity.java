package be.rapnhap.mywebprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class ExplicitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        Toast.makeText(this, "THIS is called from main activity", Toast.LENGTH_SHORT).show();

        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString("KEY");
        Toast.makeText(this, "KEY " + str, Toast.LENGTH_SHORT).show();

    }
}