package org.elkuku.pizzza.catalog;

import org.elkuku.pizzza.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class CatalogDetailsActivity extends Activity {

	private Bundle extras;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.catalog_details);

		extras = getIntent().getExtras();

		if (extras == null) {
			return;
		}

		String title = extras.getString("title");
		String description = extras.getString("description");
		int favorite = extras.getInt("favorite");

		TextView txtPrice1 = (TextView) findViewById(R.id.txt_price1);
		txtPrice1.setText(""+extras.getFloat("price_peq"));

		TextView txtPrice2 = (TextView) findViewById(R.id.txt_price2);
		txtPrice2.setText(""+extras.getFloat("price_med"));

		TextView txtPrice3 = (TextView) findViewById(R.id.txt_price3);
		txtPrice3.setText(""+extras.getFloat("price_gra"));

		TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtTitle.setText(title);

		TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
		txtDescription.setText(description);

		if(1 == favorite)
		{
			CheckBox chkFavorite = (CheckBox) findViewById(R.id.chkFavorite);
			chkFavorite.setChecked(true);
		}
	}

	public void onClick(View view) {
		finish();
	}

	@Override
	public void finish() {
		Intent data = new Intent();

		Log.i("GGG", extras.getString("title"));

		CheckBox chkFavorite = (CheckBox) findViewById(R.id.chkFavorite);
		int favorite = chkFavorite.isChecked() ? 1 : 0;

		data.putExtra("favorite", favorite);
		data.putExtra("id", extras.getLong("id"));

		setResult(RESULT_OK, data);

		super.finish();
	}
}
