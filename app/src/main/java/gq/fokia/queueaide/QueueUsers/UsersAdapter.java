package gq.fokia.queueaide.QueueUsers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gq.fokia.queueaide.R;
import gq.fokia.queueaide.data.User;

/**
 * Created by archie on 7/30/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> mUserList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.recycler_item);
        }
    }

    public UsersAdapter(List<User> userList){
        mUserList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_users_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUserList.get(position);
        holder.mTextView.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
