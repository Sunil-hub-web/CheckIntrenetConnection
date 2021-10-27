package com.example.checkintrenetconnection;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class notconnected extends AppCompatActivity {

    boolean isConnected;
    Button try_again;
    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notconnected);
        try_again = findViewById(R.id.try_again_btn);
        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isConnected){

                    Intent i = new Intent(notconnected.this,MainActivity.class);
                    startActivity(i);
                    finish();


                }else   {

                    Toast.makeText(notconnected.this,"Not Connected",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @SuppressLint("NewApi")
    private void registerNetworkCallback(){


        try {

            connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){

                @Override
                public void onAvailable(@NonNull Network network) {
                    isConnected = true;
                }

                @Override
                public void onLost(@NonNull Network network) {
                    isConnected = false;
                }
            });




        }catch (Exception e){

            isConnected = false;

        }

    }

    @SuppressLint("NewApi")
    private void unregisterNetworkCallback(){

        connectivityManager.unregisterNetworkCallback(new ConnectivityManager.NetworkCallback());

    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        registerNetworkCallback();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        super.onPause();
        unregisterNetworkCallback();
    }

}