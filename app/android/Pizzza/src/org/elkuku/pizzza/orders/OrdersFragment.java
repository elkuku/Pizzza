package org.elkuku.pizzza.orders;

import org.elkuku.pizzza.R;
import org.elkuku.pizzza.types.TContact;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OrdersFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// if (container == null) {
		// // Currently in a layout without a container, so no
		// // reason to create our view.
		// Log.d("PIZZZA", "NO container...");
		// return null;
		// }

		View view = inflater.inflate(R.layout.orders, container, false);

		OrdersDataSource dataSource = new OrdersDataSource(getActivity());

		TContact contact = dataSource.getContact();

		TextView txtCompany = (TextView) view.findViewById(R.id.txtCompany);
		txtCompany.setText(contact.getName());

		TextView txtAddress = (TextView) view.findViewById(R.id.txtAddress);
		txtAddress.setText(contact.getAddress());

		TextView txtPhone1 = (TextView) view.findViewById(R.id.txtPhone1);
		txtPhone1.setText(contact.getPhone1());

		TextView txtPhone2 = (TextView) view.findViewById(R.id.txtPhone2);
		txtPhone2.setText(contact.getPhone2());

		TextView txtPhone3 = (TextView) view.findViewById(R.id.txtPhone3);
		txtPhone3.setText(contact.getPhone3());

		TextView txtWebsite = (TextView) view.findViewById(R.id.txtWebsite);
		txtWebsite.setText(contact.getWebpage());

		TextView txtEmail = (TextView) view.findViewById(R.id.txtEmail);
		txtEmail.setText(contact.getEmail());

		TextView txtMisc = (TextView) view.findViewById(R.id.txtMisc);
		txtMisc.setText(Html.fromHtml(contact.getMisc()));

		return view;
	}

}
