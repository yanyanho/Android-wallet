package com.seuercc.ctoken.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seuercc.ctoken.R;
import com.seuercc.ctoken.network.EtherscanAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CreateWalletActivity extends AppCompatActivity {

    private static final String TAG = "CreateWalletActivity";
    private EditText urlEdit;
    private EditText editText;
    private String filePath;

    /**
     * 弹框提示
     *
     * @param activity
     */
    public static void writeDownPassword(final CreateWalletActivity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Important");
        builder.setMessage("This will encrypt your wallet with the password your provided to keep your ether safe.");
        builder.setPositiveButton("Generate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.genWallet(activity);
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wallet);

        Button button;
        button = (Button) findViewById(R.id.create);
        editText = (EditText) findViewById(R.id.password);
        filePath = CreateWalletActivity.this.getFilesDir().getAbsolutePath();
        urlEdit = (EditText) findViewById(R.id.url);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeDownPassword(CreateWalletActivity.this);
            }
        });
    }

    /**
     * 创建钱包
     */
    private void genWallet(final Context context) {
        final String ip = (TextUtils.isEmpty(urlEdit.getText().toString()) ? "192.168.203.233:8888" : (urlEdit.getText().toString()));
        final String password = editText.getText().toString();
        final String file = filePath + File.separator + password + ".json";
        Log.i(TAG, "file is : " + file);

        final String url = "http://" + ip + "/new-wallet/" + password;

        try {
            EtherscanAPI.getInstance().get(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i(TAG, "create wallet failed");
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    byte[] buf = new byte[2048];
                    int len = 0;
                    InputStream is = response.body().byteStream();
                    FileOutputStream fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    Log.i(TAG, "create wallet succed");
                    Toast.makeText(context, "Succeed", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
