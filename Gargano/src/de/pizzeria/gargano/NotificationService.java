package de.pizzeria.gargano;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class NotificationService extends Service {
	public static final String NOTIFY = "mindon.idea.wearable.demo.NOTIFY";
	public static final int RESULT_CODE_NOTIFICATION = 0;

	public static final String EXTRA = "wear.pizza.notify";
	public static final String STEPS = "steps";
	private static final int _NOTIFICATION_ID = 0;
	private static final int _IMAGE_WIDTH = 280;
	private static final int _IMAGE_HEIGHT = 280;

	private NotificationManagerCompat mNotificationManager;
	private Binder mBinder = new LocalBinder();

	public class LocalBinder extends Binder {
		NotificationService getService() {
			return NotificationService.this;
		}
	}

    private void showNotification(){
    	Bitmap bitmapMila = BitmapFactory.decodeResource(getResources(), R.drawable.icon);

    	// Nuke all previous notifications and generate unique ids
    	NotificationManagerCompat.from(this).cancelAll();
    	int notificationId = 0;

    	// String to represent the group all the notifications will be a part of
    	final String GROUP_KEY_MESSAGES = "group_key_messages";

    	// Group notification that will be visible on the phone
    	NotificationCompat.Builder builderG = new NotificationCompat.Builder(this)
    	    .setContentTitle("2 Pet Notifications")
    	    .setContentText("Mila and Dylan both sent messages")
    	    .setSmallIcon(R.drawable.ic_launcher)
    	    .setLargeIcon(bitmapMila);
    	Notification summaryNotification = new WearableNotifications.Builder(builderG)
    	    .setGroup(GROUP_KEY_MESSAGES, WearableNotifications.GROUP_ORDER_SUMMARY)
    	    .build();

    	// Separate notifications that will be visible on the watch
    	Intent viewIntent1 = new Intent(this, DashboardActivity.class);
    	PendingIntent viewPendingIntent1 =
    	    PendingIntent.getActivity(this, notificationId+1, viewIntent1, 0);
    	NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this)
    	    .addAction(R.drawable.ic_launcher, "Treat Fed", viewPendingIntent1)
    	    .setContentTitle("Message from Mila")
    	    .setContentText("What's for dinner? "
    	                    + "Can we have steak?")
    	    .setSmallIcon(R.drawable.ic_launcher);
    	Notification notification1 = new WearableNotifications.Builder(builder1)
    	    .setGroup(GROUP_KEY_MESSAGES)
    	    .build();

    	Intent viewIntent2 = new Intent(this, DashboardActivity.class);
    	PendingIntent viewPendingIntent2 =
    	     PendingIntent.getActivity(this, notificationId+2, viewIntent2, 0);
    	NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this)
    	    .addAction(R.drawable.ic_launcher, "Water Filled", viewPendingIntent2)
    	    .setContentTitle("Message from Dylan")
    	    .setContentText("Can you refill our water bowl?")
    	    .setSmallIcon(R.drawable.ic_launcher);
    	Notification notification2 = new WearableNotifications.Builder(builder2)
    	    .setGroup(GROUP_KEY_MESSAGES)
    	    .build();

    	// Issue the group notification
    	NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
    	notificationManager.notify(notificationId+0, summaryNotification);

    	// Issue the separate wear notifications
    	notificationManager.notify(notificationId+2, notification2);
    	notificationManager.notify(notificationId+1, notification1);
	}
	@Override
	public void onCreate() {
		mNotificationManager = NotificationManagerCompat.from(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent.getAction().equals(NOTIFY)) {
//			createNotification(intent);
			showNotification();
			return START_STICKY;
		}
		return START_NOT_STICKY;
	}

	
	
//	private void createNotification(Intent intent) {
//		PizzaModel pizzaModel = (PizzaModel) intent.getParcelableExtra(EXTRA);
//		ArrayList<Notification> notificationPages = new ArrayList<Notification>();
//
//		if (pizzaModel != null) {
//
//			List<String> pizzaNames = pizzaModel.getPizzaNames();
//			List<String> pizzaPrices = pizzaModel.getPizzaPrice();
//			List<String> pizzaDescription = pizzaModel.getPizzaDescriptions();
//
//			for (int i = 0; i < pizzaNames.size(); i++) {
//
//				NotificationCompat.Builder child = new NotificationCompat.Builder(
//						this);
//				// child.setSmallIcon(R.mipmap.notification); // icon
//				Bitmap image = Bitmap.createScaledBitmap(
//						loadBitmapAsset("pizzaslice.png"), _IMAGE_WIDTH,
//						_IMAGE_HEIGHT, false);
//
//				NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle()
//						.bigPicture(image);
//				style.setBigContentTitle(pizzaNames.get(i));
//				style.setSummaryText(pizzaDescription.get(i));
//				child.setStyle(style);
//
//				notificationPages.add(child.build());
//			}
//		}
//
//		NotificationCompat.Builder builder = new NotificationCompat.Builder(
//				this);
//
//		builder.setContentTitle("Pizzeria Gargano");
//		builder.setContentText("Original Italian Pizzeria");
//
//		builder.setSmallIcon(R.drawable.icon); // icon
////		builder.setOngoing(true);
//
//		Intent resultIntent = new Intent(this, DashboardActivity.class);
//		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//		stackBuilder.addParentStack(DashboardActivity.class);
//		stackBuilder.addNextIntent(resultIntent);
//		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(RESULT_CODE_NOTIFICATION, PendingIntent.FLAG_UPDATE_CURRENT);
//
//		builder.addAction(R.drawable.ic_launcher,"Order pizza", resultPendingIntent);
//		mNotificationManager.notify(_NOTIFICATION_ID, new WearableNotifications.Builder(builder)
//		.addPages(notificationPages).build());
//	}

	private Bitmap loadBitmapAsset(String bitmapFileName) {
		InputStream is = null;
		Bitmap bitmap = null;
		try {
			is = getAssets().open(bitmapFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (is != null) {
			bitmap = BitmapFactory.decodeStream(is);
		}

		return bitmap;
	}
}
