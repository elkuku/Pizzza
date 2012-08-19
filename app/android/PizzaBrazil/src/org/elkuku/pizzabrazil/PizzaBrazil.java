package org.elkuku.pizzabrazil;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PizzaBrazil extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_brazil);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pizza_brazil, menu);
        return true;
    }
}
