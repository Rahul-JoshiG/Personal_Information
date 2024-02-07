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

public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.BankInfoViewHolder>
{

	private Context context;
	private List<BankInfo> bankInfoList;

	public BankCardAdapter(Context context, List<BankInfo> bankInfoList) {
		this.context = context;
		this.bankInfoList = bankInfoList;
	}

	@NonNull
	@Override
	public BankInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.bank_card_layout, parent, false);
		return new BankInfoViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull BankInfoViewHolder holder, int position) {

		BankInfo bankInfo = bankInfoList.get(position);

		holder.viewBankName.setText(bankInfo.getBankName());
		holder.viewAccountType.setText(bankInfo.getAccountType());
		holder.viewAccountNumber.setText(bankInfo.getAccountNumber());
		holder.viewIfsc.setText(bankInfo.getIfscCode());
		holder.viewCustomer.setText(bankInfo.getCustomerId());
		holder.viewCustomerPassword.setText(bankInfo.getCustomerPassword());
	}

	@SuppressLint("NotifyDataSetChanged")
	public void setBankInfoList(List<BankInfo> bankInfoList) {
		this.bankInfoList = bankInfoList;
		notifyDataSetChanged();
	}


	@Override
	public int getItemCount() {
		return bankInfoList.size();
	}

	static class BankInfoViewHolder extends RecyclerView.ViewHolder {
		TextView viewBankName, viewAccountType, viewAccountNumber, viewIfsc, viewCustomer, viewCustomerPassword;
		public BankInfoViewHolder(@NonNull View itemView) {
			super(itemView);
			viewBankName = itemView.findViewById(R.id.bankName);
			viewAccountType = itemView.findViewById(R.id.accountType);
			viewAccountNumber = itemView.findViewById(R.id.accountNumber);
			viewIfsc = itemView.findViewById(R.id.ifscCode);
			viewCustomer = itemView.findViewById(R.id.customerId);
			viewCustomerPassword = itemView.findViewById(R.id.customerIdPassword);

		}
	}

	public static class BankInfo {
		private int id;
		private String bankName;
		private String accountType;
		private String accountNumber;
		private String ifscCode;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public String getIfscCode() {
			return ifscCode;
		}

		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getCustomerPassword() {
			return customerPassword;
		}

		public void setCustomerPassword(String customerPassword) {
			this.customerPassword = customerPassword;
		}

		private String customerId;
		private String customerPassword;

		public BankInfo(int id, String bankName, String accountType, String accountNumber, String ifscCode, String customerId, String customerPassword) {
			this.id = id;
			this.bankName = bankName;
			this.accountType = accountType;
			this.accountNumber = accountNumber;
			this.ifscCode = ifscCode;
			this.customerId = customerId;
			this.customerPassword = customerPassword;
		}
	}
}
