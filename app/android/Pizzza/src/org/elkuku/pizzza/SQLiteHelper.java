package org.elkuku.pizzza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE = "pizzza.db";

	private static final String TABLE_MENU = "menu";

	private static final int DB_VERSION = 1;

	private static final String DATABASE_CREATE =
			"create table " + TABLE_MENU
			+ " ("
			+ "id integer primary key autoincrement"
			+ ", catid integer"
			+ ", title text"
			+ ", description text"
			+ ");";

	public SQLiteHelper(Context context) {
		super(context, DATABASE, null, DB_VERSION);

		Log.e("DBDEBUG", "SQLiteHelper created");
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.e("DBDEBUG", "SQLiteHelper onCreate: " + DATABASE_CREATE);

		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		// upgrading..
		Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);

		onCreate(database);
	}

	public void resetDb() {
		SQLiteDatabase database = getWritableDatabase();

		database.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
		database.execSQL(DATABASE_CREATE);
	}

}
