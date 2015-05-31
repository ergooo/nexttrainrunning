package jp.ergo.nexttrainrunning.domain;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import jp.ergo.nexttrainrunning.domain.location.LocationService;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            context.startService(new Intent(context, LocationService.class));
        }
    }

}