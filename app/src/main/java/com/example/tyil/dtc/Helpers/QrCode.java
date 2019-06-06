package com.example.tyil.dtc.Helpers;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.tyil.dtc.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QrCode {
    private String value;
    private Context ctx;
    private Integer width = 500;
    private Integer height = 500;

    public QrCode(Context ctx, String value) {
        this.ctx = ctx;
        this.value = value;
    }

    public Bitmap make() {
        try {
            BitMatrix matrix;

            matrix = new MultiFormatWriter().encode(
                    this.value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    this.width,
                    this.height,
                    null
            );

            int[] pixels = new int[matrix.getWidth() * matrix.getHeight()];

            for (int y = 0; y < matrix.getHeight(); y++) {
                int offset = y * matrix.getWidth();

                for (int x = 0; x < matrix.getWidth(); x++) {
                    pixels[offset + x] = matrix.get(x, y)
                            ? ctx.getResources().getColor(R.color.qrPrimary)
                            : ctx.getResources().getColor(R.color.qrSecondary)
                            ;
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(
                    matrix.getWidth(),
                    matrix.getHeight(),
                    Bitmap.Config.ARGB_8888
            );

            bitmap.setPixels(
                    pixels,
                    0,
                    500,
                    0,
                    0,
                    matrix.getWidth(),
                    matrix.getHeight()
            );

            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();

            return null;
        }
    }
}
