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

	private String table = "MENU";

	private String[] columns = { "id", "catid", "title", "description" };

	private Cursor cursor;

	 public DataSourceMenu(Context context) {

		dbhelper = new SQLiteHelper(context);

		database = dbhelper.getWritableDatabase();
	}

	public void open() throws SQLException {
		dbhelper.getWritableDatabase();
	}

	public void close() {
		dbhelper.close();
	}

	public Entry createEntry(int catid, String title, String description) {
		ContentValues values = new ContentValues();

		values.put("catid", catid);
		values.put("title", title);
		values.put("description", description);

		long insertId = database.insert(table, null, values);

		cursor = database.query(table, columns, "id = " + insertId,
				null, null, null, null);

		cursor.moveToFirst();

		Entry entry = cursorToEntry(cursor);

		cursor.close();

		return entry;
	}

	public void reset() {
		dbhelper.resetDb();
	}

	protected List<Entry> getAllEntries() {
		List<Entry> list = new ArrayList<Entry>();

		cursor = database.query(table, columns, null, null, null, null,
				null);
		cursor.moveToFirst();

		if (cursor.getCount() == 0)
			return list;

		while (cursor.isAfterLast() == false) {
			Entry entry = cursorToEntry(cursor);
			list.add(entry);
			cursor.moveToNext();
		}

		cursor.close();

		return list;
	}

	private Entry cursorToEntry(Cursor cursor) {
		Entry entry = new Entry();
		entry.setId(cursor.getLong(0));
		entry.setCatid(cursor.getLong(1));
		entry.setTitle(cursor.getString(2));
		entry.setDescription(cursor.getString(3));

		return entry;
	}

}
