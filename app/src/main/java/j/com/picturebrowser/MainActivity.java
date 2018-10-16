package j.com.picturebrowser;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String Tag = MainActivity.class.getSimpleName();
    private List<PictureData> mPictureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestPermission();
        mPictureList = PictureUtil.getPictureData(this);
        RecyclerView recyclerView = findViewById(R.id.rv);

        FolderAdapter folderAdapter = new FolderAdapter(this, mPictureList);
//        folderAdapter.setHasStableIds(true);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, GridLayoutManager.VERTICAL);
        gridLayoutManager.setSpanCount(4);

        recyclerView.setAdapter(folderAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void requestPermission(){
        String [] permission = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(this, permission, 1);
    }

}
