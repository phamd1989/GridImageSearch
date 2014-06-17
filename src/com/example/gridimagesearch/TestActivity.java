package com.example.gridimagesearch;

import android.app.Activity;
import android.os.Bundle;

import com.loopj.android.image.SmartImageView;

public class TestActivity extends Activity {
	
	SmartImageView siView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		String url = getIntent().getStringExtra("obj");
		
//		siView = (SmartImageView) findViewById(R.layout.activity_test);
//		siView.setImageUrl(url);
	}
}
