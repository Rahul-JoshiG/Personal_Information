package com.example.personalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class LogInActivity extends AppCompatActivity {

	private MaterialButton logInButton;
	private MaterialTextView signUpText, forgetPassword;
	private TextInputEditText userName, userPassword;
	private RelativeLayout loginPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);

		logInButton = findViewById(R.id.logInButton);
		userName = findViewById(R.id.loginUserName);
		userPassword = findViewById(R.id.loginPassword);
		signUpText = findViewById(R.id.registerText);
//		forgetPassword = findViewById(R.id.forgetPassword);
		loginPage = findViewById(R.id.loginPage);

		signUpText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
				finish();
				startActivity(intent);
			}
		});

		logInButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String userNameStr = userName.getText().toString().toUpperCase();
				String userPasswordStr = userPassword.getText().toString();

				if (checkEmptyData(userNameStr, userPasswordStr)) {
					if (checkCredentials(userNameStr, userPasswordStr)) {
						// Credentials match, perform further actions
//						Toast.makeText(LogInActivity.this, "Log in successful!", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
						finish();
						startActivity(intent);
					} else {
						// Credentials do not match
						Toast.makeText(LogInActivity.this, "Invalid user or password. Please try again.", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					Toast.makeText(LogInActivity.this, "Please fill all details...", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private boolean checkCredentials(String userName, String userPassword) {
		// Use the singleton instance of DatabaseHelper
		DatabaseHelper databaseHelper = DatabaseHelperSingleton.getInstance(LogInActivity.this);
		return databaseHelper.getDataFromRegistrationTable(userName, userPassword);
	}

	private boolean checkEmptyData(String userNameStr, String userPasswordStr) {
		return !userNameStr.isEmpty() && !userPasswordStr.isEmpty();
	}
}
