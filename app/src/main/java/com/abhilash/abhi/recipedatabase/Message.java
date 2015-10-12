package com.abhilash.abhi.recipedatabase;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by abhi on 10/11/15.
 */
public class Message {
    public static void message(Context context,String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
