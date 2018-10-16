package j.com.picturebrowser;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class PictureFragment extends Fragment{
    private List<PictureData> PictureDataList;
    private int CurrentPos;

    public static Fragment newInstance(List<PictureData> pictureDataList, int currentPos) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("list", (ArrayList<? extends Parcelable >)pictureDataList);
        args.putInt("cPos", currentPos);
        Fragment fragment = new PictureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurrentPos = getArguments().getInt("cPos");
        PictureDataList = getArguments().getParcelableArrayList("list");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView imageView = new ImageView(getActivity());
        String data = PictureDataList.get(CurrentPos).getData();

        RequestOptions requestOptions =
                new RequestOptions().fitCenter();
        Glide.with(getActivity())
                .load(data)
                .apply(requestOptions)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View decorView = getActivity().getWindow().getDecorView();
// Hide the status bar.
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
                decorView.setSystemUiVisibility(uiOptions);

                // Remember that you should never show the action bar if the
                // status bar is hidden, so hide that too if necessary.
                android.support.v7.app.ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();
                if (ab.isShowing())
                    ab.hide();
                else
                    ab.show();

            }
        });
        return imageView;
    }
}
