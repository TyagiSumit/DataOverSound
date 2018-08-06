package io.chirp.connectdemoapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectLicenceListener;
import io.chirp.connect.models.ChirpError;
import io.chirp.connect.models.ConnectState;


public class MainActivity extends AppCompatActivity {

    private  String LICENCE="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";;
    private ChirpConnect chirpConnect;

    private static final int RESULT_REQUEST_RECORD_AUDIO = 1;

    TextView status;
    TextView lastChirp;
    TextView versionView;
    EditText chirp;

    Button startStopSendingBtn;

    Boolean startStopSdkBtnPressed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View parentLayout = findViewById(android.R.id.content);

        status = (TextView) findViewById(R.id.stateValue);
        lastChirp = (TextView) findViewById(R.id.lastChirp);
        versionView = (TextView) findViewById(R.id.versionView);

        startStopSendingBtn = (Button) findViewById(R.id.startStopSengingBtn);
        chirp=findViewById(R.id.editText);
        startStopSendingBtn.setAlpha(.4f);



        versionView.setText("Sumit Tyagi");

        String APP_KEY = "5981e1deDea3AcaAcCfFfC300";
        String APP_SECRET = "30aDB5fc7c20f5da55F2Adf5CaD7F3bA0FB2Ff59A3e33bA13B";

        /**
         * Key and secret initialisation
         */
        chirpConnect = new ChirpConnect(this, APP_KEY, APP_SECRET);

        ChirpError setLicenceError = chirpConnect.setLicence(LICENCE);
        if (setLicenceError.getCode() > 0) {
            Log.e("ChirpError:", setLicenceError.getMessage());
        }

        /*
         * Set-up the connect callbacks
         */
        chirpConnect.setListener(new ConnectEventListener() {

            @Override
            public void onSending(byte[] data) {
            	/**
		         * onSending is called when a send event begins.
		         * The data argument contains the payload being sent.
		         */
                String hexData = "null";
                if (data != null) {
                    hexData = chirpConnect.payloadToHexString(data);
                }
                Log.v("connectdemoapp", "ConnectCallback: onSending: " + hexData);
                //updateLastPayload(hexData);
                updateLastPayload(data);
            }

            @Override
            public void onSent(byte[] data) {
            	/**
		         * onSent is called when a send event has completed.
		         * The data argument contains the payload that was sent.
		         */
                String hexData = "null";
                if (data != null) {
                    hexData = chirpConnect.payloadToHexString(data);
                }
                //updateLastPayload(hexData);
                updateLastPayload(data);
                Log.v("connectdemoapp", "ConnectCallback: onSent: " + hexData);
            }

            @Override
            public void onReceiving() {
            	/**
		         * onReceiving is called when a receive event begins.
		         * No data has yet been received.
		         */
                Log.v("connectdemoapp", "ConnectCallback: onReceiving");
            }

            @Override
            public void onReceived(byte[] data) {
            	/**
		         * onReceived is called when a receive event has completed.
		         * If the payload was decoded successfully, it is passed in data.
		         * Otherwise, data is null.
		         */
                String hexData = "null";
                if (data != null) {
                    hexData = chirpConnect.payloadToHexString(data);
                }
                Log.v("connectdemoapp", "ConnectCallback: onReceived: " + hexData);
                //updateLastPayload(hexData);
                updateLastPayload(data);
            }

            @Override
            public void onStateChanged(byte oldState, byte newState) {
            	/**
		         * onStateChanged is called when the SDK changes state.
		         */
            	ConnectState state = ConnectState.createConnectState(newState);
                Log.v("connectdemoapp", "ConnectCallback: onStateChanged " + oldState + " -> " + newState);
                if (state == ConnectState.ConnectNotCreated) {
                    updateStatus("NotCreated");
                } else if (state == ConnectState.AudioStateStopped) {
                    updateStatus("Stopped");
                } else if (state == ConnectState.AudioStatePaused) {
                    updateStatus("Paused");
                } else if (state == ConnectState.AudioStateRunning) {
                    updateStatus("Running");
                } else if (state == ConnectState.AudioStateSending) {
                    updateStatus("Sending");
                } else if (state == ConnectState.AudioStateReceiving) {
                    updateStatus("Receiving");
                } else {
                    updateStatus(newState + "");
                }

            }

            @Override
            public void onSystemVolumeChanged(int oldVolume, int newVolume) {
                /**
                 * onSystemVolumeChanged is called when the system volume is changed.
                 */
                Snackbar snackbar = Snackbar.make(parentLayout, "System volume has been changed to: " + newVolume, Snackbar.LENGTH_LONG);
                snackbar.setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                        .show();
                Log.v("connectdemoapp", "System volume has been changed, notify user to increase the volume when sending data");
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO}, RESULT_REQUEST_RECORD_AUDIO);
        }
        else {
            if (startStopSdkBtnPressed) startSdk();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RESULT_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    /*if (startStopSdkBtnPressed) stopSdk();*/
                }
                return;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        chirpConnect.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            chirpConnect.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        /*stopSdk();*/
    }

    public void updateStatus(final String newStatus) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                status.setText(newStatus);
            }
        });
    }

    public void updateLastPayload(final byte[] data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                try {
                    lastChirp.setText(new String(data, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*public void stopSdk() {
        ChirpError error = chirpConnect.stop();
        if (error.getCode() > 0) {
            Log.e("ConnectError: ", error.getMessage());
            return;
        }
        startStopSendingBtn.setAlpha(.4f);
       // startStopSendingBtn.setClickable(false);
        startStopSdkBtn.setText("Start Sdk");
    }*/

    public void startSdk() {
        ChirpError error = chirpConnect.start();
        if (error.getCode() > 0) {
            Log.e("ConnectError: ", error.getMessage());
            return;
        }
        startStopSendingBtn.setAlpha(1f);
        startStopSendingBtn.setClickable(true);

    }
/*
    public void startStopSdk(View view) {
        *//**
         * Start or stop the SDK.
         * Audio is only processed when the SDK is running.
         *//*ChirpError error = chirpConnect.start();
        if (error.getCode() > 0) {
            Log.e("ConnectError: ", error.getMessage());
            return;
        }
        startStopSdkBtnPressed = true;
        if (chirpConnect.getConnectState() == ConnectState.AudioStateStopped) {
            startSdk();
        } else {
            stopSdk();
        }
    }*/

    public void sendPayload(View view) {
    	/**
         * A payload is a byte array dynamic size with a maximum size defined by the licence settings.
         * 
         * Generate a random payload, and send it.
         */
    	String chirps=chirp.getText().toString();
    	if(chirps.equals("")){
    	    chirp.setError("Please chrip!");
        }else{
            if(chirps.length()<=32){
                long maxPayloadLength = chirpConnect.getMaxPayloadLength();

                long size = (long) new Random().nextInt((int) maxPayloadLength) + 1;

                //byte[] payload = chirpConnect.randomPayload(size);

                long maxSize = chirpConnect.getMaxPayloadLength();
                byte [] payload=chirps.getBytes();
                if (maxSize < payload.length) {
                    Log.e("ConnectError: ", "Invalid Payload");
                    return;
                }
                ChirpError error = chirpConnect.send(payload);
                if (error.getCode() > 0) {
                    Log.e("ConnectError: ", error.getMessage());
                }
                chirp.setText("");
            }
            else{
                chirp.setError("Please chirp less");
            }
        }
    }

}
