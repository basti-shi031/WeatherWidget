package basti.coryphaei.com.weatherwidget.provider;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import basti.coryphaei.com.weatherwidget.service.WeatherService;

/**
 * Created by Bowen on 2015-11-09.
 */
public class WeatherProvider extends AppWidgetProvider{

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

        Intent intent = new Intent(context, WeatherService.class);
        context.startService(intent);

    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);

        Intent intent = new Intent(context, WeatherService.class);
        context.stopService(intent);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}
