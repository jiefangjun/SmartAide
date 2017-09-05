package gq.fokia.smartaide.units;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gq.fokia.smartaide.R;
import gq.fokia.smartaide.model.Unit;

/**
 * Created by archie on 7/30/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> implements View.OnClickListener{

    //private List<User> mUserList;
    private List<Unit> mUnitList;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView userIcon;
        TextView userName;
        TextView restaurantName;
        TextView productName;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            userIcon = (ImageView) itemView.findViewById(R.id.user_icon);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            restaurantName = (TextView) itemView.findViewById(R.id.user_name);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }

    public UsersAdapter(List<Unit> unitList){
        this.mUnitList = unitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_queues_item, parent, false);

        view.setOnClickListener(this);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Unit unit = mUnitList.get(position);
        holder.userName.setText(unit.getUserName());
        holder.restaurantName.setText(unit.getRestaurantName());
        holder.productName.setText(unit.getProductName());
        holder.time.setText(unit.getTime());
    }

    @Override
    public int getItemCount() {
        return mUnitList.size();
    }

    @Override
    public void onClick(View v) {

        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v);
        }
    }
}
