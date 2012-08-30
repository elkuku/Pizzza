package org.elkuku.pizzza;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class NewsActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.news);

		NewsDataSource datasource = new NewsDataSource(this);

		datasource.open();
		List<String> list = datasource.getList();
		datasource.close();

		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
	}

}
