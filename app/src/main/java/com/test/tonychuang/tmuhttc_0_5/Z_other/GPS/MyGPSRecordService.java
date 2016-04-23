package com.test.tonychuang.tmuhttc_0_5.Z_other.GPS;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.HTTCJSONAPI;
import com.test.tonychuang.tmuhttc_0_5.Z_other.JSON.JSONParser;
import com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB.RowDataFormat.PsnLocRow;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by TonyChuang on 2016/4/21.
 */
public class MyGPSRecordService extends Service {

    private String TAG = "services";
    private boolean Flag = true;

    private Handler GPSInsertHandler;
    private Runnable GPSInsertRunnable;
    private static final int GSPINSERTTime = 60000; //正式:1分鐘、測試3秒

    private Handler GPSUploadHandler;
    private Runnable GPSUploadRunnable;
    private static final int GSPUploadTime = 60000; //正式:1分鐘、測試5秒
    private UpdateLocDataAsyTk updateLocDataAsyTk;
    private ArrayList<String> timeAry;
    private ArrayList<String> lngAry;
    private ArrayList<String> latAry;

    /**
     * GPS
     */
    private LocationManager locationMgr;
    public static String provider;
    private static final int GPSUpdateTime = 60000; //正式:1分鐘、測試3秒
    private double lng; //經度
    private double lat; //緯度
    private String datetimeString; //時間
    private String dateString; //日期
    private String timeString; //時間


    /**
     * ConnectionChangeReceiver 判斷網路狀態
     */
    private ConnectionChangeReceiver mNetworkStateReceiver;


    /**
     * 資料庫相關
     */
    private SignInShrPref signInShrPref;


    @Override
    public void onCreate() {
        // TODO 自動生成的方法存根
        super.onCreate();
        Log.v(TAG, "onCreate");

        //註冊網絡監聽 用來判斷何時要用哪個GPS接收器(AGPS優先，GPS其次)
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mNetworkStateReceiver = new ConnectionChangeReceiver();
        registerReceiver(mNetworkStateReceiver, filter);

        if (initLocationProvider()) {
            whereAmI();
        } else {
            Log.v(TAG, "請開啟定位");
        }

        signInShrPref = new SignInShrPref(getApplicationContext());

        timeAry = new ArrayList<>();
        lngAry = new ArrayList<>();
        latAry = new ArrayList<>();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO 自動生成的方法存根
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        GPSInsertHandler = new Handler();
        GPSInsertRunnable = new Runnable() {
            @Override
            public void run() {
                if (Flag) {
                    if (ActivityCompat.checkSelfPermission(MyGPSRecordService.this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(MyGPSRecordService.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Location location = locationMgr.getLastKnownLocation(provider);
                    updateWithNewLocation(location);

                    if (location != null) {
                        DataBase mainDB = LiteOrm.newSingleInstance(getApplicationContext(), signInShrPref.getAID());
                        ArrayList<PsnLocRow> psnLocRows = mainDB.query(new QueryBuilder<PsnLocRow>(PsnLocRow.class)
                                .where(new WhereBuilder(PsnLocRow.class)
                                        .greaterThan(PsnLocRow.PSNLOC_DATE, dateString)
                                        .and()
                                        .equals(PsnLocRow.PSNLOC_AID, signInShrPref.getAID())
                                        .or()
                                        .equals(PsnLocRow.PSNLOC_DATE, dateString)
                                        .and()
                                        .greaterThan(PsnLocRow.PSNLOC_TIME, timeString)
                                        .and()
                                        .equals(PsnLocRow.PSNLOC_AID, signInShrPref.getAID())
                                        .or()
                                        .equals(PsnLocRow.PSNLOC_DATE, dateString)
                                        .and()
                                        .equals(PsnLocRow.PSNLOC_TIME, timeString)
                                        .and()
                                        .equals(PsnLocRow.PSNLOC_AID, signInShrPref.getAID())));

                        if (psnLocRows.size() == 0) {
                            PsnLocRow psnLocRow = new PsnLocRow(signInShrPref.getAID(), 0L, dateString, timeString, lng, lat, "N");
                            mainDB.save(psnLocRow);
                        }
//                        //test
//                        long count = mainDB.queryCount(PsnLocRow.class);
//                        toast("總筆數 : " + count + "\n資料來源 : " + provider + "\n更新時間 : " + datetimeString);
//                        //test
                        LiteOrm.releaseMemory();
                        mainDB.close();
                    }
//                    //test
//                    toast("no Data");
//                    //test
                    GPSInsertHandler.postDelayed(this, GSPINSERTTime);
                } else {
                    LiteOrm.releaseMemory();
                    GPSInsertHandler.removeCallbacks(GPSInsertRunnable);
                }
            }
        };
        GPSInsertHandler.postDelayed(GPSInsertRunnable, GSPINSERTTime);//每GSPINSERTTime秒執行一次runnable.


        GPSUploadHandler = new Handler();
        GPSUploadRunnable = new Runnable() {
            @Override
            public void run() {
                if (Flag) {
                    DataBase mainDB = LiteOrm.newSingleInstance(getApplicationContext(), signInShrPref.getAID());
                    ArrayList<PsnLocRow> psnLocRows
                            = mainDB.query(new QueryBuilder<PsnLocRow>(PsnLocRow.class)
                            .where(new WhereBuilder(PsnLocRow.class)
                                    .equals(PsnLocRow.PSNLOC_AID, signInShrPref.getAID())
                                    .and()
                                    .equals(PsnLocRow.PSNLOC_UPLOADED_FLAG, "N"))
                            .appendOrderAscBy(PsnLocRow.ID));
                    LiteOrm.releaseMemory();
                    mainDB.close();
                    if (psnLocRows.size() != 0) {
                        timeAry.clear();
                        lngAry.clear();
                        latAry.clear();
                        for (int i = 0; i < psnLocRows.size(); i++) {
                            timeAry.add(psnLocRows.get(i).getPsnLoc_date() + " " + psnLocRows.get(i).getPsnLoc_time());
                            lngAry.add(String.valueOf(psnLocRows.get(i).getPsnLoc_longitude()));
                            latAry.add(String.valueOf(psnLocRows.get(i).getPsnLoc_latitude()));
                        }
                        updateLocDataAsyTk = new UpdateLocDataAsyTk(psnLocRows);
                        updateLocDataAsyTk.execute(signInShrPref.getAID(), timeAry, lngAry, latAry);
                    }
                    GPSUploadHandler.postDelayed(this, GSPUploadTime);
                } else {
                    GPSUploadHandler.removeCallbacks(GPSUploadRunnable);
                }
            }
        };
        GPSUploadHandler.postDelayed(GPSUploadRunnable, GSPUploadTime);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //沒有手動調stopService,不會自己關閉
        super.onDestroy();
        //關閉監聽網路狀態
        unregisterReceiver(mNetworkStateReceiver);
        Flag = false;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationMgr.removeUpdates(locationListener);
        Log.v(TAG, "onDestroy");
    }


    /************************************************
     *
     * 						GPS部份
     *
     ***********************************************/

    /**
     * GPS初始化，取得可用的位置提供器
     *
     * @return
     */
    private boolean initLocationProvider() {
        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

////      1.選擇最佳提供器
//		Criteria criteria = new Criteria();
//		criteria.setAccuracy(Criteria.ACCURACY_FINE);
//		criteria.setAltitudeRequired(false);
//		criteria.setBearingRequired(false);
//		criteria.setCostAllowed(true);
//		criteria.setPowerRequirement(Criteria.POWER_LOW);
//
//		provider = locationMgr.getBestProvider(criteria, true);
//
//		if (provider != null) {
//			return true;
//		}


////        2.選擇使用GPS提供器
//        if (locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            provider = LocationManager.GPS_PROVIDER;
//            return true;
//        }


////        3.選擇使用網路提供器
//		if (locationMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//			provider = LocationManager.NETWORK_PROVIDER;
//			return true;
//		}

//        //4.第一次開啟定位設定時，優先選擇網路提供器
//        if (checkNetworkConnected() && locationMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//            provider = LocationManager.NETWORK_PROVIDER;
//            return true;
//        } else {
//            if (locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                provider = LocationManager.GPS_PROVIDER;
//                return true;
//            }
//        }

        if (locationMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (checkNetworkConnected()) {
                provider = LocationManager.NETWORK_PROVIDER;
                return true;
            } else {
                provider = LocationManager.GPS_PROVIDER;
                return true;
            }
        }
        return false;
    }

    /**
     * 執行"我"在哪裡
     * 1.建立位置改變偵聽器
     * 2.預先顯示上次的已知位置
     */
    private void whereAmI() {
        //取得上次已知的位置
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationMgr.getLastKnownLocation(provider);
        updateWithNewLocation(location);

        //GPS Listener
        locationMgr.addGpsStatusListener(gpsListener);


        //Location Listener
        long minTime = GPSUpdateTime;//ms (多久修改一次GPS容器資料)
        float minDist = 0.0f;//meter (與上次距離差距多遠，修改一次GPS容器資料)
        locationMgr.requestLocationUpdates(provider, minTime, minDist, locationListener);
    }

    /**
     * 更新並顯示新位置
     *
     * @param location
     */
    private void updateWithNewLocation(Location location) {
        String where;
        if (location != null) {
            //經度
            lng = location.getLongitude();
            //緯度
            lat = location.getLatitude();
            //速度
            float speed = location.getSpeed();
            //時間
            long time = location.getTime();
            datetimeString = getTimeString(time);
            dateString = datetimeString.substring(0, 10);
            timeString = datetimeString.substring(11, 16);

            where = "經度: " + lng +
                    "\n緯度: " + lat +
                    "\n速度: " + speed +
                    "\n時間: " + datetimeString +
                    "\nProvider: " + provider;

            Log.v(TAG, where);
        } else {
            where = "No location found.";
            Log.v(TAG, where);
        }

        //位置改變顯示
//        txtOutput.setText(where);
    }

    private String getTimeString(long timeInMilliseconds) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.TAIWAN);
        return format.format(timeInMilliseconds);
    }

    GpsStatus.Listener gpsListener = new GpsStatus.Listener() {

        @Override
        public void onGpsStatusChanged(int event) {
            switch (event) {
                case GpsStatus.GPS_EVENT_STARTED:
                    Log.d(TAG, "GPS_EVENT_STARTED");
                    break;

                case GpsStatus.GPS_EVENT_STOPPED:
                    Log.d(TAG, "GPS_EVENT_STOPPED");
                    break;

                case GpsStatus.GPS_EVENT_FIRST_FIX:
                    Log.d(TAG, "GPS_EVENT_FIRST_FIX");
                    break;

                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                    Log.d(TAG, "GPS_EVENT_SATELLITE_STATUS");
                    break;
            }
        }
    };

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.OUT_OF_SERVICE:
                    Log.v(TAG, "Status Changed: Out of Service");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.v(TAG, "Status Changed: Temporarily Unavailable");
                    break;
                case LocationProvider.AVAILABLE:
                    Log.v(TAG, "Status Changed: Available");
                    break;
            }
        }
    };

    private class UpdateLocDataAsyTk extends AsyncTask<Object, Void, Boolean> {
        private ArrayList<PsnLocRow> psnLocRows;

        public UpdateLocDataAsyTk(ArrayList<PsnLocRow> psnLocRows) {
            this.psnLocRows = psnLocRows;
        }

        @Override
        protected Boolean doInBackground(Object... params) {
            HTTCJSONAPI httcjsonapi = new HTTCJSONAPI();
            JSONParser jsonParser = new JSONParser();
            Boolean aBoolean = false;

            JSONObject jsonObject;
            try {
                jsonObject = httcjsonapi.UpdateLocData((String) params[0]
                        , (ArrayList<String>) params[1]
                        , (ArrayList<String>) params[2]
                        , (ArrayList<String>) params[3]);
                aBoolean = jsonParser.parseBoolean(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return aBoolean;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                DataBase mainDB = LiteOrm.newSingleInstance(getApplicationContext(), signInShrPref.getAID());
                HashMap<String, Object> uploadedFlagMap = new HashMap<>();
                uploadedFlagMap.put(PsnLocRow.PSNLOC_UPLOADED_FLAG, "Y");
                mainDB.update(psnLocRows, new ColumnsValue(uploadedFlagMap), ConflictAlgorithm.Fail);
                LiteOrm.releaseMemory();
                mainDB.close();
            }
        }
    }

    //偵測是否有連到網路上
    private boolean checkNetworkConnected() {
        boolean result = false;
        ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (CM == null) {
            result = false;
        } else {
            NetworkInfo info = CM.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (!info.isAvailable()) {
                    result = false;
                } else {
                    result = true;
                }
                Log.d(TAG, "[目前連線方式]" + info.getTypeName());
                Log.d(TAG, "[目前連線狀態]" + info.getState());
                Log.d(TAG, "[目前網路是否可使用]" + info.isAvailable());
                Log.d(TAG, "[網路是否已連接]" + info.isConnected());
                Log.d(TAG, "[網路是否已連接 或 連線中]" + info.isConnectedOrConnecting());
                Log.d(TAG, "[網路目前是否有問題 ]" + info.isFailover());
                Log.d(TAG, "[網路目前是否在漫遊中]" + info.isRoaming());
            }
        }
        return result;
    }

    private void toast(final String msg) {
        /*測試用toast*/
        Handler toastHandler = new Handler(Looper.getMainLooper());
        toastHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //用來接收網路連線狀態
    public class ConnectionChangeReceiver extends BroadcastReceiver {
        private String TAG = "ConnectionChangeReceiver";

        public ConnectionChangeReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v(TAG, "網絡狀態改變");

            boolean success = false;

            ConnectivityManager CM = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (CM == null) {
                success = false;
            } else {
                NetworkInfo info = CM.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (!info.isAvailable()) {
                        success = false;
                    } else {
                        success = true;
                    }
                }
            }

            if (ActivityCompat.checkSelfPermission(MyGPSRecordService.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(MyGPSRecordService.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            if (success) {
                MyGPSRecordService.provider = LocationManager.NETWORK_PROVIDER;
                locationMgr.removeUpdates(locationListener);
                locationMgr.requestLocationUpdates(provider, GPSUpdateTime, 0.0f, locationListener);
            } else {
                MyGPSRecordService.provider = LocationManager.GPS_PROVIDER;
                locationMgr.removeUpdates(locationListener);
                locationMgr.requestLocationUpdates(provider, GPSUpdateTime, 0.0f, locationListener);
            }
        }
    }
}