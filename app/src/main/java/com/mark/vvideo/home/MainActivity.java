package com.mark.vvideo.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mark.vvideo.R;
import com.mark.vvideo.bilibili.view.HomeFragment;
import com.mark.vvideo.bilibili.view.IntroductionFragment;
import com.mark.vvideo.douyutv.view.AllLiveFragment;
import com.mvp.library.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.id_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.id_fl_container)
    FrameLayout mFlContainer;

    @BindView(R.id.id_nv)
    NavigationView mNv;

    @BindView(R.id.id_dl)
    DrawerLayout mDl;

    private ActionBarDrawerToggle mToggle;

    private HomeFragment mBiliHomeFragment;

    private AllLiveFragment mDouyuAllLiveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToggle = new ActionBarDrawerToggle(this, mDl, mToolbar, R.string.open, R.string.close);
        mToggle.syncState();
        mDl.addDrawerListener(mToggle);
        mToolbar.setTitle("VVideo");
        mNv.setNavigationItemSelectedListener(this); //NavigationView 设置选择监听器
        mNv.setItemIconTintList(null);  //解决 NavigationView menu 图标不显示的问题
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        mDl.closeDrawer(GravityCompat.START);
        mDl.post(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();
                FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
                hideFragment(mTransaction);
                switch ( id ) {
                    case R.id.id_bi_item:
                        mToolbar.setTitle("哔哩哔哩动画");
                        if ( mBiliHomeFragment == null ) {
                            mBiliHomeFragment = HomeFragment.newInstance();
                            mTransaction.add(R.id.id_fl_container, mBiliHomeFragment, "BiliHomeFragment");
                            mTransaction.commit();
                        } else {
                            mTransaction.show(mBiliHomeFragment);
                        }
                        break;

                    case R.id.id_douyu:
                        mToolbar.setTitle("斗鱼TV");
                        if ( mDouyuAllLiveFragment == null ) {
                            mDouyuAllLiveFragment = AllLiveFragment.newInstance();
                            mTransaction.add(R.id.id_fl_container, mDouyuAllLiveFragment, "DouyuAllLiveFragment");
                            mTransaction.commit();
                        } else {
                            mTransaction.show(mDouyuAllLiveFragment);
                        }
                        break;
                }

            }
        });
        return true;
    }

    private void hideFragment(FragmentTransaction mTransaction) {
        if ( mBiliHomeFragment != null ) {
            mTransaction.hide(mBiliHomeFragment);
        }
        if ( mDouyuAllLiveFragment != null ) {
            mTransaction.hide(mDouyuAllLiveFragment);
        }
    }
}
