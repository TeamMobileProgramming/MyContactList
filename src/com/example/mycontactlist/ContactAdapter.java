package com.example.mycontactlist;

import android.graphics.Color;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<Contact> {

	private ArrayList<Contact> items;
	private Context adapterContext;

	public ContactAdapter(Context context, ArrayList<Contact> items) {
		super(context, R.layout.list_item, items);
		adapterContext = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		try {
			Contact contact = items.get(position);
			final int[] colors = new int[] { Color.parseColor("#FF0000"), Color.parseColor("#0000FF") };

			if (v == null) {
				LayoutInflater vi = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.list_item, null);
			}

			TextView contactName = (TextView) v.findViewById(R.id.textContactName);
			TextView contactHomeNumber = (TextView) v.findViewById(R.id.textHomeNumber);
			TextView contactCellNumber = (TextView) v.findViewById(R.id.textCellNumber);
			TextView street = (TextView) v.findViewById(R.id.street);
			TextView city = (TextView) v.findViewById(R.id.city);
			TextView state = (TextView) v.findViewById(R.id.state);
			TextView zip = (TextView) v.findViewById(R.id.zip);

			Button b = (Button) v.findViewById(R.id.buttonDeleteContact);

			contactName.setText(contact.getContactName());

			int pos = position % 2;
			contactName.setTextColor(colors[pos]);

			contactHomeNumber.setText(contact.getPhoneNumber());
			contactCellNumber.setText(contact.getCellNumber());
			street.setText(contact.getStreetAddress() + "     ");
			city.setText(contact.getCity() + "   ");
			state.setText(contact.getState() + "   ");
			zip.setText(contact.getZipCode() + "   ");

			b.setVisibility(View.INVISIBLE);
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return v;
	}

	public void showDelete(final int position, final View convertView, final Context context, final Contact contact) {
		View v = convertView;
		final Button b = (Button) v.findViewById(R.id.buttonDeleteContact);

		if (b.getVisibility() == View.INVISIBLE) {
			b.setVisibility(View.VISIBLE);
			b.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					hideDelete(position, convertView, context);
					items.remove(contact);
					deleteOption(contact.getContactID(), context);
				}

			});
		} else {
			hideDelete(position, convertView, context);
		}
	}

	private void deleteOption(int contactToDelete, Context context) {
		ContactDataSource db = new ContactDataSource(context);
		db.open();
		db.deleteContact(contactToDelete);
		db.close();
		this.notifyDataSetChanged();
	}

	private void hideDelete(int position, View convertView, Context context) {
		View v = convertView;
		final Button b = (Button) v.findViewById(R.id.buttonDeleteContact);
		b.setVisibility(View.INVISIBLE);
		b.setOnClickListener(null);
	}

}
