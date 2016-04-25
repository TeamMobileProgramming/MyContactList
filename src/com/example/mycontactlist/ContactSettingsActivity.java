package com.example.mycontactlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.ScrollView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ContactSettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_settings);

		ImageButton imageButtonSettings = (ImageButton) findViewById(R.id.imageButtonSettings);
		imageButtonSettings.setEnabled(false);
		initListButton();
		//initMapButton();
		initSettings();
		initSortByClick();
		initSortOrderClick();
		initChangeColor();
		loadColorOnLoad();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_settings, menu);
		return true;
	}

	private void initListButton() {
		ImageButton list = (ImageButton) findViewById(R.id.imageButtonList);
		list.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

	/*private void initMapButton() {
		ImageButton list = (ImageButton) findViewById(R.id.imageButtonMap);
		list.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}*/

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initSettings() {
		String sortBy = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortfield",
				"contactname");
		String sortOrder = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortorder",
				"ASC");
		String color = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("color", "red");

		RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
		RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
		RadioButton rbBirthDay = (RadioButton) findViewById(R.id.radioBirthday);
		if (sortBy.equalsIgnoreCase("contactname")) {
			rbName.setChecked(true);
		} else if (sortBy.equalsIgnoreCase("city")) {
			rbCity.setChecked(true);
		} else {
			rbBirthDay.setChecked(true);
		}

		RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
		RadioButton rbDescending = (RadioButton) findViewById(R.id.radioDescending);
		if (sortOrder.equalsIgnoreCase("ASC")) {
			rbAscending.setChecked(true);
		} else {
			rbDescending.setChecked(true);
		}

		RadioButton rbRed = (RadioButton) findViewById(R.id.radioRed);
		RadioButton rbGreen = (RadioButton) findViewById(R.id.radioGreen);
		RadioButton rbBlue = (RadioButton) findViewById(R.id.radioBlue);

		if (color.equalsIgnoreCase("red")) {
			rbRed.setChecked(true);
		} else if (color.equalsIgnoreCase("green")) {
			rbGreen.setChecked(true);
		} else {
			rbBlue.setChecked(true);
		}
	}

	private void initChangeColor() {
		RadioGroup rgChangeColor = (RadioGroup) findViewById(R.id.radioGroup3);
		rgChangeColor.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				RadioButton rbRed = (RadioButton) findViewById(R.id.radioRed);
				RadioButton rbGreen = (RadioButton) findViewById(R.id.radioGreen);
				ScrollView layout1 = (ScrollView) findViewById(R.id.scrollView1);

				if (rbRed.isChecked()) {
					layout1.setBackgroundResource(R.color.color_red);
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
							.putString("color", "Red").commit();
				} else if (rbGreen.isChecked()) {
					layout1.setBackgroundResource(R.color.color_green);
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
							.putString("color", "Green").commit();
				} else {
					layout1.setBackgroundResource(R.color.color_blue);
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
							.putString("color", "Blue").commit();
				}
			}
		});
	}

	private void loadColorOnLoad() {

		RadioButton rbRed = (RadioButton) findViewById(R.id.radioRed);
		RadioButton rbGreen = (RadioButton) findViewById(R.id.radioGreen);
		ScrollView layout1 = (ScrollView) findViewById(R.id.scrollView1);

		if (rbRed.isChecked()) {
			layout1.setBackgroundResource(R.color.color_red);
			getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("color", "Red")
					.commit();
		} else if (rbGreen.isChecked()) {
			layout1.setBackgroundResource(R.color.color_green);
			getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("color", "Green")
					.commit();
		} else {
			layout1.setBackgroundResource(R.color.color_blue);
			getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("color", "Blue")
					.commit();
		}
	}

	private void initSortByClick() {
		RadioGroup rgSortBy = (RadioGroup) findViewById(R.id.radioGroup1);
		rgSortBy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
				RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
				// RadioButton rbBirthDay = (RadioButton)
				// findViewById(R.id.radioBirthdate);
				if (rbName.isChecked()) {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
							.putString("sortfield", "contactname").commit();
				} else if (rbCity.isChecked()) {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
							.putString("sortfield", "city").commit();
				} else {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
							.putString("sortfield", "birthday").commit();
				}
			}
		});
	}

	private void initSortOrderClick() {
		RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroup2);
		rgSortOrder.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
				// RadioButton rbDescending = (RadioButton)
				// findViewById(R.id.radioDescending);
				if (rbAscending.isChecked()) {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
							.putString("sortorder", "ASC").commit();
				} else {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit()
							.putString("sortorder", "DESC").commit();
				}
			}
		});
	}
}
