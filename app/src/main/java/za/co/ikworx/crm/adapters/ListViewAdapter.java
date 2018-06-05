package za.co.ikworx.crm.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.HashMap;

import za.co.ikworx.crm.Product_main;
import za.co.ikworx.crm.R;
import za.co.ikworx.crm.models.productModel;

import static za.co.ikworx.crm.Utility.Utility.getIP;


public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	private static final String baseUrlForImage =getIP()+ "places/";
	double currLO,currLa;
	//ImageLoader imageLoader;
	HashMap<String, String> resultp = new HashMap<String, String>();

	public ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		this.currLa=currLa;
		this.currLO=currLO;
		//imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		 TextView name;
		TextView comapny;
		 TextView designate;
		 TextView Distance = null;
	 		TextView code;
		 ImageView imageP;
		final TextView price;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.custom_user_main, parent, false);
		// Get the position
		resultp = data.get(position);


		name = (TextView) itemView.findViewById(R.id.fullname);
		comapny = (TextView) itemView.findViewById(R.id.Company);
		designate= (TextView) itemView.findViewById(R.id.Position);


		// Capture position and set results to the TextViews
		name.setText(resultp.get("fullname"));
		//String[] spilt =(resultp.get("address")).split("#");
		designate.setText(resultp.get("position"));
		comapny.setText(resultp.get("company"));



		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				Intent intent = new Intent(context,Product_main.class);
				// Pass all data rank
				productModel.setCustID(resultp.get("ID"));
				productModel.setCustname(resultp.get("fullname"));
				intent.putExtra("name", resultp.get("name"));
				//intent.putExtra("email",resultp.get("email"));
				// Pass all data country
				intent.putExtra("address", resultp.get("address"));
				// Pass all data population
				intent.putExtra("latitude",resultp.get("latitude"));
				intent.putExtra("longitude",resultp.get("longitude"));
				intent.putExtra("distance",resultp.get("distance"));
				// Pass all data flag

				intent.putExtra("price", resultp.get("price"));
				intent.putExtra("place_id",resultp.get("place_id"));
				// Start SingleItemView Class
				context.startActivity(intent);

// or use a builder to change some configs


			}
		});
		return itemView;
	}
}
