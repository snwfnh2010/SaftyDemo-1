package com.example.a360safty.service;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;

import com.android.internal.telephony.ITelephony;

import com.example.a360safty.model.BlackNumberDao;

import java.lang.reflect.Method;

/**
 * Created by snwfnh on 2016/10/28.
 */
public class BlackNumberService extends Service {

    private InnerSmsReceiver mInnerSmsReceiver;
    private BlackNumberDao mDao;
    private TelephonyManager mTM;
    private MyPhoneStateListener mPhoneStateListener;
    private MyContentObserver mContentObserver;
    @Override
    public void onCreate() {
        mDao = BlackNumberDao.getInstance(getApplicationContext());


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.setPriority(1000);

        mInnerSmsReceiver = new InnerSmsReceiver();
        registerReceiver(mInnerSmsReceiver, intentFilter);


        mTM = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        mPhoneStateListener = new MyPhoneStateListener();
        mTM.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        super.onCreate();
    }

    class MyPhoneStateListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    endCall(incomingNumber);
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }
    class InnerSmsReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Object[] objects = (Object[]) intent.getExtras().get("pdus");

            for (Object object : objects) {

                SmsMessage sms = SmsMessage.createFromPdu((byte[])object);
                String originatingAddress = sms.getOriginatingAddress();
                String messageBody = sms.getMessageBody();

                int mode = mDao.getMode(originatingAddress);

                if(mode == 1 || mode == 3){
                    abortBroadcast();
                }
            }
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    public void endCall(String phone) {
        int mode = mDao.getMode(phone);

        if(mode == 2 || mode == 3){

            try {

                Class<?> clazz = Class.forName("android.os.ServiceManager");
                Method method = clazz.getMethod("getService", String.class);
                IBinder iBinder = (IBinder) method.invoke(null, Context.TELEPHONY_SERVICE);

                ITelephony iTelephony = ITelephony.Stub.asInterface(iBinder);


                iTelephony.endCall();
            } catch (Exception e) {
                e.printStackTrace();
            }

            mContentObserver = new MyContentObserver(new Handler(),phone);
            getContentResolver().registerContentObserver(
                    Uri.parse("content://call_log/calls"), true, mContentObserver);
        }
    }

    class MyContentObserver extends ContentObserver {
        private String phone;
        public MyContentObserver(Handler handler,String phone) {
            super(handler);
            this.phone = phone;
        }

        @Override
        public void onChange(boolean selfChange) {

            getContentResolver().delete(
                    Uri.parse("content://call_log/calls"), "number = ?", new String[]{phone});
            super.onChange(selfChange);
        }
    }
    @Override
    public void onDestroy() {

        if(mInnerSmsReceiver!=null){
            unregisterReceiver(mInnerSmsReceiver);
        }


        if(mContentObserver!=null){
            getContentResolver().unregisterContentObserver(mContentObserver);
        }


        if(mPhoneStateListener!=null){
            mTM.listen(mPhoneStateListener,PhoneStateListener.LISTEN_NONE);
        }
        super.onDestroy();
    }
}
