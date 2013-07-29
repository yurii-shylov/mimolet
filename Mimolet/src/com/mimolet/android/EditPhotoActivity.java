package com.mimolet.android;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class EditPhotoActivity extends SherlockActivity {

	public static String IS_LEFT = "isLeft";

	private boolean isLeft;

	private ImageButton[] bottomTabs;
	private int[] bottomTabsSelectedResources;
	private int[] bottomTabsNormalResources;

	private LinearLayout chooseLayoutPopup;
	private LinearLayout chooseBackgroundPopup;
	private LinearLayout addTextPopup;

	private RadioGroup.OnCheckedChangeListener fontToggleListener = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
			for (int j = 0; j < radioGroup.getChildCount(); j++) {
				final ToggleButton view = (ToggleButton) radioGroup
						.getChildAt(j);
				view.setChecked(view.getId() == i);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_photo);

		isLeft = getIntent().getBooleanExtra(IS_LEFT, true);

		bottomTabs = new ImageButton[4];
		bottomTabs[0] = (ImageButton) findViewById(R.id.chooseLayoutTab);
		bottomTabs[1] = (ImageButton) findViewById(R.id.chooseBackgroundTab);
		bottomTabs[2] = (ImageButton) findViewById(R.id.addTextTab);
		bottomTabs[3] = (ImageButton) findViewById(R.id.changePhotoTab);
		bottomTabsNormalResources = new int[] { R.drawable.edit_photo_tab1,
				R.drawable.edit_photo_tab2, R.drawable.edit_photo_tab3,
				R.drawable.edit_photo_tab4 };
		bottomTabsSelectedResources = new int[] {
				R.drawable.edit_photo_tab1_selected,
				R.drawable.edit_photo_tab2_selected,
				R.drawable.edit_photo_tab3_selected,
				R.drawable.edit_photo_tab4_selected };

		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.edit_photo_menu);
		getSupportActionBar().getCustomView().findViewById(R.id.action_bar_no)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						EditPhotoActivity.this.onBackPressed();
					}
				});
		getSupportActionBar().getCustomView().findViewById(R.id.action_bar_yes)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(EditPhotoActivity.this, "Yes", 1000)
								.show();
					}
				});

		chooseLayoutPopup = (LinearLayout) findViewById(R.id.popup_layout);
		chooseBackgroundPopup = (LinearLayout) findViewById(R.id.popup_background);
		addTextPopup = (LinearLayout) findViewById(R.id.popup_text);

		((RadioGroup) findViewById(R.id.font_toggle_group))
				.setOnCheckedChangeListener(fontToggleListener);
	}

	private void renderSelectedButton(int selectedButtonId) {
		for (int i = 0; i < bottomTabs.length; i++) {
			final ImageButton bottomTab = bottomTabs[i];
			if (bottomTab.getId() == selectedButtonId) {
				bottomTab.setBackgroundResource(bottomTabsSelectedResources[i]);
			} else {
				bottomTab.setBackgroundResource(bottomTabsNormalResources[i]);
			}
		}
	}

	public void bottomMenuButtonClick(View view) {
		switch (view.getId()) {
		case R.id.chooseLayoutTab:
			chooseLayoutPopup.setVisibility(LinearLayout.VISIBLE);
			chooseBackgroundPopup.setVisibility(LinearLayout.INVISIBLE);
			addTextPopup.setVisibility(LinearLayout.INVISIBLE);
			break;
		case R.id.chooseBackgroundTab:
			chooseLayoutPopup.setVisibility(LinearLayout.INVISIBLE);
			chooseBackgroundPopup.setVisibility(LinearLayout.VISIBLE);
			addTextPopup.setVisibility(LinearLayout.INVISIBLE);
			break;
		case R.id.addTextTab:
			chooseLayoutPopup.setVisibility(LinearLayout.INVISIBLE);
			chooseBackgroundPopup.setVisibility(LinearLayout.INVISIBLE);
			addTextPopup.setVisibility(LinearLayout.VISIBLE);
			break;
		case R.id.changePhotoTab:
		}
		renderSelectedButton(view.getId());
	}

	public void popupOkClick(View view) {
		switch (view.getId()) {
		case R.id.choose_layout_ok:
			chooseLayoutPopup.setVisibility(LinearLayout.INVISIBLE);
			break;
		case R.id.choose_background_ok:
			chooseBackgroundPopup.setVisibility(LinearLayout.INVISIBLE);
			break;
		case R.id.add_text_ok:
			addTextPopup.setVisibility(LinearLayout.INVISIBLE);
			break;
		}

		renderSelectedButton(-1);
	}

	public void fontToggle(View view) {
		((RadioGroup) view.getParent()).check(view.getId());
		// app specific stuff ..
	}
}