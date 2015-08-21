package com.suxihui.demo_sms;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private final static String TAG = MainActivity.class.getSimpleName();

    private static final String SMS_URI_ALL = "content://sms/";
    private static final String SMS_URI_INBOX = "content://sms/inbox";
    private static final String SMS_URI_SEND = "content://sms/sent";
    private static final String SMS_URI_DRAFT = "content://sms/draft";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<CallLogBean> callLogBeans = checkLogs();
        Log.i(TAG,"----callLogBeans = " + callLogBeans);

        List<SMSBean> smsBeans = checkSMS();
        Log.i(TAG, "----smsBeans = " + smsBeans);
    }

    /** 查询与该用户通话记录 **/
    public List<CallLogBean> checkLogs() {
        List<CallLogBean> callLogBeans = new ArrayList<CallLogBean>();

        Uri uri = CallLog.Calls.CONTENT_URI;
        String[] projection = new String[]{
                CallLog.Calls.NUMBER,
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE
        };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);

        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                String cachedName = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                String type = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));
                String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));

                CallLogBean bean = new CallLogBean(number,cachedName,type,date);
                callLogBeans.add(bean);
            }
        }

        cursor.close();
        return callLogBeans;
    }

    /** 查询与该用户短信记录 **/
    public List<SMSBean> checkSMS() {
        List<SMSBean> smsBeans = new ArrayList<SMSBean>();


        final String SMS_URI_ALL = "content://sms/";
        Uri uri = Uri.parse(SMS_URI_ALL);
        String[] projection = new String[]{"address","person","date","type","body"};

        Cursor cursor = getContentResolver().query(uri, projection,null,null,"date desc");

        if(cursor.moveToFirst()){
            while (cursor.moveToNext()) {
//                String person = cursor.getString(cursor.getColumnIndex("person"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String body = cursor.getString(cursor.getColumnIndex("body"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String type = cursor.getString(cursor.getColumnIndex("type"));

                String name = getContactByAddr(address);

                SMSBean smsBean = new SMSBean(address,name,date,type,body);
                smsBeans.add(smsBean);
            }
        }
        cursor.close();
        return smsBeans;
    }

    //通过地址找到用户名
    private String getContactByAddr( String address) {
        String name = null;
        Uri personUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI, address);
        Cursor cursor = getContentResolver().query(personUri,
                new String[] { ContactsContract.PhoneLookup.DISPLAY_NAME },
                null, null, null );
        if( cursor.moveToFirst() ) {
            int nameIdx = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            name = cursor.getString(nameIdx);
        }
        cursor.close();
        return name;
    }
}
