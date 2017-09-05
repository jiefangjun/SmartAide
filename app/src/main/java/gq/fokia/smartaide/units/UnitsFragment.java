package gq.fokia.smartaide.units;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;

import gq.fokia.smartaide.R;
import gq.fokia.smartaide.model.Unit;
import gq.fokia.smartaide.model.User;

/**
 * Created by archie on 7/29/17.
 */

public class UnitsFragment extends Fragment implements UnitsContract.View {

    private UnitsPresenter mPresenter = new UnitsPresenter(TestModel.getInstance(), this);
    private RecyclerView mRecyclerView;
    private UsersAdapter mUsersAdapter;
    private List<Unit> mUnitList;
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
        mPresenter.loadData(mSwipeRefreshLayout);
    }

    public void initData(){
        mUnitList = mPresenter.getData();
        mUsersAdapter= new UsersAdapter(mUnitList);
        mRecyclerView.setAdapter(mUsersAdapter);
    }

    @Override
    public void showData(){
        mUsersAdapter.notifyDataSetChanged();
    }


    @Override
    public void setPresenter(UnitsContract.Presenter presenter) {
         //mPresenter = new UnitsPresenter(TestModel.getInstance(), this);
    }

    @Override
    public void showInfo(){
        //mSwipeRefreshLayout.setRefreshing(true);
        Toast.makeText(getActivity(), "数据获取成功", Toast.LENGTH_LONG).show();
    }

    public void setListener(){
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData(mSwipeRefreshLayout);
            }
        });

        mUsersAdapter.setOnItemClickListener(new UsersAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Log.d(getClass().toString(), "itemClick");
                Toast.makeText(getActivity(), "itemOnClick", Toast.LENGTH_LONG).show();
            }
        });
    }




}
