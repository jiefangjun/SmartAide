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

import java.util.ArrayList;
import java.util.List;

import gq.fokia.smartaide.R;
import gq.fokia.smartaide.data.remote.UnitsRemoteDataSource;
import gq.fokia.smartaide.model.Unit;

/**
 * Created by archie on 7/29/17.
 */

public class UnitsFragment extends Fragment implements UnitsContract.View {

    private UnitsPresenter mPresenter = new UnitsPresenter(UnitsRemoteDataSource.getInstance(), this);
    private RecyclerView mRecyclerView;
    private UnitsAdapter mUnitsAdapter;
    public static List<Unit> mUnitList = new ArrayList<>();
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
        mPresenter.loadData();
    }

    public void initData(){
        mUnitList = mPresenter.getData();
        Log.d("mUnitList", mUnitList.size()+"");
        mUnitsAdapter= new UnitsAdapter(mUnitList);
        mRecyclerView.setAdapter(mUnitsAdapter);
    }

    @Override
    public void showData(){
        mUnitsAdapter.notifyDataSetChanged();
    }


    @Override
    public void setPresenter(UnitsContract.Presenter presenter) {}

    @Override
    public void showInfo(){
        Toast.makeText(getActivity(), "数据获取失败", Toast.LENGTH_LONG).show();
    }

    public void setListener(){
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mUnitsAdapter.setOnItemClickListener(new UnitsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Log.d(getClass().toString(), "itemClick");
                Toast.makeText(getActivity(), "itemOnClick", Toast.LENGTH_LONG).show();
            }
        });
    }




}
