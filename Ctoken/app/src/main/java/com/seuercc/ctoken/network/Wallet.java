package com.seuercc.ctoken.network;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/22.
 */
public class Wallet {

    private static final String TAG = "WalletBean";

    public void createWallet(final String password, String fileName, String ip) {

        final String url = "http://" + ip + "/new-wallet/" + password;
        Log.i(TAG, "url is : " + url);
        final File file = new File(fileName);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (file.exists()) {
                        return;
                    } else {
                        file.createNewFile();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .build();//创建Request 对象

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onResponse(Call arg0, Response response) throws IOException {
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
                    }

                    @Override
                    public void onFailure(Call arg0, IOException arg1) {
                        Log.e(TAG, "create wallet failed");
                    }
                });

            }
        }).start();
    }

    public void getBalance(String address, final String ip) {

        final String url = "http://" + ip + '/' + address + "/balance";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    Request request = new Request.Builder()
                            .url(url)//请求接口。如果需要传参拼接到接口后面。
                            .build();//创建Request 对象
                    Response response = null;
                    response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        Log.d("kwwl", "response.code()==" + response.code());
                        Log.d("kwwl", "response.message()==" + response.message());
                        Log.d("kwwl", "res==" + response.body().string());

                        //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
