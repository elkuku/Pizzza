package org.elkuku.pizzza.orders;

import android.app.Activity;
import android.os.Bundle;

public class OrdersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			OrdersFragment details = new OrdersFragment();
			details.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
		}
	}
}
