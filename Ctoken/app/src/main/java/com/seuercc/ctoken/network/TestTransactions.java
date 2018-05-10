package com.seuercc.ctoken.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TestTransactions {
    private static final String TAG = "TestTransactions";

    public void transactions() {
        final String address = "0x9cdAFb867CE63611e980c9B71C30ae496B0e8Ce2";
        try {
            EtherscanAPI.getInstance().getNormalTransactions(address, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    if (response != null && response.body() != null) {
                        String restring = response.body().string();

                        if (restring != null && restring.length() > 2) {
                            Log.i(TAG, "Response is : " + restring);
                            RequestCache.getInstance().put(RequestCache.TYPE_TXS_NORMAL, address, restring);
                        }
                    }
                }
            }, true);
            EtherscanAPI.getInstance().getInternalTransactions(address, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String restring = response.body().string();
                    if (restring != null && restring.length() > 2) {
                        Log.i(TAG, "Response is : " + restring);
                        RequestCache.getInstance().put(RequestCache.TYPE_TXS_INTERNAL, address, restring);
                    }

                }
            }, true);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
