package org.elkuku.pizzza;

import java.util.Calendar;
import java.util.List;

import org.elkuku.pizzza.prefs.PrefsActivity;
import org.elkuku.pizzza.promos.PromosDataSource;
import org.elkuku.pizzza.types.TPromo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		Calendar now = Calendar.getInstance();

		String[] strDays = new String[] { "domingo", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado" };

		String curDay = strDays[now.get(Calendar.DAY_OF_WEEK) - 1];

		PromosDataSource datasource = new PromosDataSource(this);

		datasource.open();
		List<TPromo> list = datasource.getAllEntries();
		datasource.close();

		String promoText = "";

		for (TPromo item : list) {
			if (item.getDay().equals(curDay)) {
				promoText = item.getName();
			}
		}

		TextView txtPromo = (TextView) findViewById(R.id.txtPromo);

		if (promoText.contentEquals("")) {
			//-- No Promo today :(
			txtPromo.setVisibility(View.GONE);
		} else {
			txtPromo.setText(promoText);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.activity_main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.menu_settings:

			Intent i = new Intent(MainActivity.this, PrefsActivity.class);

			startActivity(i);
			break;
		}

		return true;
	}
}
