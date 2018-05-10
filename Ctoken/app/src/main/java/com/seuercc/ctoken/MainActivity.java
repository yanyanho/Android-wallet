package com.seuercc.ctoken;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.seuercc.ctoken.network.Wallet;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.create);
        final EditText editText = (EditText) findViewById(R.id.password);
        final String filePath = MainActivity.this.getFilesDir().getAbsolutePath();
        final EditText urlEdit = (EditText) findViewById(R.id.url);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ip = (TextUtils.isEmpty(urlEdit.getText().toString()) ? "192.168.1.4" : (urlEdit.getText().toString()));
                final String password = editText.getText().toString();
                final String file = filePath + File.separator + password + ".json";
                Log.i(TAG, "file is : " + file);
                Wallet temp = new Wallet();
                temp.createWallet(password, file, ip);
            }
        });
    }
}
