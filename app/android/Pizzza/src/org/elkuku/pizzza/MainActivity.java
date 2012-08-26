package org.elkuku.pizzza;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		String ss = "12,34,56";

		final String[] parts = ss.split(",");

//		Log.w("PREFTEST", Arrays.toString(parts));

		StringBuilder b = new StringBuilder();

		for(String s : parts){
			if(0 == b.length()) {
				b.append(s);
			}
			else {
				b.append(","+s);
			}
		}

		Log.w("PREFTEST", b.toString());
/*
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

		String a1 = prefs.getString("foox", "bar");

		Log.w("PREFTEST", a1);

		Editor mEditor = prefs.edit();
		mEditor.putString("foox","bazzzz");
//		mEditor.putString("password","password1234");
		mEditor.commit();

		String a2 = prefs.getString("foox", "bar");
		Log.w("PREFTEST", a2);
*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		String s = item.toString();
		Toast.makeText(getApplication(), "Hey: " + s, Toast.LENGTH_LONG).show();

		return true;
	}
}
