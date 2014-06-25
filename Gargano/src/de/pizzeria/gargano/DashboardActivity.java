package de.pizzeria.gargano;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DashboardActivity extends Activity implements OnClickListener {

	public static void start(Context context) {

		Intent intent = new Intent(context, DashboardActivity.class);

		context.startActivity(intent);
	}

	private ArrayList<String[]> products = new ArrayList<String[]>();

	public static final int PRODUCT_PIZZA = 0;

	ArrayList<PizzaModel> pizzas = new ArrayList<PizzaModel>();

	private String[] pizzanames = null;
	private String[] pizzaprice = null;
	private String[] pizzadescription = null;
	private String[] pizzaIds = null;

	private String[] pastanames = null;
	private String[] pastaprice = null;
	private String[] pastadescription = null;

	private String[] insalatanames = null;
	private String[] insalataprice = null;
	private String[] insalatadescription = null;

	private String[] dessertnames = null;
	private String[] dessertprice = null;
	private String[] dessertdescription = null;

	private String[] antipastinames = null;
	private String[] antipastiprice = null;
	private String[] antipastidescription = null;

	private String[] spaghettinames = null;
	private String[] spaghettiprice = null;
	private String[] spaghettidescription = null;

	private String[] pennenames = null;
	private String[] penneprice = null;
	private String[] pennedescription = null;

	private String[] rigatoninames = null;
	private String[] rigatoniprice = null;
	private String[] rigatonidescription = null;

	private String[] tortellinenames = null;
	private String[] tortelliniprice = null;
	private String[] tortellinidescription = null;

	private String[] gnoccinames = null;
	private String[] gnocciprice = null;
	private String[] gnoccidescription = null;

	private String[] tagliatellenames = null;
	private String[] tagliatelleprice = null;
	private String[] tagliatelledescription = null;

	private String[] gerichtenames = null;
	private String[] gerichteprice = null;
	private String[] gerichtedescription = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dashboard);

		pizzanames = getResources().getStringArray(R.array.pizza_names);
		pizzaprice = getResources().getStringArray(R.array.pizza_price);
		pizzadescription = getResources().getStringArray(
				R.array.pizza_ingredient);
		pizzaIds = getResources().getStringArray(R.array.pizza_id);

		pastanames = getResources().getStringArray(R.array.pasta_names);
		pastaprice = getResources().getStringArray(R.array.pasta_price);
		pastadescription = getResources().getStringArray(
				R.array.pasta_ingredient);

		insalatanames = getResources().getStringArray(R.array.insalata_names);
		insalataprice = getResources().getStringArray(R.array.insalata_price);
		insalatadescription = getResources().getStringArray(
				R.array.insalata_ingredient);

		dessertnames = getResources().getStringArray(R.array.dessert_names);
		dessertprice = getResources().getStringArray(R.array.dessert_price);
		dessertdescription = getResources().getStringArray(
				R.array.dessert_ingredient);

		antipastinames = getResources().getStringArray(R.array.antipasti_names);
		antipastiprice = getResources().getStringArray(R.array.antipasti_price);
		antipastidescription = getResources().getStringArray(
				R.array.antipasti_ingredient);

		spaghettinames = getResources().getStringArray(R.array.spaghetti_names);
		spaghettiprice = getResources().getStringArray(R.array.spaghetti_price);
		spaghettidescription = getResources().getStringArray(
				R.array.spaghetti_ingredient);

		pennenames = getResources().getStringArray(R.array.penne_names);
		penneprice = getResources().getStringArray(R.array.penne_price);
		pennedescription = getResources().getStringArray(
				R.array.penne_ingredient);

		rigatoninames = getResources().getStringArray(R.array.rigatoni_names);
		rigatoniprice = getResources().getStringArray(R.array.rigatoni_price);
		rigatonidescription = getResources().getStringArray(
				R.array.rigatoni_ingredient);

		tortellinenames = getResources().getStringArray(
				R.array.tortellini_names);
		tortelliniprice = getResources().getStringArray(
				R.array.tortellini_price);
		tortellinidescription = getResources().getStringArray(
				R.array.tortellini_ingredient);

		gnoccinames = getResources().getStringArray(R.array.gnocci_names);
		gnocciprice = getResources().getStringArray(R.array.gnocci_price);
		gnoccidescription = getResources().getStringArray(
				R.array.gnocci_ingredient);

		tagliatellenames = getResources().getStringArray(
				R.array.tagliatelle_names);
		tagliatelleprice = getResources().getStringArray(
				R.array.tagliatelle_price);
		tagliatelledescription = getResources().getStringArray(
				R.array.tagliatelle_ingredient);

		gerichtenames = getResources().getStringArray(R.array.gerichte_names);
		gerichteprice = getResources().getStringArray(R.array.gerichte_price);
		gerichtedescription = getResources().getStringArray(
				R.array.gerichte_ingredient);

		ViewActionController viewActionController = new ViewActionController(
				this);
		viewActionController.registerActions();

		LinearLayout submenu = (LinearLayout) findViewById(R.id.submenu);
		submenu.setVisibility(LinearLayout.VISIBLE);

		TextView tv = null;
		LinearLayout lin = null;
		lin = (LinearLayout) findViewById(R.id.dessert);
		lin.setOnClickListener(this);
		lin = (LinearLayout) findViewById(R.id.pizza);
		lin.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.tvmenualforno);
		tv.setOnClickListener(this);
		lin = (LinearLayout) findViewById(R.id.salad);
		lin.setOnClickListener(this);
		lin = (LinearLayout) findViewById(R.id.lingerichte);
		lin.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.tvmenuantipasti);
		tv.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.tvmenuspaghetti);
		tv.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.tvmenupenne);
		tv.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.tvmenurigatoni);
		tv.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.tvmenutortellini);
		tv.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.tvmenugnocci);
		tv.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.tvmenutagliatelle);
		tv.setOnClickListener(this);

		tv = (TextView) findViewById(R.id.tvmenupizza);
		tv.setTypeface(null, Typeface.BOLD);

		initList(pizzanames, pizzaprice, pizzadescription);

		PizzaModel pizzaModel = null;
		for(int i=0;i<pizzaIds.length;i++){
			pizzaModel = new PizzaModel(pizzanames[i],
					pizzaprice[i], pizzadescription[i], pizzaIds[i]);
			pizzas.add(pizzaModel);
		}
		
		// Intent intent = new Intent(getApplicationContext(),
		// NotificationService.class);
		// intent.putExtra(NotificationService.EXTRA, pizzaModel);
		// intent.setAction(NotificationService.NOTIFY);
		// startService(intent);

		new Handler().postAtTime(new Runnable() {

			@Override
			public void run() {
				Log.i("pizza","create new ones");
				createPizzaNotifications();
			}
		}, 1000);
	}

	private void createPizzaNotifications() {
		Bitmap bitmapMila = BitmapFactory.decodeResource(getResources(),
				R.drawable.garganologo);

		// String to represent the group all the notifications will be a part of
		final String GROUP_KEY_MESSAGES = "group_key_messages";

		// Group notification that will be visible on the phone
		NotificationCompat.Builder groupBuilder = new NotificationCompat.Builder(
				this).setContentTitle("Pizzeria Gargano")
				.setContentText("Original Italian Pizza")
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.wearbackground))
				.setSmallIcon(R.drawable.ic_launcher).setLargeIcon(bitmapMila);
		Notification summaryNotification = new WearableNotifications.Builder(
				groupBuilder).setGroup(GROUP_KEY_MESSAGES,
				WearableNotifications.GROUP_ORDER_SUMMARY).build();
		
		HashMap<Notification, PizzaModel> pizzaNotifs = new HashMap<Notification, PizzaModel>();
		
		for(PizzaModel pizza : pizzas){
			Intent orderIntent = new Intent(this, ConfirmationActivity.class);

			Intent listIntent = new Intent(this, OrderActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("orderkey", pizza.getPizzaId());
			Log.i("pizzakeys", pizza.getPizzaId());
			listIntent.putExtras(bundle);

			PendingIntent orderIntentPending = PendingIntent.getActivity(this,
					Integer.valueOf(pizza.getPizzaId()), orderIntent, 0);

			PendingIntent addListIntent = PendingIntent.getActivity(this,
					Integer.valueOf(pizza.getPizzaId()), listIntent, PendingIntent.FLAG_UPDATE_CURRENT);

			NotificationCompat.Builder child1 = new NotificationCompat.Builder(this)
					.addAction(android.R.drawable.ic_menu_add,"Order now", orderIntentPending)
					.addAction(android.R.drawable.ic_menu_edit,"Add to list",addListIntent)
					.setContentTitle(pizza.getPizzaName())
					.setContentText(pizza.getPizzaPrice());
			
			//add second page - description
			// Create a big text style for the second page
			BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
			secondPageStyle.setBigContentTitle("Zutaten")
			               .bigText(pizza.getPizzaDescriptions());

			// Create second page notification
			Notification secondPageNotification =
			        new NotificationCompat.Builder(this)
			        .setStyle(secondPageStyle)
			        .build();

			//---
			
			pizzaNotifs.put(new WearableNotifications.Builder(child1)
					.setGroup(GROUP_KEY_MESSAGES).addPage(secondPageNotification).build(), pizza);		
		}

		// Issue the group notification
		NotificationManagerCompat notificationManager = NotificationManagerCompat
				.from(this);
		notificationManager.notify(0, summaryNotification);

		// Issue the separate wear notifications
		
		Set<Notification> keys = pizzaNotifs.keySet();
		
		for(Notification notifs : keys){
			notificationManager.notify(
					Integer.valueOf(pizzaNotifs.get(notifs).getPizzaId()), notifs);						
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		createPizzaNotifications();
		Log.i("onActivityResult","pizza added to list");
	}
	
	@Deprecated
	private void showNotification() {
		Bitmap bitmapMila = BitmapFactory.decodeResource(getResources(),
				R.drawable.garganologo);

		int notificationId = 0;

		// String to represent the group all the notifications will be a part of
		final String GROUP_KEY_MESSAGES = "group_key_messages";

		// Group notification that will be visible on the phone
		NotificationCompat.Builder groupBuilder = new NotificationCompat.Builder(
				this).setContentTitle("Pizzeria Gargano")
				.setContentText("Pizza Pizza Pizza")
				.setSmallIcon(R.drawable.garganologo).setLargeIcon(bitmapMila);
		Notification summaryNotification = new WearableNotifications.Builder(
				groupBuilder).setGroup(GROUP_KEY_MESSAGES,
				WearableNotifications.GROUP_ORDER_SUMMARY).build();

		// Separate notifications that will be visible on the watch
		Intent viewIntent1 = new Intent(this, DashboardActivity.class);
		PendingIntent viewPendingIntent1 = PendingIntent.getActivity(this,
				notificationId + 1, viewIntent1, 0);
		NotificationCompat.Builder child1 = new NotificationCompat.Builder(this)
				.addAction(android.R.drawable.ic_notification_clear_all,
						"Now per SMS", viewPendingIntent1)
				.setContentTitle("Pizza Salami")
				.setContentText("Cheese + Salami")
				.setSmallIcon(android.R.drawable.ic_notification_clear_all);
		Notification notification1 = new WearableNotifications.Builder(child1)
				.setGroup(GROUP_KEY_MESSAGES).build();

		Intent viewIntent2 = new Intent(this, ConfirmationActivity.class);
		PendingIntent viewPendingIntent2 = PendingIntent.getActivity(this,
				notificationId + 2, viewIntent2, 0);
		NotificationCompat.Builder child2 = new NotificationCompat.Builder(this)
				.addAction(R.drawable.ic_launcher, "Now per SMS",
						viewPendingIntent2).setContentTitle("Pizza Tuna")
				.setContentText("Cheese + Tuna + Broccoli + Ciarcofi")
				.setSmallIcon(android.R.drawable.ic_notification_overlay);
		Notification notification2 = new WearableNotifications.Builder(child2)
				.setGroup(GROUP_KEY_MESSAGES).build();

		// Issue the group notification
		NotificationManagerCompat notificationManager = NotificationManagerCompat
				.from(this);
		notificationManager.notify(notificationId + 0, summaryNotification);

		// Issue the separate wear notifications
		notificationManager.notify(notificationId + 2, notification2);
		notificationManager.notify(notificationId + 1, notification1);
	}

	private LinearLayout linRoot = null;

	public void initList(String[] names, String[] price, String[] description) {

		this.linRoot = (LinearLayout) this.findViewById(R.id.linitems);

		if (names.length > 0) {

			this.linRoot.removeAllViews();

			for (int i = 0; i < names.length; i++) {

				View v = View.inflate(this, R.layout.item, null);

				RelativeLayout linItem = (RelativeLayout) v
						.findViewById(R.id.linitem);
				// linItem.setOnClickListener(new OnClickListener() {
				//
				// @Override
				// public void onClick(View v) {
				//
				//
				// }
				// });

				TextView tv = null;

				tv = (TextView) v.findViewById(R.id.tvname);
				tv.setText(names[i]);

				tv = (TextView) v.findViewById(R.id.tvdescription);
				tv.setText(description[i]);

				tv = (TextView) v.findViewById(R.id.tvprice);
				tv.setText(price[i]);

				RelativeLayout relMinder = (RelativeLayout) v
						.findViewById(R.id.linminders);
				ImageView ivempty = (ImageView) v
						.findViewById(R.id.ivminderempty);
				ImageView ivfull = (ImageView) v
						.findViewById(R.id.ivminderfull);
				relMinder.setOnClickListener(new Minder(ivempty, ivfull, i,
						names, price, description));

				linRoot.addView(linItem);
			}

		} else {

			LinearLayout linRoot = (LinearLayout) this
					.findViewById(R.id.linitems);

			linRoot.removeAllViews();
		}

	}

	public class Minder implements OnClickListener {

		private int pos = -1;
		private String[] name = null;
		private String[] price = null;
		private String[] desc = null;

		private boolean isMinded = true;

		private ImageView ivfull = null;
		private ImageView ivempty = null;

		public Minder(ImageView ivempty, ImageView ivfull, int pos,
				String[] name, String[] price, String[] desc) {
			this.pos = pos;
			this.name = name;
			this.price = price;
			this.desc = price;

			this.ivempty = ivempty;
			this.ivfull = ivfull;
		}

		@Override
		public void onClick(View v) {

			ImageView iv = null;

			// if(!isMinded){
			// Toast.makeText(DashboardActivity.this,
			// isMinded+" "+name[this.pos], Toast.LENGTH_SHORT).show();
			// isMinded = true;
			//
			// this.ivempty.setVisibility(ImageView.VISIBLE);
			// this.ivfull.setVisibility(ImageView.INVISIBLE);
			//
			// }else{
			// Toast.makeText(DashboardActivity.this,
			// isMinded+" "+name[this.pos], Toast.LENGTH_SHORT).show();
			// isMinded = false;
			// this.ivempty.setVisibility(ImageView.INVISIBLE);
			// this.ivfull.setVisibility(ImageView.VISIBLE);
			// }

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_info:

			Intent intent = new Intent(this, InfoActivity.class);
			startActivity(intent);

			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		TextView tv = null;
		LinearLayout lin = null;

		switch (v.getId()) {

		case R.id.tvmenuantipasti:
			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.BOLD_ITALIC);
			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(antipastinames, antipastiprice, antipastidescription);

			break;

		case R.id.tvmenualforno: // pasta �berbacken

			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.BOLD_ITALIC);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(pastanames, pastaprice, pastadescription);

			break;

		case R.id.tvmenutagliatelle:
			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.BOLD_ITALIC);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(tagliatellenames, tagliatelleprice, tagliatelledescription);

			break;

		case R.id.tvmenugnocci:
			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.BOLD_ITALIC);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(gnoccinames, gnocciprice, gnoccidescription);

			break;

		case R.id.tvmenutortellini:
			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.BOLD_ITALIC);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(tortellinenames, tortelliniprice, tortellinidescription);

			break;

		case R.id.tvmenurigatoni:
			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.BOLD_ITALIC);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(rigatoninames, rigatoniprice, rigatonidescription);

			break;

		case R.id.tvmenupenne:
			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.BOLD_ITALIC);
			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(pennenames, penneprice, pennedescription);

			break;

		case R.id.tvmenuspaghetti:
			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.BOLD_ITALIC);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(spaghettinames, spaghettiprice, spaghettidescription);

			break;

		case R.id.pizza:
			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.BOLD);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(pizzanames, pizzaprice, pizzadescription);

			break;

		case R.id.salad:
			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.BOLD);

			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(insalatanames, insalataprice, insalatadescription);

			break;

		case R.id.dessert:
			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.BOLD_ITALIC);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.NORMAL);

			initList(dessertnames, dessertprice, dessertdescription);

			break;

		case R.id.lingerichte:
			tv = (TextView) findViewById(R.id.tvmenudessert);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupizza);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuinsalata);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvmenualforno);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuantipasti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenuspaghetti);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenurigatoni);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutagliatelle);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenutortellini);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenugnocci);
			tv.setTypeface(null, Typeface.NORMAL);
			tv = (TextView) findViewById(R.id.tvmenupenne);
			tv.setTypeface(null, Typeface.NORMAL);

			tv = (TextView) findViewById(R.id.tvgerichte);
			tv.setTypeface(null, Typeface.BOLD);

			initList(gerichtenames, gerichteprice, gerichtedescription);

			break;
		}
	}
}