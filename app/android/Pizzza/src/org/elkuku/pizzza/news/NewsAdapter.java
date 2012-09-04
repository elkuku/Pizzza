package org.elkuku.pizzza.news;

import java.util.List;

import org.elkuku.pizzza.R;
import org.elkuku.pizzza.R.id;
import org.elkuku.pizzza.R.layout;
import org.elkuku.pizzza.types.TNews;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<String> {

	private final Context context;
	private List<TNews> list;

	public NewsAdapter(Context context, List<TNews> list, String[] dummylist) {
		super(context, R.layout.news_row, dummylist);
		this.context = context;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.news_row, parent, false);

		TNews item = list.get(position);

		TextView textTitle = (TextView) rowView.findViewById(R.id.txtTitle);
		textTitle.setText(item.getTitle());

		TextView textDate = (TextView) rowView.findViewById(R.id.txtDate);
		textDate.setText(item.getDate());

		TextView textText = (TextView) rowView.findViewById(R.id.txtText);
		textText.setText(Html.fromHtml(item.getText()));

		return rowView;
	}

}
