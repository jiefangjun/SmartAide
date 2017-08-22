package gq.fokia.queueaide;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import gq.fokia.queueaide.users.QueueUsersFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        initBottomNavigationBar(bottomNavigationBar);





    }

    public void initBottomNavigationBar(BottomNavigationBar bottomNavigationBar){
        bottomNavigationBar.setAutoHideEnabled(true);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "排队").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.ic_local_shipping_black_24dp, "外卖").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_event_note_black_24dp, "记账"))
                .addItem(new BottomNavigationItem(R.drawable.ic_notifications_black_24dp, "点餐"))
                .addItem(new BottomNavigationItem(R.drawable.ic_account_circle_black_24dp, "我"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .initialise();
        //bottomNavigationBar.setFirstSelectedPosition(0)
        //单个view对应多个presenter，用fragment实现。

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                switch (position){
                    case 0:
                        replaceFragment(new QueueUsersFragment());
                        break;
                    case 2:
                        //replaceFragment();
                        //TODO 在account下新建一个AccountFragment，实现记账功能，布局参考 QueueUsersFragment，展示数据。给一个添加数据的接口。
                }
            }
            @Override
            public void onTabUnselected(int position) {
                Log.d("onTabUnselected", position+"");
            }
            @Override
            public void onTabReselected(int position) {
                Log.d("onTabReselected", position+"");
            }
        });

        bottomNavigationBar.selectTab(0);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentLayout, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.scan:
                //TODO
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
