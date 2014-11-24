package me.k_ilyashenko.centile_tables;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	public static final String EXTRA_POSITION = "my.KI.position";

	public static enum TABLES              {BOYS_WEIGHT,      BOYS_LENGHT,      GIRLS_WEIGHT,    GIRLS_LENGHT};
    public final static String TITLES[] =  {"Мальчики: Вес",  "Мальчики: Рост", "Девочки: Вес",  "Девочки: Рост"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, TITLES));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i =new Intent(this, StyleTable.class);
		i.putExtra(MainActivity.EXTRA_POSITION,  position);
		startActivity(i);
	}
	
}
