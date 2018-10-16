package j.com.picturebrowser;

import android.os.Parcel;
import android.os.Parcelable;

public class PictureData implements Parcelable {
    private String id;
    private String name;
    private String data;
    private String size;
    private String title;
    private String height;
    private String width;
    private String time;
    private String bucket;

    public PictureData(String id, String name, String data, String size, String title, String height, String width, String time, String bucket) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.size = size;
        this.title = title;
        this.height = height;
        this.width = width;
        this.time = time;
        this.bucket = bucket;
    }

    protected PictureData(Parcel in) {
        id = in.readString();
        name = in.readString();
        data = in.readString();
        size = in.readString();
        title = in.readString();
        height = in.readString();
        width = in.readString();
        time = in.readString();
        bucket = in.readString();
    }

    public static final Creator<PictureData> CREATOR = new Creator<PictureData>() {
        @Override
        public PictureData createFromParcel(Parcel in) {
            return new PictureData(in);
        }

        @Override
        public PictureData[] newArray(int size) {
            return new PictureData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(data);
        parcel.writeString(size);
        parcel.writeString(title);
        parcel.writeString(height);
        parcel.writeString(width);
        parcel.writeString(time);
        parcel.writeString(bucket);
    }
}
