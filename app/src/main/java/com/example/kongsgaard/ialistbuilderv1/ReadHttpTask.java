package com.example.kongsgaard.ialistbuilderv1;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * Created by magiskebob on 07-04-2017.
 */

public class ReadHttpTask extends AsyncTask<String, Void, CharSequence> {
    @Override
    protected CharSequence doInBackground(String... urls) {
        String urlString = urls[0];
        try {
            CharSequence result = HttpHelper.GetHttpResponse(urlString);
            return result;
        } catch (IOException ex) {
            cancel(true);
            String errorMessage = ex.getMessage() + "\n" + urlString;
            Log.e("Dish", errorMessage);
            return errorMessage;
        }
    }
}
