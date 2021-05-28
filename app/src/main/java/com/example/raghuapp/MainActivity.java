package com.example.raghuapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.raghuapp.foregroundservices.MyForegroundService;

import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Button startServiceBt,stopServiceBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceBt = findViewById(R.id.button);
        stopServiceBt = findViewById(R.id.button2);
        startServiceBt.setOnClickListener(this);
        stopServiceBt.setOnClickListener(this);

        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        // Phone does not support Bluetooth so let the user know and exit.
        if (defaultAdapter == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Not compatible")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }


        if (!defaultAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, 100);
        }



    }

    @Override
    public void onClick(View v) {
        if(v == startServiceBt){
            startService(new Intent(getApplicationContext(), MyForegroundService.class));
        }
        else if(v == stopServiceBt){
            stopService(new Intent(getApplicationContext(), MyForegroundService.class));
        }
    }

}