package com.example.gridimagesearch;

import com.example.gridimagesearch.EditSettingsDialog.EditNameDialogListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SettingsActivity extends FragmentActivity implements EditNameDialogListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showEditDialog();
	}

	private void showEditDialog() {
		FragmentManager fm = getSupportFragmentManager();
	    EditSettingsDialog editNameDialog = EditSettingsDialog.newInstance("Advanced search options");
	    editNameDialog.show(fm, "fragment_edit_settings");
	}

	@Override
	public void onFinishEditDialog(String size, String color, String type, String site) {
		Intent data = new Intent();
		data.putExtra("size" , size);
		data.putExtra("color", color);
		data.putExtra("type",  type);
		data.putExtra("site",  site);
		setResult(RESULT_OK, data);
		finish();
	}
	
}
