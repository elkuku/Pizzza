package org.elkuku.pizzza.news;

import java.util.List;

import org.elkuku.pizzza.R;
import org.elkuku.pizzza.types.TNews;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsListFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		Log.d("PIZZZA", "" + savedInstanceState);

		View view = inflater.inflate(R.layout.news, container, false);

		NewsDataSource datasource = new NewsDataSource(getActivity());

		datasource.open();
		List<TNews> list = datasource.getAllEntries();
		datasource.close();

		// TODO we are creating a dummy list - learn java => ArrayAdapter ;(
		String[] dummylist = new String[list.size()];

		NewsAdapter adapter = new NewsAdapter(getActivity(), list, dummylist);

		setListAdapter(adapter);

		return view;
	}

}
