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

public class DocumentCardAdapter extends RecyclerView.Adapter<DocumentCardAdapter.DocumentInfoViewHolder> {

	private Context context;
	private List<DocumentCardAdapter.DocumentInfo> documentInfoList;

	public DocumentCardAdapter(Context context, List<DocumentInfo> documentInfoList) {
		this.context = context;
		this.documentInfoList = documentInfoList;
	}

	@NonNull
	@Override
	public DocumentCardAdapter.DocumentInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.document_card_layout, parent, false);
		return new DocumentInfoViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull DocumentCardAdapter.DocumentInfoViewHolder holder, int position) {
		DocumentCardAdapter.DocumentInfo documentInfo = documentInfoList.get(position);

		holder.viewDocName.setText(documentInfo.getDocName());
		holder.viewDocNumber.setText(documentInfo.getDocNumber());
	}

	@Override
	public int getItemCount() {
		return documentInfoList.size();
	}

	@SuppressLint("NotifyDataSetChanged")
	public void setDocumentInfoList(List<DocumentCardAdapter.DocumentInfo> documentInfoList) {
		this.documentInfoList = documentInfoList;
		notifyDataSetChanged();
	}

	static class DocumentInfoViewHolder extends RecyclerView.ViewHolder {
		TextView viewDocName, viewDocNumber;

		// Add more TextViews for other information as needed


		public DocumentInfoViewHolder(@NonNull View itemView) {
			super(itemView);
			viewDocName = itemView.findViewById(R.id.docName);
			viewDocNumber = itemView.findViewById(R.id.docNumber);
		}

	}

	public static class DocumentInfo {
		private String id;
		private String docName;
		private String docNumber;

		public DocumentInfo(String id, String docName, String docNumber) {
			this.id = id;
			this.docName = docName;
			this.docNumber = docNumber;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDocName() {
			return docName;
		}

		public void setDocName(String docName) {
			this.docName = docName;
		}

		public String getDocNumber() {
			return docNumber;
		}

		public void setDocNumber(String docNumber) {
			this.docNumber = docNumber;
		}
	}
}
