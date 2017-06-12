package com.example.yoon.word;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
public class BaseAdapterEx2 extends BaseAdapter {

	Context mContext = null;
	ArrayList<words2> mData = null;
	LayoutInflater mLayoutInflater = null;

	public BaseAdapterEx2(Context context, ArrayList<words2> data) {
		mContext = context;
		mData = data;
		mLayoutInflater = LayoutInflater.from(mContext);
	}

	public int getCount() {
		return mData.size();
	}

	public long getItemId(int position) {
		return position;
	}

	public void remove(int position){
		mData.remove(position);
	}
	public words2 getItem(int position) {
		return mData.get(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View itemlayout = mLayoutInflater.inflate(R.layout.list_view2, null);

		TextView tv_1 = (TextView) itemlayout.findViewById(R.id.tv_12);
		CheckBox cb_2 = (CheckBox) itemlayout.findViewById(R.id.checkbox);

		tv_1.setText(mData.get(position).words_name);
		cb_2.setChecked(mData.get(position).select);

		return itemlayout;
	}
}