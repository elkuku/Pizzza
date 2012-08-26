package org.elkuku.pizzza;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSourceMenu {

	private SQLiteDatabase database;

	private SQLiteHelper dbhelper;

	private String table = "menu";

	private String[] columns = { "id", "catid", "title", "description", "price_peq", "price_med", "price_gra",
			"favorite" };

	private Cursor cursor;

	public DataSourceMenu(Context context) {

		dbhelper = new SQLiteHelper(context);

		database = dbhelper.getWritableDatabase();
	}

	public void open() throws SQLException {
		database = dbhelper.getWritableDatabase();
	}

	public void close() {
		dbhelper.close();
	}

	public TEntry createEntry(int catid, String title, String description, double p_peq, double p_med, double p_gra) {

		ContentValues values = new ContentValues();

		values.put("catid", catid);
		values.put("title", title);
		values.put("description", description);
		values.put("price_peq", p_peq);
		values.put("price_med", p_med);
		values.put("price_gra", p_gra);

		long insertId = database.insert(table, null, values);

		cursor = database.query(table, columns, "id = " + insertId, null, null, null, null);

		cursor.moveToFirst();

		TEntry entry = cursorToEntry(cursor);

		cursor.close();

		return entry;
	}

	public void reset() {
		dbhelper.resetDb();
	}

	public List<Long> getFavorites() {
		List<Long> list = new ArrayList<Long>();

		//String[] columns = new String[] { "id" };

		Cursor cursor = database.query(table, new String[] { "id" }, "favorite=?", new String[] { "1" }, null, null, null);

		// cursor = database.query(table, columns, null, null, null, null, null);

		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return list;

		while (cursor.isAfterLast() == false) {
			//TEntry entry = cursorToEntry(cursor);
			list.add(cursor.getLong(0));
			cursor.moveToNext();
		}

		cursor.close();

		return list;
	}

	public void setFavorite(long id, int favorite) {

		open();

		ContentValues values = new ContentValues();
		values.put("favorite", favorite);

		String[] ids = new String[] { String.valueOf(id) };

		database.update(table, values, "id = ?", ids);
	}

	protected List<TEntry> getAllEntries() {
		List<TEntry> list = new ArrayList<TEntry>();

		cursor = database.query(table, columns, null, null, null, null, null);
		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return list;

		while (cursor.isAfterLast() == false) {
			TEntry entry = cursorToEntry(cursor);
			list.add(entry);
			cursor.moveToNext();
		}

		cursor.close();

		return list;
	}

	private TEntry cursorToEntry(Cursor cursor) {
		TEntry entry = new TEntry();

		entry.setId(cursor.getLong(0));
		entry.setCatid(cursor.getLong(1));
		entry.setTitle(cursor.getString(2));
		entry.setDescription(cursor.getString(3));
		entry.setPrice_peq(cursor.getFloat(4));
		entry.setPrice_med(cursor.getFloat(5));
		entry.setPrice_gra(cursor.getFloat(6));
		entry.setFavorite(cursor.getInt(7));

		return entry;
	}

}
