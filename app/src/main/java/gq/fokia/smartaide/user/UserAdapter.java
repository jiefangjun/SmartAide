package gq.fokia.smartaide.user;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by archie on 8/26/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView avatar;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            //avatar = itemView.findViewById()

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
