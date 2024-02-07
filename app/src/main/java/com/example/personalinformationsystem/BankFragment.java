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

import com.example.personalinformationsystem.cardadapters.BankCardAdapter;
import com.example.personalinformationsystem.cardadapters.PersonalCardAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BankFragment extends Fragment {

	private BankCardAdapter adapter;
	private RecyclerView recyclerView;
	private DatabaseHelper databaseHelper;
	public BankFragment() {
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
		adapter = new BankCardAdapter(getContext(), databaseHelper.getAllBankInfo());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		addNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialogForBank();
			}
		});

		return view;
	}

	private void showDialogForBank() {
		Dialog dialog = new Dialog(requireContext());
		dialog.setContentView(R.layout.dialog_add_bank_layout);
		dialog.setCancelable(false);

		Button saveButton = dialog.findViewById(R.id.btnAddBankInfo);
		EditText bankNameEditText = dialog.findViewById(R.id.bankName);
		EditText accountTypeEditText = dialog.findViewById(R.id.accountType);
		EditText accountNumberEditText = dialog.findViewById(R.id.accountNumber);
		EditText ifscCodeEditText = dialog.findViewById(R.id.ifscCode);
		EditText customerIdEditText = dialog.findViewById(R.id.customerId);
		EditText customerIdPasswordEditText = dialog.findViewById(R.id.customerIdPassword);
		ImageButton close = dialog.findViewById(R.id.closeDialog);

		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String bankName = bankNameEditText.getText().toString().trim().toUpperCase();
				String accountType = accountTypeEditText.getText().toString().trim().toUpperCase();
				String accountNumber = accountNumberEditText.getText().toString().trim();
				String ifscCode = ifscCodeEditText.getText().toString().trim();
				String customerId = customerIdEditText.getText().toString().trim();
				String customerIdPassword = customerIdPasswordEditText.getText().toString();

				boolean isDataEmpty = bankName.isEmpty() || accountType.isEmpty() || accountNumber.isEmpty() || ifscCode.isEmpty();

				if (isDataEmpty) {
					Toast.makeText(requireContext(), "Please fill all details...", Toast.LENGTH_SHORT).show();
				} else {
					boolean isSaved = databaseHelper.insertDataIntoBankTable(bankName, accountType, accountNumber, ifscCode, customerId, customerIdPassword);

					if (isSaved) {
						Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show();
						adapter.setBankInfoList(databaseHelper.getAllBankInfo());
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