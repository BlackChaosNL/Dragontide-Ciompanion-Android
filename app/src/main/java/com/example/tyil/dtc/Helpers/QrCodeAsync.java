package com.example.tyil.dtc.Helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Asynchronously create a QR code of a string.
 */
public class QrCodeAsync extends AsyncTask<Void, Void, Void> {
    private Context ctx;
    private ImageView node;
    private String value;
    private Bitmap image;

    /**
     * Instantiate a QrCodeAsync object with all relevant information to make
     * it work in practice.
     *
     * @param ctx   The context the QrCode is generated in.
     * @param node  The node to put the generated QrCode image in.
     * @param value The String value to make a QrCode of.
     */
    public QrCodeAsync (Context ctx, ImageView node, String value) {
        this.ctx = ctx;
        this.node = node;
        this.value = value;
    }

    /**
     * Run the following code in the background.
     *
     * @param params
     * @return
     */
    @Override
    protected Void doInBackground(Void ...params) {
        this.image = new QrCode(this.ctx, value).make();

        return null;
    }

    /**
     * Run the following code directly after doInBackground finishes.
     *
     * @param aVoid
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        this.node.setImageBitmap(this.image);
    }
}
