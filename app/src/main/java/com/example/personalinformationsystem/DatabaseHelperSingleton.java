package com.example.personalinformationsystem;

import android.content.Context;

public class DatabaseHelperSingleton {
	private static DatabaseHelper instance;

	public static synchronized DatabaseHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DatabaseHelper(context.getApplicationContext());
		}
		return instance;
	}
}
