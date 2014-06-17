package com.example.gridimagesearch;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultArrayAdapter(Context context, List<ImageResult> objects) {
		super(context, R.layout.item_image_result, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult img = this.getItem(position);
		SmartImageView ivView;
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			ivView = (SmartImageView) inflator.inflate(R.layout.item_image_result, parent, false);
		} else {
			ivView = (SmartImageView) convertView;
			ivView.setImageResource(android.R.color.transparent);
		}
		ivView.setImageUrl(img.getThumbUrl());
		return ivView;
	}
}
