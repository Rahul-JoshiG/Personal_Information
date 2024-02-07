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

public class PasswordCardAdapter extends RecyclerView.Adapter<PasswordCardAdapter.PasswordInfoViewHolder> {

	private Context context;
	private List<PasswordCardAdapter.PasswordInfo> passwordInfoList;

	public PasswordCardAdapter(Context context, List<PasswordInfo> passwordInfoList) {
		this.context = context;
		this.passwordInfoList = passwordInfoList;
	}

	@NonNull
	@Override
	public PasswordCardAdapter.PasswordInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.password_card_layout, parent, false);
		return new PasswordInfoViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PasswordCardAdapter.PasswordInfoViewHolder holder, int position) {
		PasswordCardAdapter.PasswordInfo passwordInfo = passwordInfoList.get(position);

		holder.viewPasswordName.setText(passwordInfo.getPasswordName());
		holder.viewPassword.setText(passwordInfo.getPassword());
	}

	@Override
	public int getItemCount() {
		return passwordInfoList.size();
	}

	@SuppressLint("NotifyDataSetChanged")
	public void setPasswordInfoList(List<PasswordCardAdapter.PasswordInfo> passwordInfoList) {
		this.passwordInfoList = passwordInfoList;
		notifyDataSetChanged();
	}

	static class PasswordInfoViewHolder extends RecyclerView.ViewHolder {
		TextView viewPasswordName, viewPassword;

		// Add more TextViews for other information as needed
		public PasswordInfoViewHolder(@NonNull View itemView) {
			super(itemView);
			viewPasswordName = itemView.findViewById(R.id.passwordName);
			viewPassword = itemView.findViewById(R.id.password);
		}

	}

	public static class PasswordInfo {
		private String id;
		private String passwordName;
		private String password;

		public PasswordInfo(String id, String passwordName, String password) {
			this.id = id;
			this.passwordName = passwordName;
			this.password = password;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPasswordName() {
			return passwordName;
		}

		public void setPasswordName(String passwordName) {
			this.passwordName = passwordName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
