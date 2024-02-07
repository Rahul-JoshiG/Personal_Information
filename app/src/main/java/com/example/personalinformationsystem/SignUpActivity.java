package com.example.personalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class SignUpActivity extends AppCompatActivity {

	private DatabaseHelper databaseHelper;
	private MaterialTextView signIn;
	private TextInputEditText userName, userFullName, userPassword;
	private MaterialButton signUpButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		signIn = findViewById(R.id.signInText);
		userName = findViewById(R.id.signUpUserName);
		userFullName = findViewById(R.id.signUpFullName);
		userPassword = findViewById(R.id.signUpPassword);
		signUpButton = findViewById(R.id.signUpButton);

		databaseHelper = new DatabaseHelper(this);

		signUpButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String userNameStr = userName.getText().toString().toUpperCase();
				String userFullNameStr = userFullName.getText().toString().toUpperCase();
				String userPasswordStr = userPassword.getText().toString();

				if (checkEmptyData(userNameStr, userFullNameStr, userPasswordStr)) {
					boolean isDataInserted = databaseHelper.insertDataIntoRegistrationTable(userNameStr, userFullNameStr, userPasswordStr);

					if (isDataInserted) {
						// Send data to LogInActivity
						Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
						intent.putExtra("userName", userNameStr);
						intent.putExtra("password", userPasswordStr);
						startActivity(intent);
						finish();
					} else {
						// Data insertion failed
						Toast.makeText(SignUpActivity.this, "Sorry, something went wrong...", Toast.LENGTH_SHORT).show();
					}
				} else {
					// Empty data
					Toast.makeText(SignUpActivity.this, "Please fill all details...", Toast.LENGTH_SHORT).show();
				}
			}
		});

		signIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
				startActivity(intent);
			}
		});
	}

	private boolean checkEmptyData(String userNameStr, String userFullNameStr, String userPasswordStr) {
		return !userNameStr.isEmpty() && !userFullNameStr.isEmpty() && !userPasswordStr.isEmpty();
	}
}
