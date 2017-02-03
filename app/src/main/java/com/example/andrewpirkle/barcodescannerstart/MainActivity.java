package com.example.andrewpirkle.barcodescannerstart;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = (Button) findViewById(R.id.scan_button);
        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.scan_button) {
            //scan away
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            // we have results!!!
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: "+ scanFormat);
            contentTxt.setText("CONTENT: "+ scanContent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

