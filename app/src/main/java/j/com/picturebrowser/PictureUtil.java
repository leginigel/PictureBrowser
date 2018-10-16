package j.com.picturebrowser;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PictureUtil {

    private static final String Tag = PictureUtil.class.getSimpleName();

    static List<PictureData> getPictureData(Context context){
        Log.i(Tag,"getPictureData");

        List <PictureData>PictureList = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = null;
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        uri.getLastPathSegment();
        String [] projection = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.DATE_MODIFIED,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        };

        try {
            cursor = cr.query(uri, projection, null, null, null);

            if (cursor != null) {
                Log.d(Tag, "cursor not null !!!");
                while (cursor.moveToNext()) {
                    PictureData data = new PictureData(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            null,
                            null,
                            cursor.getString(5),
                            cursor.getString(6)
                    );
                    PictureList.add(data);
//                    Log.d(Tag, "id "+cursor.getString(0));
//                    Log.d(Tag, "name "+cursor.getString(1));
                    Log.d(Tag, "title "+cursor.getString(4));
                    Log.d(Tag, "Bucket"+ cursor.getString(6));
                }
            } else
                Log.i(Tag, "No fetch data");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (cursor != null) cursor.close();
        }


        return PictureList;
    }



}
