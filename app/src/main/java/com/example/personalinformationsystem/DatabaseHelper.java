package com.example.personalinformationsystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.personalinformationsystem.cardadapters.BankCardAdapter;
import com.example.personalinformationsystem.cardadapters.DocumentCardAdapter;
import com.example.personalinformationsystem.cardadapters.PasswordCardAdapter;
import com.example.personalinformationsystem.cardadapters.PersonalCardAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String REGISTRATION_TABLE = "RegistrationInformation";
	private static final String REG_COL1 = "REG_ID";
	private static final String REG_COL2 = "REG_USER_NAME";
	private static final String REG_COL3 = "REG_FULL_NAME";
	private static final String REG_COL4 = "REG_PASSWORD";

	private static final String DATABASE_NAME = "Personal_Information.db";
	private static final int DATABASE_VERSION = 1;

	private static final String PERSONAL_TABLE = "PersonalInformation";
	private static final String PER_COL1 = "ID";
	private static final String PER_COL2 = "FIRST_NAME";
	private static final String PER_COL3 = "LAST_NAME";
	private static final String PER_COL4 = "DATE_OF_BIRTH";
	private static final String PER_COL5 = "PHONE_NUMBER";
	private static final String PER_COL6 = "EMAIL_ID";

	private static final String BANK_TABLE = "BankInformation";
	private static final String BANK_COL1 = "BANK_ID";
	private static final String BANK_COL2 = "BANK_NAME";
	private static final String BANK_COL3 = "ACCOUNT_TYPE";
	private static final String BANK_COL4 = "ACCOUNT_NUMBER";
	private static final String BANK_COL5 = "IFSC_CODE";
	private static final String BANK_COL6 = "CUSTOMER_ID";
	private static final String BANK_COL7 = "CUSTOMER_ID_PASSWORD";

	private static final String DOC_TABLE = "DocumentsInformation";
	private static final String DOC_COL1 = "DOCUMENT_ID";
	private static final String DOC_COL2 = "DOCUMENT_NAME";
	private static final String DOC_COL3 = "DOCUMENT_NUMBER";

	private static final String PASS_TABLE = "PasswordInformation";
	private static final String PASS_COL1 = "PASSWORD_ID";
	private static final String PASS_COL2 = "WHICH_PASSWORD";
	private static final String PASS_COL3 = "PASSWORD";

	private static final String PERSONAL_QUERY = "CREATE TABLE " + PERSONAL_TABLE + " ( " +
			PER_COL1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
			PER_COL2 + " TEXT NOT NULL, " +
			PER_COL3 + " TEXT NOT NULL, " +
			PER_COL4 + " TEXT NOT NULL, " +
			PER_COL5 + " TEXT NOT NULL, " +
			PER_COL6 + " TEXT)";

	private static final String BANK_QUERY = "CREATE TABLE " + BANK_TABLE + " ( " +
			BANK_COL1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
			BANK_COL2 + " TEXT NOT NULL, " +
			BANK_COL3 + " TEXT NOT NULL, " +
			BANK_COL4 + " TEXT NOT NULL, " +
			BANK_COL5 + " TEXT NOT NULL, " +
			BANK_COL6 + " TEXT, " +
			BANK_COL7 + " TEXT)";

	private static final String DOCUMENT_QUERY = "CREATE TABLE " + DOC_TABLE + " ( " +
			DOC_COL1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
			DOC_COL2 + " TEXT NOT NULL, " +
			DOC_COL3 + " TEXT NOT NULL)";

	private static final String PASSWORD_QUERY = "CREATE TABLE " + PASS_TABLE + " ( " +
			PASS_COL1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
			PASS_COL2 + " TEXT NOT NULL, " +
			PASS_COL3 + " TEXT NOT NULL)";

	private static final String REGISTRATION_QUERY = "CREATE TABLE " + REGISTRATION_TABLE + " ( " +
			REG_COL1 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
			REG_COL2 + " TEXT NOT NULL UNIQUE, " +
			REG_COL3 + " TEXT NOT NULL, " +
			REG_COL4 + " TEXT NOT NULL)";

	public DatabaseHelper(@Nullable Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(PERSONAL_QUERY);
		db.execSQL(BANK_QUERY);
		db.execSQL(DOCUMENT_QUERY);
		db.execSQL(PASSWORD_QUERY);
		db.execSQL(REGISTRATION_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for (String s : Arrays.asList(PERSONAL_TABLE, DOC_TABLE, PASS_TABLE, BANK_TABLE, REGISTRATION_TABLE)) {
			db.execSQL("DROP TABLE IF EXISTS " + s);
		}
		onCreate(db);
	}

	// Insert data into registration table
	public boolean insertDataIntoRegistrationTable(String userName, String userFullName, String userPassword) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(REG_COL2, userName);
		contentValues.put(REG_COL3, userFullName);
		contentValues.put(REG_COL4, userPassword);

		long res = db.insert(REGISTRATION_TABLE, null, contentValues);
		return res != -1;
	}

	// Retrieve data from registration table
	public boolean getDataFromRegistrationTable(String userName, String password) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + REGISTRATION_TABLE +
				" WHERE " + REG_COL2 + "=? AND " + REG_COL4 + "=?", new String[]{userName, password});
		boolean isValid = cursor.getCount() > 0;
		cursor.close();
		return isValid;
	}


	//TODO Insert data into personal table
	public boolean insertDataIntoPersonalTable(String fName, String lName, String dob, String phoneNumber, String email) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(PER_COL2, fName);
		contentValues.put(PER_COL3, lName);
		contentValues.put(PER_COL4, dob);
		contentValues.put(PER_COL5, phoneNumber);
		contentValues.put(PER_COL6, email);

		long res = db.insert(PERSONAL_TABLE, null, contentValues);
		return res != -1;
	}

	public List<PersonalCardAdapter.PersonalInfo> getAllPersonalInfo() {
		List<PersonalCardAdapter.PersonalInfo> personalInfoList = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(PERSONAL_TABLE, null, null, null, null, null, null);

		if (cursor != null) {
			while (cursor.moveToNext()) {
				@SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(PER_COL1));
				@SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex(PER_COL2));
				@SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex(PER_COL3));
				@SuppressLint("Range") String dateOfBirth = cursor.getString(cursor.getColumnIndex(PER_COL4));
				@SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex(PER_COL5));
				@SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(PER_COL6));

				PersonalCardAdapter.PersonalInfo personalInfo = new PersonalCardAdapter.PersonalInfo(id, firstName, lastName, dateOfBirth, phoneNumber, email);
				personalInfoList.add(personalInfo);
			}
			cursor.close();
		}
		return personalInfoList;
	}

	//TODO get data or retrieve data from bank table
	public boolean insertDataIntoBankTable(String bName, String aType, String aNumber, String ifscCode, String cId, String cIdPass) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(BANK_COL2, bName);
		contentValues.put(BANK_COL3, aType);
		contentValues.put(BANK_COL4, aNumber);
		contentValues.put(BANK_COL5, ifscCode);
		contentValues.put(BANK_COL6, cId);
		contentValues.put(BANK_COL7, cIdPass);

		long res = db.insert(BANK_TABLE, null, contentValues);
		return res != -1;
	}

	public List<BankCardAdapter.BankInfo> getAllBankInfo() {
		List<BankCardAdapter.BankInfo> bankInfoList = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(BANK_TABLE, null, null, null, null, null, null);

		if (cursor != null) {
			while (cursor.moveToNext()) {
				@SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(BANK_COL1));
				@SuppressLint("Range") String bankName = cursor.getString(cursor.getColumnIndex(BANK_COL2));
				@SuppressLint("Range") String accountType = cursor.getString(cursor.getColumnIndex(BANK_COL3));
				@SuppressLint("Range") String accountNumber = cursor.getString(cursor.getColumnIndex(BANK_COL4));
				@SuppressLint("Range") String ifscCode = cursor.getString(cursor.getColumnIndex(BANK_COL5));
				@SuppressLint("Range") String customerId = cursor.getString(cursor.getColumnIndex(BANK_COL6));
				@SuppressLint("Range") String customerIdPassword = cursor.getString(cursor.getColumnIndex(BANK_COL7));

				BankCardAdapter.BankInfo bankInfo = new BankCardAdapter.BankInfo(id, bankName, accountType, accountNumber, ifscCode, customerId, customerIdPassword);
				bankInfoList.add(bankInfo);
			}
			cursor.close();
		}
		return bankInfoList;
	}


	//TODO get data or retrieve data from document table
	public boolean insertDataIntoDocTable(String dName, String dNumber) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(DOC_COL2, dName);
		contentValues.put(DOC_COL3, dNumber);

		long res = db.insert(DOC_TABLE, null, contentValues);
		return res != -1;
	}

	public List<DocumentCardAdapter.DocumentInfo> getAllDocumentInfo() {
		List<DocumentCardAdapter.DocumentInfo> documentInfoList = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(DOC_TABLE, null, null, null, null, null, null);

		if (cursor != null) {
			while (cursor.moveToNext()) {
				@SuppressLint("Range") String id = String.valueOf(cursor.getInt(cursor.getColumnIndex(DOC_COL1)));
				@SuppressLint("Range") String docName = cursor.getString(cursor.getColumnIndex(DOC_COL2));
				@SuppressLint("Range") String docNumber = cursor.getString(cursor.getColumnIndex(DOC_COL3));
				DocumentCardAdapter.DocumentInfo documentInfo = new DocumentCardAdapter.DocumentInfo(id, docName, docNumber);
				documentInfoList.add(documentInfo);
			}
			cursor.close();
		}
		return documentInfoList;
	}


	//TODO get data or retrieve data from password table
	public boolean insertDataIntoPasswordTable(String pName, String password) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(PASS_COL2, pName);
		contentValues.put(PASS_COL3, password);

		long res = db.insert(PASS_TABLE, null, contentValues);
		return res != -1;
	}

	public List<PasswordCardAdapter.PasswordInfo> getAllPasswordInfo() {
		List<PasswordCardAdapter.PasswordInfo> passwordInfoList = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(PASS_TABLE, null, null, null, null, null, null);

		if (cursor != null) {
			while (cursor.moveToNext()) {
				@SuppressLint("Range") String id = String.valueOf(cursor.getInt(cursor.getColumnIndex(PASS_COL1)));
				@SuppressLint("Range") String passwordName = cursor.getString(cursor.getColumnIndex(PASS_COL2));
				@SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(PASS_COL3));

				PasswordCardAdapter.PasswordInfo passwordInfo = new PasswordCardAdapter.PasswordInfo(id, passwordName, password);
				passwordInfoList.add(passwordInfo);
			}
			cursor.close();
		}
		return passwordInfoList;
	}
}
