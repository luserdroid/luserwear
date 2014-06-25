package de.pizzeria.gargano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;

/**
 * Sends pizza order to pizza shop
 * 
 * @author kocyigitre
 * 
 */
public class ConfirmationActivity extends Activity {

	public static final String ACTION_SMS_SENT = "com.pizzagargano.SMS_SENT_ACTION";

	private HashMap<String, PizzaModel> pizzas = new HashMap<String,PizzaModel>();
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getPizzas();
		// read from prefs and send ids per sms to pizzeria
		sendOrder();
	}

	private void getPizzas(){
		String[] pizzanames = getResources().getStringArray(R.array.pizza_names);
		String[] pizzaprice = getResources().getStringArray(R.array.pizza_price);
		String[] pizzadescription = getResources().getStringArray(
				R.array.pizza_ingredient);
		String[] pizzaIds = getResources().getStringArray(R.array.pizza_id);
		PizzaModel pizzaModel = null;
		for(int i=0;i<pizzaIds.length;i++){
			pizzaModel = new PizzaModel(pizzanames[i],
					pizzaprice[i], pizzadescription[i], pizzaIds[i]);
			pizzas.put(pizzaModel.getPizzaId(),pizzaModel);
		}
	}
	
	private void sendOrder() {
		Gson gson = new Gson();
		SharedPreferences prefs = getSharedPreferences("ORDER", 0);
		String orderListJson = prefs.getString("orderkey", "");
		final List<String> orderList = gson.fromJson(orderListJson,
				new TypeToken<List<String>>() {
				}.getType());

		StringBuilder builder = new StringBuilder();
		if(orderList == null){
			return;
		}
		
		PizzaModel pizza;
		builder.append("Meine Bestellung geht an:").append(System.lineSeparator())
		.append("Larry Page, San Francisco").append(System.lineSeparator());
		for (String pizzaIds : orderList) {
			pizza = pizzas.get(pizzaIds);
			builder.append(pizzaIds).append(": ").append(pizza.getPizzaName()).append(System.lineSeparator());
		}

		String strReceipentsList = "your number";
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(strReceipentsList, null, builder.toString(),
				PendingIntent.getBroadcast(this, 0,
						new Intent(ACTION_SMS_SENT), 0), null);

		// also create a new notification
		this.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {

				String message = "";

				switch (getResultCode()) {
				case Activity.RESULT_OK:
					message = "Pizza order send!";
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				case SmsManager.RESULT_ERROR_NO_SERVICE:
				case SmsManager.RESULT_ERROR_NULL_PDU:
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					message = "Pizza could not be ordered!";
					break;
				default:
					message = "something went totally wrong";
				}

				// create here a new normal notification
				Log.i("sms", message);

				// delete former order
				SharedPreferences prefs = getSharedPreferences("ORDER", 0);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("orderkey", "");
				editor.commit();

				// send a notification
				NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
						ConfirmationActivity.this)
						.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle("Pizza Order")
						.setContentText(message);

				// Get an instance of the NotificationManager service
				NotificationManagerCompat notificationManager = NotificationManagerCompat
						.from(ConfirmationActivity.this);

				// Build the notification and issues it with notification
				// manager.
				notificationManager.notify(1101, notificationBuilder.build());

				finish();
			}
		}, new IntentFilter(ACTION_SMS_SENT));
	}
}
