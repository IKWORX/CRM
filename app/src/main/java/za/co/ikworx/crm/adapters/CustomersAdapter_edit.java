package za.co.ikworx.crm.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;

import za.co.ikworx.crm.Product_main;
import za.co.ikworx.crm.R;
import za.co.ikworx.crm.customer_view_edit;
import za.co.ikworx.crm.edit_customer;
import za.co.ikworx.crm.helpers.CustomDialogClass;
import za.co.ikworx.crm.models.Customer_model;
import za.co.ikworx.crm.models.productModel;

public class CustomersAdapter_edit extends RecyclerSwipeAdapter<CustomersAdapter_edit.SimpleViewHolder> {
    private static final String TAG = "CustomersAdapter";
    private Context mContext;
    private ArrayList<Customer_model> customerlist_edit;
    public Activity activity;

    public CustomersAdapter_edit(Context context, ArrayList<Customer_model> objects,Activity activity) {
        this.mContext = context;
        this.customerlist_edit = objects;
        this.activity=activity;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayoutcustomer_view_edit, parent, false);
        return new SimpleViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final Customer_model item = customerlist_edit.get(position);

        viewHolder.Name.setText(item.getCust_name());
        viewHolder.Surname.setText(item.getCust_surname());

        viewHolder.Phone.setText(item.getCust_phone());
        viewHolder.Company.setText(item.getCust_company());

        viewHolder.City.setText(item.getCust_city());
        viewHolder.Province.setText(item.getCust_province());



        // Log.e(TAG, salesList.get(1));


        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //dari kiri
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.swipe_left_customer));

        //dari kanan
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.cust_swipe_right));



        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

        viewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Product_main.class);
                // Pass all data rank
                String fullname=item.getCust_name()+" "+item.getCust_surname();

                productModel.setCustID(item.getCust_ID());
                productModel.setCustname(fullname);
                mContext.startActivity(intent);
                // + item.getS_surname()+ " \n"+ item.getS_role()+ " \n"+ item.getSalesID()
                Toast.makeText(mContext, " Clicked : " + item.getCust_name()+ " \n" + item.getCust_surname()+ " \n" + item.getCust_email(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,edit_customer.class);
                // Pass all data rank
                String fullname=item.getCust_name()+" "+item.getCust_surname();

                productModel.setCustID(item.getCust_ID());
                //productModel.setCustname(fullname);
                mContext.startActivity(intent);

                Toast.makeText(view.getContext(), "Clicked on Edit  " + viewHolder.Name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked on Information " + viewHolder.Name.getText().toString(), Toast.LENGTH_SHORT).show();
                CustomDialogClass cdd=new CustomDialogClass(activity);
                cdd.setTitle("more info");
                cdd.setName(item.getCust_name());
                cdd.setSurname(item.getCust_surname());
                cdd.setAddress(item.getCust_address());
                cdd.setCity(item.getCust_city());
                cdd.setComment(item.getCust_comment());
                cdd.setPosition(item.getCust_designation());
                cdd.setEmail(item.getCust_email());
                cdd.setPhone(item.getCust_phone());
                cdd.setProvince(item.getCust_province());
                cdd.show();
            }
        });

        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,customer_view_edit.class);
                // Pass all data rank

                String fullname=item.getCust_name()+" "+item.getCust_surname();

                productModel.setCustID(item.getCust_ID());
               // Customer_model.setCust_name(fullname);
                mContext.startActivity(intent);

                Toast.makeText(view.getContext(), "Clicked on Edit  " + viewHolder.Name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                customerlist_edit.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, customerlist_edit.size());
                mItemManger.closeAllItems();
                Toast.makeText(v.getContext(), "Deleted " + viewHolder.Name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return customerlist_edit.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeCustomer;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public SwipeLayout swipeLayout;
        public TextView Name;
        public TextView Surname;

        public TextView Phone;
        public TextView Company;


        public TextView City;
        public TextView Province;

        public TextView Delete;
        public TextView Edit;
        public ImageButton btnLocation;

        public SimpleViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipeCustomer);
            Name = (TextView) itemView.findViewById(R.id.cust_name);
            Surname = (TextView) itemView.findViewById(R.id.cust_surname);

            Phone = (TextView) itemView.findViewById(R.id.cust_phone);
            Company = (TextView) itemView.findViewById(R.id.cust_company);


            City = (TextView) itemView.findViewById(R.id.cust_city);
            Province = (TextView) itemView.findViewById(R.id.cust_province);


            Delete = (TextView) itemView.findViewById(R.id.cust_Delete);
            Edit = (TextView) itemView.findViewById(R.id.cust_Edit1);
            btnLocation = (ImageButton) itemView.findViewById(R.id.cust_btnLocation_customer);
        }
    }

    public void filterList(ArrayList<Customer_model> filteredList){
        customerlist_edit = filteredList;
        notifyDataSetChanged();
    }
}
