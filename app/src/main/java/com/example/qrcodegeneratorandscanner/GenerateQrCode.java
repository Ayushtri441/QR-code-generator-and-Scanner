package com.example.qrcodegeneratorandscanner;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQrCode extends AppCompatActivity {
    TextView qrcode;
    ImageView img;
    EditText dataedt;
    Button genqr;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr_code);
        qrcode = findViewById(R.id.gentxt);
        img = findViewById(R.id.frameimg);
        dataedt = findViewById(R.id.TILID);
        genqr = findViewById(R.id.btngen);
        genqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = dataedt.getText().toString();
                if(data.isEmpty()){
                    Toast.makeText(GenerateQrCode.this,"Please enter some text to make QR code..",Toast.LENGTH_LONG).show();
                }
                else {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height=  point.y;
                    int dim = width<height? width:height;
                    dim = dim*3/4;
                    qrgEncoder = new QRGEncoder(dataedt.getText().toString(), null, QRGContents.Type.TEXT, dim);
                    try {

                        bitmap = qrgEncoder.encodeAsBitmap();
                        img.setImageBitmap(bitmap);
                        qrcode.setText("");

                    } catch (WriterException e) {

                        Log.e("Tag", e.toString());
                    }

                }
            }
        });

    }
}