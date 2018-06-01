package za.co.ikworx.crm.adapters;

import android.content.Context;
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

import za.co.ikworx.crm.R;
import za.co.ikworx.crm.models.Customer_model;
import za.co.ikworx.crm.models.Sales_model;

public class SwipeRecyclerViewAdapter extends RecyclerSwipeAdapter<SwipeRecyclerViewAdapter.SimpleViewHolder> {
    private static final String TAG = "SwipeRecyclerViewAdapter";
    private Context mContext;
    private ArrayList<Sales_model> salesList;

    public SwipeRecyclerViewAdapter(Context context, ArrayList<Sales_model> objects) {
        this.mContext = context;
        this.salesList = objects;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayoutsales, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final Sales_model item = salesList.get(position);

        viewHolder.Name.setText(item.getS_name());
        viewHolder.EmailId.setText(item.getS_email());
        viewHolder.Surname.setText(item.getS_surname());
        viewHolder.Role.setText(item.getS_role());
        viewHolder.SalesID.setText(item.getSalesID());


       // Log.e(TAG, salesList.get(1));


        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //dari kiri
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.swipe_left));

        //dari kanan
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.swipe_right));



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
                // + item.getS_surname()+ " \n"+ item.getS_role()+ " \n"+ item.getSalesID()
                Toast.makeText(mContext, " Clicked : " + item.getS_name()+ " \n" + item.getS_email()+ " \n" + item.getS_role(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked on Information " + viewHolder.Name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Clicked on Share " + viewHolder.Name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Clicked on Edit  " + viewHolder.Name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                salesList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, salesList.size());
                mItemManger.closeAllItems();
                Toast.makeText(v.getContext(), "Deleted " + viewHolder.Name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return salesList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public SwipeLayout swipeLayout;
        public TextView Name;
        public TextView EmailId;
        public TextView Surname;
        public TextView Role;
        public TextView SalesID;
        public TextView Delete;
        public TextView Edit;
        public TextView Share;
        public ImageButton btnLocation;

        public SimpleViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            Name = (TextView) itemView.findViewById(R.id.s_name);
            EmailId = (TextView) itemView.findViewById(R.id.s_email);
            Surname = (TextView) itemView.findViewById(R.id.s_surname);
            Role = (TextView) itemView.findViewById(R.id.s_role);
            SalesID = (TextView) itemView.findViewById(R.id.s_employee_status);
            Delete = (TextView) itemView.findViewById(R.id.Delete);
            Edit = (TextView) itemView.findViewById(R.id.Edit);
            Share = (TextView) itemView.findViewById(R.id.Share);
            btnLocation = (ImageButton) itemView.findViewById(R.id.btnLocation);
        }
    }


    public void filterList_sales(ArrayList<Sales_model> filteredList_sales){
        salesList = filteredList_sales;
        notifyDataSetChanged();
    }

}
