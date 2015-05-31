package jp.ergo.nexttrainrunning.infrastructure.notification;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import jp.ergo.nexttrainrunning.R;

/**
 * Created by masato on 2015/05/31.
 */
public class Notifier {
    public static void notify(final Context context, final String title, final String message) {
        final int notificationId = 1;

        final NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setStyle(new NotificationCompat.InboxStyle()
                                        .addLine(message)
                                        .setBigContentTitle(title)
                        );

        // Notification を発行
        final NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, notificationBuilder.build());

    }
}
