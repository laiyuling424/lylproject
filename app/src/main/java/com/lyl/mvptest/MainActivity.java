package com.lyl.mvptest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.lyl.mvptest.adapter.ViewPagerAdapter;
import com.lyl.mvptest.widget.NoScrollViewPager;
import com.lyl.utils.function.InstallUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create 2018/8/21
 * author lyl
 */
public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("native-lib");
    }

    //    DrawerLayout mDrawerLayout;
    NoScrollViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    List<Fragment> list;
    Toolbar mToolbar;
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);

        init();

//        if (TestConfig.outputTestAnnotationLog)  testAnnotation();
    }

    private void init() {
//        mDrawerLayout=(DrawerLayout)findViewById(R.id.drag_layout);
//        NavigationView navigationView=(NavigationView)findViewById(R.id.nav);
        viewPager = (NoScrollViewPager) findViewById(R.id.viewpager);
        list = new ArrayList<>();
        Fragment fragmentOne = new MainFragment();
        Fragment fragmentTwo = new SecondFragment();
        Fragment fragmentThree = new ThirdFragment();
        list.add(fragmentOne);
        list.add(fragmentTwo);
        list.add(fragmentThree);
//        viewPagerAdapter=new ViewPagerAdapter(this.getSupportFragmentManager(),list);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_me);
        mToolbar.setContentInsetsAbsolute(0, 0);
        mToolbar.setContentInsetStartWithNavigation(0);


//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onClick(View v) {
//               mDrawerLayout.openDrawer(Gravity.START);
//            }
//        });
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv3:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void updata() {
        new AsyncTask<Void, Void, File>() {
            @Override
            protected File doInBackground(Void... voids) {

                String patch = new File(Environment.getExternalStorageDirectory(), "patch").getAbsolutePath();
                String oldApk = getApplicationInfo().sourceDir;
                String output = createNewApk().getAbsolutePath();

                if (!new File(patch).exists()) {
                    return null;
                }
                bsPatch(oldApk, patch, output);
                return new File(output);
            }

            @Override
            protected void onPostExecute(File file) {
                super.onPostExecute(file);
                Log.d("lyll", "path===" + file.getAbsolutePath());
                //安装新apk
                if (file != null) {
                    InstallUtil installUtil = new InstallUtil(MainActivity.this, file.getAbsolutePath());
                    installUtil.install();
                } else {
                    Toast.makeText(MainActivity.this, "差分包不存在！", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }


    /**
     * 创建合成后的新版本apk文件
     *
     * @return
     */
    private File createNewApk() {
        File newApk = new File(Environment.getExternalStorageDirectory(), "bsdiff.apk");
        if (!newApk.exists()) {
            try {
                newApk.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newApk;
    }


    private native void bsPatch(String oldApk, String patch, String output);
}
