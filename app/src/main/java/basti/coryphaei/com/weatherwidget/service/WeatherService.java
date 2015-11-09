package basti.coryphaei.com.weatherwidget.service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.LocationManagerProxy;

import basti.coryphaei.com.weatherwidget.R;

/**
 * Created by Bowen on 2015-11-09.
 */
public class WeatherService extends Service implements AMapLocalWeatherListener{

    private LocationManagerProxy mLocationManagerProxy;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {

        mLocationManagerProxy = LocationManagerProxy.getInstance(this);
        mLocationManagerProxy.requestWeatherUpdates(
                LocationManagerProxy.WEATHER_TYPE_LIVE, this);

    }

    @Override
    public void onWeatherLiveSearched(AMapLocalWeatherLive aMapLocalWeatherLive) {
        Log.i("start","start");
        if(aMapLocalWeatherLive!=null && aMapLocalWeatherLive.getAMapException().getErrorCode() == 0){
                String city = aMapLocalWeatherLive.getCity();//城市
                String weather = aMapLocalWeatherLive.getWeather();//天气情况
                String windDir = aMapLocalWeatherLive.getWindDir();//风向
                String windPower = aMapLocalWeatherLive.getWindPower();//风力
                String humidity = aMapLocalWeatherLive.getHumidity();//空气湿度
            String reportTime = aMapLocalWeatherLive.getReportTime();//数据发布时间
            Log.i("start","start1");
            upDateView(weather);
        }else{
            // 获取天气预报失败
            Log.i("start","start2");
            Toast.makeText(this, "获取天气预报失败:" + aMapLocalWeatherLive.getAMapException().getErrorMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void upDateView(String weather) {

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.weather);
        remoteViews.setTextViewText(R.id.weather,weather);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());

        ComponentName name = new ComponentName(getApplication(),WeatherService.class);

        appWidgetManager.updateAppWidget(name,remoteViews);


    }

    @Override
    public void onWeatherForecaseSearched(AMapLocalWeatherForecast aMapLocalWeatherForecast) {

    }
}
