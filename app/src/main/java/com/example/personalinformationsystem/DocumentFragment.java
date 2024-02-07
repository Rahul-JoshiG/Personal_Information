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
import com.example.personalinformationsystem.cardadapters.DocumentCardAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DocumentFragment extends Fragment {
	private DocumentCardAdapter adapter;
	private RecyclerView recyclerView;
	private DatabaseHelper databaseHelper;

	public DocumentFragment() {
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
		adapter = new DocumentCardAdapter(getContext(), databaseHelper.getAllDocumentInfo());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		addNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialogForDocument();
			}
		});

		return view;
	}
	private void showDialogForDocument() {
		Dialog dialog = new Dialog(requireContext());
		dialog.setContentView(R.layout.dialog_add_document_layout);
		dialog.setCancelable(false);

		Button saveButton = dialog.findViewById(R.id.btnAddBankInfo);
		EditText documentNameEditText = dialog.findViewById(R.id.docName);
		EditText documentNumberEditText = dialog.findViewById(R.id.docNumber);
		ImageButton close = dialog.findViewById(R.id.closeDialog);

		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String documentName = documentNameEditText.getText().toString().trim().toUpperCase();
				String documentNumber = documentNumberEditText.getText().toString().trim();


				boolean isDataEmpty = documentName.isEmpty() || documentNumber.isEmpty();

				if (isDataEmpty) {
					Toast.makeText(requireContext(), "Please fill all details...", Toast.LENGTH_SHORT).show();
				} else {
					boolean isSaved = databaseHelper.insertDataIntoDocTable(documentName, documentNumber);

					if (isSaved) {
						Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show();
						adapter.setDocumentInfoList(databaseHelper.getAllDocumentInfo());
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