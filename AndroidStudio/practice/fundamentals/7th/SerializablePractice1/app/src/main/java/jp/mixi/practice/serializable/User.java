package jp.mixi.practice.serializable;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class User implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private String name;
    private int id;
    private int age;
    private String keyword;
    private Status status;

    public User() {}
    private User(Parcel in) {
        name = in.readString();
        id = in.readInt();
        age = in.readInt();
        keyword = in.readString();
        status = in.readParcelable(Status.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeInt(age);
        parcel.writeString(keyword);
        parcel.writeParcelable(status, 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public static class Status implements Parcelable {
        public static final Parcelable.Creator<Status> CREATOR = new Parcelable.Creator<Status>() {
            public Status createFromParcel(Parcel in) {
                return new Status(in);
            }
            public Status[] newArray(int size) {
                return new Status[size];
            }
        };
        public Status() {}
        private Status(Parcel in) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.US);
            try {
                mPostedDate = sdf.parse(in.readString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mText = in.readString();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(mPostedDate.toString());
            parcel.writeString(mText);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        private Date mPostedDate;
        private String mText;
        public Date getPostedDate() {
            return mPostedDate;
        }
        public String getText(){
            return mText;
        }
        public void setPostedDate(Date date){
            mPostedDate = date;
        }
        public void setText(String text) {
            mText = text;
        }
    }

}
