package me.k_ilyashenko.centile_tables;

import me.k_ilyashenko.centile_tables.adapters.SampleTableAdapter;
import android.app.Activity;
import android.os.Bundle;

import com.inqbarna.tablefixheaders.TableFixHeaders;

public class StyleTable extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table);
		int position = getIntent().getIntExtra(MainActivity.EXTRA_POSITION, 0);
		setTitle(MainActivity.TITLES[position]);
				
		TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
		tableFixHeaders.setAdapter(new SampleTableAdapter(this, position));
	}

}
