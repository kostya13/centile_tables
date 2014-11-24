package me.k_ilyashenko.centile_tables.adapters;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;
import me.k_ilyashenko.centile_tables.DataHolder;
import me.k_ilyashenko.centile_tables.R;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SampleTableAdapter extends BaseTableAdapter {
	private final Context context;
	private final LayoutInflater inflater;

    private  final String[] age = {"0ì", "1ì", "2ì", "3ì","4ì","5ì","6ì","7ì","8ì","9ì","10ì","11ì",
	        "1ã","1ã 3ì","1ã 6ì","1ã 9ì","2ã","2ã 3ì","2ã 6ì","2ã 9ì","3ã","3ã 6ì",
	        "4ã","4ã 6ì","5ë","5ë 6ì","6ë","6ë 6ì","7ë","8ë","9ë","10ë","11ë","12ë",
	        "13ë","14ë","15ë","16ë","17ë"};
		
		private final static int WIDTH_DIP = 45;
		private final static int HEIGHT_DIP = 25;
		
		private final int width;
		private final int height;
		private String[][] table;

	public SampleTableAdapter(Context context, int position) {
		this.context = context;
		inflater = LayoutInflater.from(context);

		table = CreateTable(position);
		Resources r = context.getResources();
		width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, WIDTH_DIP, r.getDisplayMetrics()));
		height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HEIGHT_DIP, r.getDisplayMetrics()));
	}

	@Override
	public int getRowCount() {
		return table.length - 1;
	}

	@Override
	public int getColumnCount() {
		return table[0].length - 1;
	}

	@Override
	public int getWidth(int column) {
		if(column%2 == 0)
			return 13;
		else
			return width;
	}

	@Override
	public int getHeight(int row) {
		return height;
	}
	
	public Context getContext() {
		return context;
	}

	public LayoutInflater getInflater() {
		return inflater;
	}

	@Override
	public View getView(int row, int column, View converView, ViewGroup parent) {
		if (converView == null) 
		{
			converView = inflater.inflate(getLayoutResource(row, column), parent, false);
		}
			
		converView.setBackgroundColor(getBackgroundColor(row, column));
		setText(converView, getCellString(row, column));

		return converView;
	}

	private void setText(View view, String text) {
		((TextView) view.findViewById(android.R.id.text1)).setText(text);
	}

	/**
	 * @param row
	 *            the title of the row of this header. If the column is -1
	 *            returns the title of the row header.
	 * @param column
	 *            the title of the column of this header. If the column is -1
	 *            returns the title of the column header.
	 * @return the string for the cell [row, column]
	 */
	public String getCellString(int row, int column) {
		return table[row + 1][column + 1];
	}
	
	public int getBackgroundColor(int row, int column) {
		if(row == -1)
		{
			if(column%2 == 1 || column<0)
				return 0xFFFFFFFF;
			else
				return 0xFF00FF00;
		}

		if(column%2 == 0 )
			return 0xFF00FF00;

		if(row%2 == 0)
			return 0xFFDDDDDD;
		else
			return 0xFFFFFFFF;
	}
	
	public int getLayoutResource(int row, int column) {
		final int layoutResource;
		switch (getItemViewType(row, column)) {
			case 0:
				layoutResource = R.layout.item_table1_header;
				break;
			case 1:
				layoutResource = R.layout.item_table1;
				break;
			default:
				throw new RuntimeException("wtf?");
		}
		return layoutResource;
	}

	@Override
	public int getItemViewType(int row, int column) {
		if (row < 0 || column < 0) {
				return 0;
		} 
		else {
			return 1;
		}
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	protected String[][] CreateTable(int position)
	{
		double data[][] = DataHolder.getData(position);
		String[] header = {"", "1", "", "2", "", "3", "", "4", "", "5", "", "6", "", "7", "", "8" };
		String[][] table = new String[data.length+1][header.length];
		table[0] = header;
		for(int i = 1; i<data.length+1; i++){
			table[i][0] = age[i-1];
			for(int j = 1; j<header.length; j++){
				if(j%2 == 1)
					table[i][j]=" ";
				else
					table[i][j] = Double.toString(data[i-1][j/2-1]);
			}
		}
		return table;
	}	
}
