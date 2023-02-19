package com.dma.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.circularprogressbar.CircularProgressBar;


public class MainActivity extends AppCompatActivity {

    //CircularProgressBar circularProgressBar;
    CircularProgressBar progressBar;
    private AppCompatButton btnCapture;
    private AppCompatImageView imgShow;
    public static final int RequestPermissionCode = 1;
    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //circularProgressBar = findViewById(R.id.circularProgressBar);
        progressBar = findViewById(R.id.progress_bar);
        btnCapture = findViewById(R.id.btnCapture);
        imgShow = findViewById(R.id.imgShow);

        EnableRuntimePermission();

        btnCapture.setOnClickListener(v -> {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 7);
        });

        /*final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void run() {
                showProgress(value);
                handler.postDelayed(this, 1000);
                value = value + 10;
            }
        }, 1000);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgShow.setImageBitmap(bitmap);
        }
    }
    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                Manifest.permission.CAMERA)) {
            Toast.makeText(MainActivity.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        super.onRequestPermissionsResult(requestCode, permissions, result);
        switch (requestCode) {
            case RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void showProgress(int val) {

        progressBar.setProgress(val);
        /*// Set Progress
        circularProgressBar.setProgress(val);
        // or with animation
        circularProgressBar.setProgressWithAnimation(val, 1000L); // =1s

        // Set Progress Max
        circularProgressBar.setProgressMax(200f);

        // Set ProgressBar Color
        circularProgressBar.setProgressBarColor(Color.BLACK);
        // or with gradient
        circularProgressBar.setProgressBarColorStart(Color.GRAY);
        circularProgressBar.setProgressBarColorEnd(Color.RED);
        circularProgressBar.setProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);

        // Set background ProgressBar Color
        circularProgressBar.setBackgroundProgressBarColor(Color.GRAY);
        // or with gradient
        circularProgressBar.setBackgroundProgressBarColorStart(Color.WHITE);
        circularProgressBar.setBackgroundProgressBarColorEnd(Color.RED);
        circularProgressBar.setBackgroundProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);

        // Set Width
        circularProgressBar.setProgressBarWidth(7f); // in DP
        circularProgressBar.setBackgroundProgressBarWidth(3f); // in DP

        // Other
        circularProgressBar.setRoundBorder(true);
        circularProgressBar.setStartAngle(180f);
        circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_RIGHT);

        circularProgressBar.setOnIndeterminateModeChangeListener(isEnable -> {
            // Do something
            return Unit.INSTANCE;
        });

        circularProgressBar.setOnProgressChangeListener(progress -> {
            // Do something
            return Unit.INSTANCE;
        });*/

        if (val == 100){
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        }
    }
}