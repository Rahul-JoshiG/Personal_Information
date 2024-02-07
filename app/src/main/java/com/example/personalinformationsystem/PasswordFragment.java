package com.example.personalinformationsystem;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.personalinformationsystem.cardadapters.DocumentCardAdapter;
import com.example.personalinformationsystem.cardadapters.PasswordCardAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class PasswordFragment extends Fragment {

	private PasswordCardAdapter adapter;
	private RecyclerView recyclerView;
	private DatabaseHelper databaseHelper;

	public PasswordFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_bank, container, false);
		FloatingActionButton addNew = view.findViewById(R.id.addNew);

		recyclerView = view.findViewById(R.id.recyclerView);

		// Initialize the DatabaseHelper
		databaseHelper = DatabaseHelperSingleton.getInstance(requireContext());

		// Initialize the adapter with data from the database
		adapter = new PasswordCardAdapter(getContext(), databaseHelper.getAllPasswordInfo());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		addNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialogForPassword();
			}
		});

		return view;
	}

	private void showDialogForPassword() {
		Dialog dialog = new Dialog(requireContext());
		dialog.setContentView(R.layout.dialog_add_password_layout);
		dialog.setCancelable(false);

		Button saveButton = dialog.findViewById(R.id.btnAddBankInfo);
		EditText passwordNameEditText = dialog.findViewById(R.id.passwordName);
		EditText passwordEditText = dialog.findViewById(R.id.password);
		ImageButton close = dialog.findViewById(R.id.closeDialog);

		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String passwordName = passwordNameEditText.getText().toString().trim().toUpperCase();
				String password = passwordEditText.getText().toString().trim();

				boolean isDataEmpty = passwordName.isEmpty() || password.isEmpty();

				if (isDataEmpty) {
					Toast.makeText(requireContext(), "Please fill all details...", Toast.LENGTH_SHORT).show();
				} else {
					boolean isSaved = databaseHelper.insertDataIntoPasswordTable(passwordName, password);
					if (isSaved) {
						Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show();
						adapter.setPasswordInfoList(databaseHelper.getAllPasswordInfo());
					} else {
						Toast.makeText(requireContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
					}
					dialog.dismiss();
				}
			}
		});

		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}
}