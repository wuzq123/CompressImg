package com.wzq.compressimg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("bitherjni");
//    }



    private ImageView image;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.img);
        text = (TextView) findViewById(R.id.text);
    }

    public void testJpeg(View view) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int quality = 90;
                    InputStream in = getResources().getAssets()
                            .open("test.jpg");
                    Bitmap bit = BitmapFactory.decodeStream(in);
                    File dirFile = getExternalCacheDir();
                    if (!dirFile.exists()) {
                        dirFile.mkdirs();
                    }
                    File originalFile = new File(dirFile, "original.jpg");
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            originalFile);
                    bit.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
                    final File jpegTrueFile = new File(dirFile, "jpegtrue.jpg");
                    File jpegFalseFile = new File(dirFile, "jpegfalse.jpg");
                    NativeUtil.compressBitmap(bit, quality,
                            jpegTrueFile.getAbsolutePath(), true);
                    NativeUtil.compressBitmap(bit, quality,
                            jpegFalseFile.getAbsolutePath(), false);
                    if(bit != null && !bit.isRecycled())
                        bit.recycle();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            image.setImageBitmap(BitmapFactory.decodeFile(jpegTrueFile.getAbsolutePath()));
                            text.setText(jpegTrueFile.getAbsolutePath());

                        }
                    });

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
