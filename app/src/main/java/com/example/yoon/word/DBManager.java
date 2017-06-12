package com.example.yoon.word;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

public class DBManager {

	static final String DB_WORDS = "words.db";
	static final String TABLE_WORDS = "words";
	static final int DB_VERSION = 1;
	Context mContext = null;

	private static DBManager mDbManager = null;
	private SQLiteDatabase mDatabase = null;

	public static DBManager getInstance(Context context) {
		if (mDbManager == null) {
			mDbManager = new DBManager(context);
		}
		return mDbManager;
	}

	public DBManager(Context context) {

		mContext = context;
		mDatabase = context.openOrCreateDatabase(DB_WORDS,
				Context.MODE_PRIVATE, null);
		mDatabase.execSQL("create table if not Exists " + TABLE_WORDS + "("
				+ "_id integer primary key autoincrement, "
				+ "words_name text, " + "words_mean text, "
				+ "words_memo text );");
	}

	public long insert(ContentValues addRowValue) {
		return mDatabase.insert(TABLE_WORDS, null, addRowValue);
	}

	public Cursor query(String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy) {
		return mDatabase.query(TABLE_WORDS, columns, selection, selectionArgs,
				groupBy, having, orderBy);
	}

	public int update(ContentValues updateRowValue, String whereClause,
			String[] whereArgs) {
		return mDatabase.update(TABLE_WORDS, updateRowValue, whereClause,
				whereArgs);
	}

	public int delete(String whereClause, String[] whereArgs) {
		return mDatabase.delete(TABLE_WORDS, whereClause, whereArgs);
	}
}
