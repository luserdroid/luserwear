package de.pizzeria.gargano;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Collects pizza order items.
 * 
 * @author kocyigitre
 *
 */
public class OrderActivity extends Activity {

	public static final String TAG = OrderActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");

		Bundle extras = getIntent().getExtras();
		saveOrder(extras);

		finish();
	}

	private void saveOrder(Bundle orderBundle) {
		Gson gson = new Gson();
		SharedPreferences prefs = getSharedPreferences("ORDER", 0);
		String orderListJson = prefs.getString("orderkey", "");
		List<String> orderList = gson.fromJson(orderListJson, new TypeToken<List<String>>(){}.getType());
		String newPizza = (String)orderBundle.getString("orderkey");
		Log.i(TAG, "new pizza is: "+newPizza);

		if(orderList == null){
			orderList = new ArrayList<String>();
		}
		orderList.add(newPizza);
		orderListJson = gson.toJson(orderList);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("orderkey", orderListJson);
		editor.commit();
	}
}
