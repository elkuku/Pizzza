package org.elkuku.pizzza;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class PedidosActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.pedidos);

		PedidosDataSource dataSource = new PedidosDataSource(getApplicationContext());
		TContact contact = dataSource.getContact();

		TextView txtCompany = (TextView) findViewById(R.id.txtCompany);
		txtCompany.setText(contact.getName());

		TextView txtAddress = (TextView) findViewById(R.id.txtAddress);
		txtAddress.setText(contact.getAddress());

		TextView txtPhone1 = (TextView) findViewById(R.id.txtPhone1);
		txtPhone1.setText(contact.getPhone1());

		TextView txtPhone2 = (TextView) findViewById(R.id.txtPhone2);
		txtPhone2.setText(contact.getPhone2());

		TextView txtPhone3 = (TextView) findViewById(R.id.txtPhone3);
		txtPhone3.setText(contact.getPhone3());

		TextView txtWebsite = (TextView) findViewById(R.id.txtWebsite);
		txtWebsite.setText(contact.getWebpage());

		TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
		txtEmail.setText(contact.getEmail());

		TextView txtMisc = (TextView) findViewById(R.id.txtMisc);
		txtMisc.setText(Html.fromHtml(contact.getMisc()));

		return;
	}

}
