package com.skd.bluetoothcontroller.adapters;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skd.bluetoothcontroller.R;
import com.skd.bluetoothcontroller.entity.Message;

public class MessageAdapter extends BaseAdapter {
	
	static class ViewHolder {
		
		TextView mTextMessage;
		TextView mTextSentAt;
		
		public ViewHolder(View view) {
			mTextMessage = (TextView) view.findViewById(R.id.textMessageText);
			mTextSentAt = (TextView) view.findViewById(R.id.textSentAt);
		}
	}
	
	private List<Message> mMessages;
	
	public MessageAdapter() {
		mMessages = new ArrayList<Message>();
	}
	
	public void addMessage(Message message){
		mMessages.add(message);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mMessages != null ? mMessages.size() : 0;
	}

	@Override
	public Message getItem(int position) {
		return mMessages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = createNewView(parent);
		}
		
		ViewHolder holder = (ViewHolder) convertView.getTag();
		Message message = getItem(position);
		
		holder.mTextMessage.setText(message.getMessage());
		holder.mTextSentAt.setText(message.getSentAt());
		
		return convertView;
	}

	private View createNewView(ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.list_item_message, parent, false);
		ViewHolder holder = new ViewHolder(view);
		view.setTag(holder);
		return view;
	}
}