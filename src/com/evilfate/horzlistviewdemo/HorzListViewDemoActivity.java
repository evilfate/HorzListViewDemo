package com.evilfate.horzlistviewdemo;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sileria.android.view.HorzListView;

public class HorzListViewDemoActivity extends Activity {

	private final static int ITEM_PER_ROW = 4;

	private ArrayList<String> dataStrings;
	Bitmap iconBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.horzdemo);
		HorzListView listview = (HorzListView) findViewById(R.id.listview);

		dataStrings = new ArrayList<String>();
		for (int i = 0; i < 98; i++) {
			dataStrings.add("Text : #" + i);
		}

		iconBitmap = readBitmap(getApplicationContext(), R.drawable.ic_launcher);
		listview.setAdapter(mRowAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private BaseAdapter mRowAdapter = new BaseAdapter() {

		@Override
		public int getCount() {
			if (dataStrings.size() > 0) {
				int rowCount = dataStrings.size();
				int acualCount = rowCount % ITEM_PER_ROW == 0 ? (rowCount / ITEM_PER_ROW)
						: (rowCount / ITEM_PER_ROW) + 1;
				return acualCount;
			} else {
				return 0;
			}
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = convertView;
			ViewHolder holder;
			if (view == null) {
				holder = new ViewHolder();
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, null);
				holder.item1 = (LinearLayout) view.findViewById(R.id.item1);
				holder.item2 = (LinearLayout) view.findViewById(R.id.item2);
				holder.item3 = (LinearLayout) view.findViewById(R.id.item3);
				holder.item4 = (LinearLayout) view.findViewById(R.id.item4);
				holder.textView1 = (TextView) holder.item1.findViewById(R.id.title);
				holder.textView2 = (TextView) holder.item2.findViewById(R.id.title);
				holder.textView3 = (TextView) holder.item3.findViewById(R.id.title);
				holder.textView4 = (TextView) holder.item4.findViewById(R.id.title);
				holder.imageView1 = (ImageView) holder.item1.findViewById(R.id.image);
				holder.imageView2 = (ImageView) holder.item2.findViewById(R.id.image);
				holder.imageView3 = (ImageView) holder.item3.findViewById(R.id.image);
				holder.imageView4 = (ImageView) holder.item4.findViewById(R.id.image);

				view.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			int offset = position * ITEM_PER_ROW;

			if (offset < dataStrings.size()) {
				holder.textView1.setText(dataStrings.get(offset));
				holder.imageView1.setImageBitmap(iconBitmap);
			}

			if (offset + 1 < dataStrings.size()) {
				holder.textView2.setText(dataStrings.get(offset + 1));
				holder.imageView2.setImageBitmap(iconBitmap);
			} else {
				holder.item2.setVisibility(View.GONE);
			}

			if (offset + 2 < dataStrings.size()) {
				holder.textView3.setText(dataStrings.get(offset + 2));
				holder.imageView3.setImageBitmap(iconBitmap);
			} else {
				holder.item3.setVisibility(View.GONE);
			}

			if (offset + 3 < dataStrings.size()) {
				holder.textView4.setText(dataStrings.get(offset + 3));
				holder.imageView4.setImageBitmap(iconBitmap);
			} else {
				holder.item4.setVisibility(View.GONE);
			}

			return view;
		}

	};

	static class ViewHolder {
		LinearLayout item1;
		LinearLayout item2;
		LinearLayout item3;
		LinearLayout item4;
		ImageView imageView1;
		ImageView imageView2;
		ImageView imageView3;
		ImageView imageView4;
		TextView textView1;
		TextView textView2;
		TextView textView3;
		TextView textView4;
	}

	public static Bitmap readBitmap(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;

		InputStream is = context.getResources().openRawResource(resId);
		Bitmap bm = BitmapFactory.decodeStream(is, null, opt);
		SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bm);

		return softBitmap.get();
	}
}
