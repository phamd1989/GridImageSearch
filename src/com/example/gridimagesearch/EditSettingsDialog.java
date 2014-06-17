package com.example.gridimagesearch;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class EditSettingsDialog extends DialogFragment implements OnEditorActionListener{

	private EditText imgSizeEditText;
	private EditText colorFilterEditText;
	private EditText imgTypeEditText;
	private EditText siteFilterEditText;

	public EditSettingsDialog() {
		// Empty constructor required for DialogFragment
	}
	
	
	public interface EditNameDialogListener {
        void onFinishEditDialog(String size, String color, String type, String site);
    }
	
	
	public static EditSettingsDialog newInstance(String title) {
		EditSettingsDialog frag = new EditSettingsDialog();
		Bundle args = new Bundle();
		args.putString("title", title);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_edit_settings, container);
		imgSizeEditText     = (EditText) view.findViewById(R.id.etSize);
		colorFilterEditText = (EditText) view.findViewById(R.id.etColorFilter);
		imgTypeEditText     = (EditText) view.findViewById(R.id.etImageType);
		siteFilterEditText  = (EditText) view.findViewById(R.id.etSiteFilter);
		String title = getArguments().getString("title", "Enter Name");
		getDialog().setTitle(title);
		
		// Show soft keyboard automatically
		imgSizeEditText.requestFocus();
		imgSizeEditText.setOnEditorActionListener(this);
		colorFilterEditText.setOnEditorActionListener(this);
		imgTypeEditText.setOnEditorActionListener(this);
		siteFilterEditText.setOnEditorActionListener(this);
		getDialog().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		return view;
	}

	// Fires whenever the textfield has an action performed
    // In this case, when the "Done" button is pressed
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener listener = (EditNameDialogListener) getActivity();
            listener.onFinishEditDialog(imgSizeEditText.getText().toString(),
            							colorFilterEditText.getText().toString(),
            							imgTypeEditText.getText().toString(),
            							siteFilterEditText.getText().toString());
            dismiss();
            return true;
        }
        return false;
    }
}
