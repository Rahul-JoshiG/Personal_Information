package com.example.personalinformationsystem.cardadapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalinformationsystem.R;

import java.util.List;

public class PersonalCardAdapter extends RecyclerView.Adapter<PersonalCardAdapter.PersonalInfoViewHolder> {

	private Context context;
	private List<PersonalInfo> personalInfoList;


	public PersonalCardAdapter(Context context, List<PersonalInfo> personalInfoList) {
		this.context = context;
		this.personalInfoList = personalInfoList;
	}

	@NonNull
	@Override
	public PersonalInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.personal_card_layout, parent, false);
		return new PersonalInfoViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PersonalInfoViewHolder holder, int position) {
		PersonalInfo personalInfo = personalInfoList.get(position);

		holder.viewFirstName.setText(personalInfo.getFirstName());
		holder.viewLastName.setText(personalInfo.getLastName());
		holder.viewDOB.setText(personalInfo.getDataOfBirth());
		holder.viewPhone.setText(personalInfo.getPhoneNumber());
		holder.viewEmail.setText(personalInfo.getEmail());

		// Set other TextViews based on the PersonalInfo model

		// Add more bindings for other information as needed
	}
	@SuppressLint("NotifyDataSetChanged")
	public void setPersonalInfoList(List<PersonalInfo> personalInfoList) {
		this.personalInfoList = personalInfoList;
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return personalInfoList.size();
	}

	static class PersonalInfoViewHolder extends RecyclerView.ViewHolder {
		TextView viewFirstName, viewLastName, viewDOB, viewPhone, viewEmail;

		// Add more TextViews for other information as needed

		PersonalInfoViewHolder(@NonNull View itemView) {
			super(itemView);
			viewFirstName = itemView.findViewById(R.id.personFirstName);
			viewLastName = itemView.findViewById(R.id.personLastName);
			viewPhone = itemView.findViewById(R.id.personPhone);
			viewDOB = itemView.findViewById(R.id.personDob);
			viewEmail = itemView.findViewById(R.id.personEmail);
			// Initialize other TextViews here
		}
	}
	public static class PersonalInfo {
		private int id;
		private String firstName;
		private String lastName;
		private String dataOfBirth;
		private String phoneNumber;
		private String email;

		public PersonalInfo(int id, String firstName, String lastName, String dataOfBirth, String phoneNumber, String email) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.dataOfBirth = dataOfBirth;
			this.phoneNumber = phoneNumber;
			this.email = email;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getDataOfBirth() {
			return dataOfBirth;
		}

		public void setDataOfBirth(String dataOfBirth) {
			this.dataOfBirth = dataOfBirth;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
}
