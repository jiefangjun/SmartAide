package gq.fokia.smartaide.units;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gq.fokia.smartaide.R;
import gq.fokia.smartaide.model.Unit;
import gq.fokia.smartaide.units.helper.ItemTouchHelperAdapter;

/**
 * Created by archie on 7/30/17.
 */

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.ViewHolder> implements ItemTouchHelperAdapter{

    private List<Unit> mUnitList;

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        Log.d("mUnitList", mUnitList.size()+"");
        mUnitList.remove(position);
        notifyItemRemoved(position);
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
            restaurantName = (TextView) itemView.findViewById(R.id.restaurant_name);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }

    public UnitsAdapter(List<Unit> unitList){
        this.mUnitList = unitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_queues_item, parent, false);
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
}
