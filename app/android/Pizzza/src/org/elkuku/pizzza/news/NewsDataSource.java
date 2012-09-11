package org.elkuku.pizzza.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elkuku.pizzza.R;
import org.elkuku.pizzza.helpers.HttpHelper;
import org.elkuku.pizzza.helpers.SQLiteHelper;
import org.elkuku.pizzza.types.TNews;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class NewsDataSource {
	private Context context;
	private SQLiteDatabase database;
	private SQLiteHelper dbhelper;
	private Cursor cursor;

	private String table = "news";
	private String[] columns = { "id", "title", "text", "date" };

	public NewsDataSource(Context context) {

		this.context = context;

		dbhelper = new SQLiteHelper(context);

		database = dbhelper.getWritableDatabase();
	}

	public void open() throws SQLException {
		database = dbhelper.getWritableDatabase();
	}

	public void close() {
		dbhelper.close();
	}

	public void update() {
		String url = context.getString(R.string.pizzza_link_base) + "/" + context.getString(R.string.pizzza_news_link);

		new BackgroundTaskText().execute(url);
	}

	public TNews createEntry(String title, String text, String date) {

		ContentValues values = new ContentValues();

		values.put("title", title);
		values.put("text", text);
		values.put("date", date);

		long insertId = database.insert(table, null, values);

		cursor = database.query(table, columns, "id = " + insertId, null, null, null, null);

		cursor.moveToFirst();

		TNews item = cursorToItem(cursor);

		cursor.close();

		return item;
	}

	protected List<TNews> getAllEntries() {
		List<TNews> list = new ArrayList<TNews>();

		cursor = database.query(table, columns, null, null, null, null, null);
		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return list;

		while (cursor.isAfterLast() == false) {
			TNews entry = cursorToItem(cursor);
			list.add(entry);
			cursor.moveToNext();
		}

		cursor.close();

		return list;
	}

	protected List<String> getList() {
		List<String> list = new ArrayList<String>();

		cursor = database.query(table, columns, null, null, null, null, null);
		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return list;

		while (cursor.isAfterLast() == false) {
			String entry = cursor.getString(1);
			list.add(entry);
			cursor.moveToNext();
		}

		cursor.close();

		return list;
	}

	private void updateFromUpstream(JSONObject jsonObject) throws JSONException {

		String sql = "DELETE FROM " + table;

		database.execSQL(sql);

		JSONArray data = jsonObject.getJSONArray("data");

		for (int i = 0; i < data.length(); i++) {

			JSONObject item = data.getJSONObject(i);

			createEntry(item.getString("title"), item.getString("text"), item.getString("date"));
		}
	}

	private TNews cursorToItem(Cursor cursor) {
		TNews item = new TNews();

		item.setId(cursor.getLong(0));
		item.setTitle(cursor.getString(1));
		item.setText(cursor.getString(2));
		item.setDate(cursor.getString(3));

		return item;
	}

	private class BackgroundTaskText extends AsyncTask<String, Void, String> {

		protected String doInBackground(String... url) {
			String s = "";

			try {
				HttpHelper HttpHelper = new HttpHelper();

				s = HttpHelper.fetchUrl(url[0]);
			} catch (IOException e) {
				Log.e("PIZZA", e.getMessage());
			}

			return s;
		}

		protected void onPostExecute(String string) {

			if (0 == string.length()) {
				Toast.makeText(context, R.string.request_failed, Toast.LENGTH_LONG).show();
			} else {
				try {
					updateFromUpstream(new JSONObject(string));
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("PIZZZA", e.getMessage());
				}
			}
		}
	}

}
