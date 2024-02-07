package com.example.personalinformationsystem;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalinformationsystem.cardadapters.PersonalCardAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PersonalFragment extends Fragment {

	private PersonalCardAdapter adapter;
	private DatabaseHelper databaseHelper;
	private RecyclerView recyclerView;

	public PersonalFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_personal, container, false);
		FloatingActionButton addNew = view.findViewById(R.id.addNew);

		recyclerView = view.findViewById(R.id.recyclerView);

		// Initialize the DatabaseHelper
		databaseHelper = DatabaseHelperSingleton.getInstance(requireContext());

		// Initialize the adapter with data from the database
		adapter = new PersonalCardAdapter(getContext(), databaseHelper.getAllPersonalInfo());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		addNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialogForPersonal();
			}
		});

		return view;
	}

	private void showDialogForPersonal() {
		Dialog dialog = new Dialog(requireContext());
		dialog.setContentView(R.layout.dialog_add_personal_info);
		dialog.setCancelable(false);

		Button saveButton = dialog.findViewById(R.id.btnAddPersonalInfo);
		EditText firstNameEditText = dialog.findViewById(R.id.firstName);
		EditText lastNameEditText = dialog.findViewById(R.id.lastName);
		EditText dobEditText = dialog.findViewById(R.id.dob);
		EditText phoneEditText = dialog.findViewById(R.id.phoneNumber);
		EditText emailEditText = dialog.findViewById(R.id.email);
		ImageButton close = dialog.findViewById(R.id.closeDialog);

		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String firstName = firstNameEditText.getText().toString().trim();
				String lastName = lastNameEditText.getText().toString().trim();
				String dob = dobEditText.getText().toString().trim();
				String phone = phoneEditText.getText().toString().trim();
				String email = emailEditText.getText().toString().trim();

				boolean isDataEmpty = firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty() || email.isEmpty() || phone.isEmpty();

				if (isDataEmpty) {
					Toast.makeText(requireContext(), "Please fill all details...", Toast.LENGTH_SHORT).show();
				} else {
					boolean isSaved = databaseHelper.insertDataIntoPersonalTable(firstName, lastName, dob, phone, email);

					if (isSaved) {
						Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show();
						// Update the adapter data after saving
						adapter.setPersonalInfoList(databaseHelper.getAllPersonalInfo());

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
