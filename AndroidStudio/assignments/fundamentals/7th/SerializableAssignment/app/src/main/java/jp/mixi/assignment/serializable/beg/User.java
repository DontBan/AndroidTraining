package jp.mixi.assignment.serializable.beg;

import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by KOBAYASHI Tomohiro on 16/06/06.
 */
public class User implements Parcelable {
    @SuppressWarnings("unused")
    private static final String TAG = User.class.getSimpleName();
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
    private JoinDate joinDate;
    private Status status;

    public User() {}

    private User(Parcel in) {

        name = in.readString();
        id = in.readInt();
        age = in.readInt();
        keyword = in.readString();
        joinDate = in.readParcelable(JoinDate.class.getClassLoader());
        status = in.readParcelable(Status.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeInt(age);
        parcel.writeString(keyword);
        parcel.writeParcelable(joinDate, 0);
        parcel.writeParcelable(status, 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public JoinDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(JoinDate joinDate) {
        this.joinDate = joinDate;
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

    public static class JoinDate implements Parcelable {
        public static final Parcelable.Creator<JoinDate> CREATOR
                = new Parcelable.Creator<JoinDate>() {
            public JoinDate createFromParcel(Parcel in) { return new JoinDate(in); }
            public JoinDate[] newArray(int size) { return new JoinDate[size]; }
        };
        public JoinDate() {}
        private JoinDate(Parcel in) {
            setYear(in.readString());
            setMonth(in.readString());
            setDate(in.readString());
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(mYear);
            parcel.writeString(mMonth);
            parcel.writeString(mDate);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        private String mYear;
        private String mMonth;
        private String mDate;
        public String getYear() {
            return mYear;
        }
        public void setYear(String year) {
            mYear = year;
        }
        public String getMonth() {
            return mMonth;
        }
        public void setMonth(String month) {
            mMonth = month;
        }
        public String getDate() {
            return mDate;
        }
        public void setDate(String date) {
            mDate = date;
        }
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