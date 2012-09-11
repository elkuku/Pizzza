package org.elkuku.pizzza.promos;

import java.util.List;

import org.elkuku.pizzza.R;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class PromosListFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		View view = inflater.inflate(R.layout.promos, container, false);

		PromosDataSource datasource = new PromosDataSource(getActivity());

		datasource.open();
		List<String> list = datasource.getList();
		datasource.close();

		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list));

		return view;
	}

}
