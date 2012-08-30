package org.elkuku.pizzza;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends ArrayAdapter<String> {

	private final Context context;
	private List<TEntry> menuList;

	public MenuAdapter(Context context, List<TEntry> menuList, String[] dummylist) {
		super(context, R.layout.pizzzarow, dummylist);
		this.context = context;
		this.menuList = menuList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.pizzzarow, parent, false);

		TEntry item = menuList.get(position);

		TextView textViewLabel = (TextView) rowView.findViewById(R.id.label);
		textViewLabel.setText(item.getTitle());

		TextView textViewDesc = (TextView) rowView.findViewById(R.id.ingredients);
		textViewDesc.setText(item.getDescription());


		//Preferences.pprefs =
		//SharedPreferences prefs = getSharedPreferences("myDataStorage");
//Log.i("DBDEBUG", ""+item.getId()+" - "+item.getFavorite());

		if(0 == item.getCatid()) {
			// This is a category
			rowView.setBackgroundColor(getContext().getResources().getColor(R.color.pizzza_CI_bg));
			textViewLabel.setTextColor(getContext().getResources().getColor(R.color.pizzza_CI_fg));
			textViewDesc.setHeight(0);
		}
		else if(1 == item.getFavorite()) {
			// Favorites
			ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
			imageView.setImageResource(R.drawable.bookmark);
		}
		else {
			// Regular entry
			ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
			imageView.setVisibility(View.GONE);
		}

		return rowView;
	}

	public TEntry getEntry(int position) {
		return menuList.get(position);
	}

	public Intent getEntryToIntent(int position, int REQUEST_CODE) {
		TEntry item = getEntry(position);

		Intent i = new Intent(context, DetailsActivity.class);

		i.putExtra("id", item.getId());
		i.putExtra("title", item.getTitle());
		i.putExtra("description", item.getDescription());
		i.putExtra("price_peq", item.getPrice_peq());
		i.putExtra("price_med", item.getPrice_med());
		i.putExtra("price_gra", item.getPrice_gra());
		i.putExtra("favorite", item.getFavorite());

		return i;
	}
}
