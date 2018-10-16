package j.com.picturebrowser;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    private Context mContext;
    private List<PictureData> mPictureList;
//    private ImageView imageView;

    public FolderAdapter(Context mContext, List<PictureData> mPictureList) {
        this.mContext = mContext;
        this.mPictureList = mPictureList;
    }

    @NonNull
    @Override
    public FolderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View pictureView = inflater.inflate(R.layout.image_thunbnail, parent, false);

        return new FolderAdapter.ViewHolder(pictureView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FolderAdapter.ViewHolder holder, int position) {

        String data = mPictureList.get(position).getData();
//        imageView.setImageURI(Uri.parse(data));

        Log.d("adapter", ""+mPictureList.get(position).getId());
        RequestOptions requestOptions =
                new RequestOptions().centerCrop();
        Glide.with(mContext)
                .load(data)
                .apply(requestOptions)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.clicked){
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(
                            ObjectAnimator.ofFloat(holder.imageView, "scaleX", 0.8f, 1.0f).setDuration(200),
                            ObjectAnimator.ofFloat(holder.imageView, "scaleY", 0.8f, 1.0f).setDuration(200)
                    );
                    animatorSet.start();
                    holder.clicked = false;
                }
                else {

                    Intent intent = new Intent(mContext, PictureViewActivity.class);
                    intent.putParcelableArrayListExtra("dataList", (ArrayList<? extends Parcelable>) mPictureList);
                    intent.putExtra("currentPos", holder.getLayoutPosition());
//                    int position = holder.getAdapterPosition();
//                    if(position != RecyclerView.NO_POSITION) {
//                        PictureData pictureData = mPictureList.get(position);
//                        Intent intent = new Intent(mContext, PictureViewActivity.class);
//                intent.putExtra(PictureViewActivity.EXTRA_SPACE_PHOTO, pictureData);
                    mContext.startActivity(intent);
                }
            }
//            holder.imageView.setBackgroundColor(Color.WHITE);
        }
    );
}

    @Override
    public int getItemCount() {
        return mPictureList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

 class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private boolean clicked=false;
    private FrameLayout frameLayout;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        imageView = new ImageView(mContext);

//        imageView.setLayoutParams(new ViewGroup.LayoutParams(size.x/4,size.x/4));
//        imageView.setPadding(5,5,5,5);
//        imageView.setBackgroundColor(Color.BLACK);

        frameLayout = itemView.findViewById(R.id.image_thumbnail);
        FrameLayout.LayoutParams fllp = new FrameLayout.LayoutParams(size.x/4,size.x/4);
        fllp.setMargins(5,1,1,1);
        frameLayout.setLayoutParams(fllp);
        frameLayout.addView(imageView);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (clicked == false) {
                    AnimatorSet animatorSet = new AnimatorSet();
//                ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(holder.imageView, "scaleX", 1.0f, 0.8f);
//                scaleXAnim.setDuration(500);
//                ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(holder.imageView, "scaleY", 1.0f, 0.8f);
//                scaleYAnim.setDuration(500);
                    animatorSet.playTogether(
                            ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 0.8f).setDuration(200),
                            ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 0.8f).setDuration(200)
                    );
                    animatorSet.start();
                    clicked = true;
//                scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
//                scaleAnim.setRepeatMode(ValueAnimator.REVERSE);
//                scaleAnim.setInterpolator(new BounceInterpolator());
//                scaleXAnim.start();
//                scaleYAnim.start();
                    frameLayout.setBackgroundColor(Color.BLACK);
                    return true;
                }
                else
                    return false;
            }
        });
    }
    }
}
