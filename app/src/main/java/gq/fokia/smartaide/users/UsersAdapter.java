package gq.fokia.smartaide.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gq.fokia.smartaide.R;
import gq.fokia.smartaide.data.User;

/**
 * Created by archie on 7/30/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> implements View.OnClickListener{

    private List<User> mUserList;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView userIcon;
        TextView windowName;
        TextView foodName;
        TextView userName;
        TextView userTime;

        public ViewHolder(View itemView) {
            super(itemView);
            userIcon = (ImageView) itemView.findViewById(R.id.user_icon);
            windowName = (TextView) itemView.findViewById(R.id.window_name);
            foodName = (TextView) itemView.findViewById(R.id.food_name);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            userTime = (TextView) itemView.findViewById(R.id.user_time);
        }
    }

    public UsersAdapter(List<User> userList){
        mUserList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_users_item, parent, false);

        view.setOnClickListener(this);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUserList.get(position);
        holder.windowName.setText(user.getName());
        holder.userName.setText(user.getName());
        holder.foodName.setText(user.getAvatar());
        holder.userTime.setText(user.getIs_merchant());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    @Override
    public void onClick(View v) {

        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v);
        }
    }
}
