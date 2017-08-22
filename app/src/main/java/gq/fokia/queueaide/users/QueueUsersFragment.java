package gq.fokia.queueaide.QueueUsers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;

import gq.fokia.queueaide.R;
import gq.fokia.queueaide.data.User;

/**
 * Created by archie on 7/29/17.
 */

public class QueueUsersFragment extends Fragment implements QueueUsersContract.View {

    private QueueUsersPresenter mPresenter = new QueueUsersPresenter(TestModel.getInstance(), this);
    private RecyclerView mRecyclerView;
    private UsersAdapter mUsersAdapter;
    private List<User> mUserList;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queueusers, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        initData();
        setListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public void initData(){
        mUserList = mPresenter.getData();
        mUsersAdapter= new UsersAdapter(mUserList);
        mRecyclerView.setAdapter(mUsersAdapter);
    }

    @Override
    public void showData(){
        mUsersAdapter.notifyDataSetChanged();
        //mSwipeRefreshLayout.setRefreshing(false);
        Log.d(getClass().toString(), "子线程结束");
    }


    @Override
    public void setPresenter(QueueUsersContract.Presenter presenter) {
         //mPresenter = new QueueUsersPresenter(TestModel.getInstance(), this);
    }

    @Override
    public void showInfo(){
        //mSwipeRefreshLayout.setRefreshing(true);
        Toast.makeText(getActivity(), "数据获取成功", Toast.LENGTH_LONG).show();
    }

    public void setListener(){

        mUsersAdapter.setOnItemClickListener(new UsersAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Log.d(getClass().toString(), "itemClick");
                Toast.makeText(getActivity(), "itemOnClick", Toast.LENGTH_LONG).show();
            }
        });
    }


}
