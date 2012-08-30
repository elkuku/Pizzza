package org.elkuku.pizzza;

import java.util.List;

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



/*
ListView listView = getListView();
listView.setTextFilterEnabled(true);

listView.setOnItemClickListener(new OnItemClickListener() {
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
	    // When clicked, show a toast with the TextView text
	    Toast.makeText(getApplicationContext(),
		((TextView) view).getText(), Toast.LENGTH_SHORT).show();
	}
});

*/
	}

}
