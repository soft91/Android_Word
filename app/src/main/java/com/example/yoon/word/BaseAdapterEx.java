package com.example.yoon.word;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
//����Ʈ�� ��µǴ� �� ��.
public class BaseAdapterEx extends BaseAdapter {

	Context mContext = null;
	ArrayList<words> mData = null;
	LayoutInflater mLayoutInflater = null;

	public BaseAdapterEx(Context context, ArrayList<words> data) {
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

	public words getItem(int position) {
		return mData.get(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View itemlayout = mLayoutInflater.inflate(R.layout.list_view, null);

		TextView tv_1 = (TextView) itemlayout.findViewById(R.id.tv_1);
		TextView tv_2 = (TextView) itemlayout.findViewById(R.id.tv_2);

		tv_1.setText(mData.get(position).words_name);
		tv_2.setText(mData.get(position).words_mean);

		return itemlayout;
	}
}