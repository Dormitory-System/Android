package com.system.dormitory.dormitory_system_android.activity_main.Manager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.system.dormitory.dormitory_system_android.R;
import com.system.dormitory.dormitory_system_android.adapter.ViewPagerAdapter;
import com.system.dormitory.dormitory_system_android.data.BoardItem;
import com.system.dormitory.dormitory_system_android.data.DataManager;
import com.system.dormitory.dormitory_system_android.data.NoticeItem;
import com.system.dormitory.dormitory_system_android.login.Activity_Login;

public class Activity_Manager_Main extends AppCompatActivity {
    private ListView lvNavList;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;
    private ViewPager viewPager;
    private AQuery aq;
    private DataManager dataManager;
    private String[] navItems={"대여승인", "외박승인", "점호확인"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Activity_Login login = (Activity_Login) Activity_Login.login_Activity; //login_Activity_finish
        login.finish();

        aq = new AQuery(this);

        init();
    }

    public void init() {
        dataManager = DataManager.getInstance();
        dataManager.DataClear();

        for (int i = 0; i < 20; i++) {
            dataManager.getBoardItems().add(new BoardItem("게시글 #" + (i + 1), "게시글 내용 #" + (i + 1)));
            dataManager.getNoticeItems().add(new NoticeItem("공지사항 #" + (i + 1), "공지사항 내용 #" + (i + 1)));
        }

        Log.i("size", String.valueOf(dataManager.getBoardItems().size()));

        aq.id(R.id.notice_layout).clicked(changePage);
        aq.id(R.id.point_layout).clicked(changePage);
        aq.id(R.id.board_layout).clicked(changePage);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getApplicationContext()));

        lvNavList = (ListView) findViewById(R.id.lv_activity_main_nav_list);

        lvNavList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));
        lvNavList.setOnItemClickListener(new DrawerItemClickListener());

        dlDrawer = (DrawerLayout) findViewById(R.id.dl_activity_main_drawer);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        dlDrawer.setDrawerListener(dtToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Button.OnClickListener changePage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.notice_layout:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.point_layout:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.board_layout:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
    };

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
            switch (position) {
                case 0:
                    Toast.makeText(Activity_Manager_Main.this, "대여승인", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(Activity_Manager_Main.this, "외박승인", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(Activity_Manager_Main.this, "점호확인", Toast.LENGTH_SHORT).show();
                    break;
            }
            dlDrawer.closeDrawer(lvNavList);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode) {
            case KeyEvent.KEYCODE_BACK:
                new AlertDialog.Builder(this)
                        .setTitle("종료")
                        .setMessage("종료 하시겠어요?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", null).show();
                return false;
            default:
                return false;
        }
    }
}