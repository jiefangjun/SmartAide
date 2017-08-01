package gq.fokia.queueaide.QueueUsers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gq.fokia.queueaide.R;
import gq.fokia.queueaide.data.User;

/**
 * Created by archie on 7/29/17.
 */

public class QueueUsersFragment extends Fragment implements QueueUsersContract.View {

    private QueueUsersPresenter mPresenter = new QueueUsersPresenter(TestModel.getInstance(), this);
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queueusers, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        UsersAdapter usersAdapter = new UsersAdapter(mPresenter.getData());
        mRecyclerView.setAdapter(usersAdapter);
        return view;
    }

    @Override
    public void showData(String users) {
        mPresenter.getData();
    }


}
