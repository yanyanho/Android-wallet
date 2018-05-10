package com.seuercc.ctoken.util;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * <BR>
 *
 * @author c00373278
 * @version [v1.0.0.301, 2018/4/23]
 */


public class IOUtil {

    private static final String TAG = "IOUtil";

    public static String InputStream2String(InputStream is) {
        String result = "";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;

        try {
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            result = new String(out.toByteArray());
        } catch (IOException e) {
            Log.e(TAG, "Input stream 2 String error", e);
        } finally {
            closeSecure(out);
        }
        return result;
    }

    /**
     * 安全关闭Closeable对象
     *
     * @param closeable 接受null参数, 和已经关闭的.
     */
    public static void closeSecure(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e(TAG, "closeSecure IOException");
            }
        }
    }

}
