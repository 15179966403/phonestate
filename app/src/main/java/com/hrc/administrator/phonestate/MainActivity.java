package com.hrc.administrator.phonestate;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager tm=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        MyPhoneCallListener phoneCallListener=new MyPhoneCallListener();
        tm.listen(phoneCallListener,PhoneStateListener.LISTEN_CALL_STATE);
    }

    public class MyPhoneCallListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            AudioManager audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(MainActivity.this,"通话中....", Toast.LENGTH_LONG).show();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    if("12345678".equals(incomingNumber)){
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    }
                    break;
            }
            super.onCallStateChanged(state,incomingNumber);
        }
    }
}
