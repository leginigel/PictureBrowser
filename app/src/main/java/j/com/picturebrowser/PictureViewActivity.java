package j.com.picturebrowser;

import android.app.ActionBar;
import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class PictureViewActivity extends AppCompatActivity {

    private List<PictureData> pictureDataList;
    private int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
               | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        pictureDataList = getIntent().getParcelableArrayListExtra("dataList");
        currentPos = getIntent().getIntExtra("currentPos", 0);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        viewPager.setAdapter(new PictureFragmentPagerAdapter(getSupportFragmentManager(), PictureViewActivity.this));
        viewPager.setCurrentItem(currentPos);
        //viewPager.setPadding(20,0,20,0);
        //viewPager.setClipToPadding(false);
        viewPager.setPageMargin(50);

    }
    public class PictureFragmentPagerAdapter extends FragmentPagerAdapter {
        public PictureFragmentPagerAdapter(FragmentManager fm, Context mContext) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PictureFragment.newInstance(pictureDataList, position);
        }

        @Override
        public int getCount() {
            return pictureDataList.size();
        }
    }
}
