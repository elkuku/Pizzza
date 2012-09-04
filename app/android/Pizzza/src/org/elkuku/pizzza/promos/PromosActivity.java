package org.elkuku.pizzza.promos;

import java.util.List;

import org.elkuku.pizzza.R;
import org.elkuku.pizzza.R.layout;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class PromosActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.promos);

		PromosDataSource datasource = new PromosDataSource(this);

		datasource.open();
		List<String> list = datasource.getList();
		datasource.close();

		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
	}

}
