package dhbk.android.xyzreaderphong.interactor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.sqlbrite.objectmapper.annotation.Column;
import com.hannesdorfmann.sqlbrite.objectmapper.annotation.ObjectMappable;

import lombok.Getter;

/**
 * Created by huynhducthanhphong on 9/4/16.
 *
 * fixme - 3 the column in database is set as @Column,
 * this column is also the field in retrofit
 */
@ObjectMappable
public class XYZResponse {
    public static final String DB_NAME = "XYZ_reader_database";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "items";

    //     7b - the column for this table, so if you want to add to column (use this name)
    /**
     * Type: INTEGER PRIMARY KEY AUTOINCREMENT
     */
    public static final String _ID = "_id";
    /**
     * Type: TEXT
     */
    public static final String SERVER_ID = "id";
    /**
     * Type: TEXT NOT NULL
     */
    public static final String TITLE = "title";
    /**
     * Type: TEXT NOT NULL
     */
    public static final String AUTHOR = "author";
    /**
     * Type: TEXT NOT NULL
     */
    public static final String BODY = "body";
    /**
     * Type: TEXT NOT NULL
     */
    public static final String THUMB_URL = "thumb_url";
    /**
     * Type: TEXT NOT NULL
     */
    public static final String PHOTO_URL = "photo_url";
    /**
     * Type: REAL NOT NULL DEFAULT 1.5
     */
    public static final String ASPECT_RATIO = "aspect_ratio";
    /**
     * Type: INTEGER NOT NULL DEFAULT 0
     */
    public static final String PUBLISHED_DATE = "published_date";


    // key in JSON to download
    @Column(_ID)
    int mId;

    @Column(SERVER_ID)
    @SerializedName("id")
    @Expose
    @Getter
    String mServerId;

    @Column(PHOTO_URL)
    @SerializedName("photo")
    @Expose
    @Getter
    String mPhotoUrl;

    @Column(THUMB_URL)
    @SerializedName("thumb")
    @Expose
    @Getter
    String mThumbUrl;

    @Column(ASPECT_RATIO)
    @SerializedName("aspect_ratio")
    @Expose
    @Getter
    double mAspectRadio;

    @Column(AUTHOR)
    @SerializedName("author")
    @Expose
    @Getter
    String mAuthor;

    @Column(TITLE)
    @SerializedName("title")
    @Expose
    @Getter
    String mTitle;

    @Column(PUBLISHED_DATE)
    @SerializedName("published_date")
    @Expose
    @Getter
    String mPublishedDate;

    @Column(BODY)
    @SerializedName("body")
    @Expose
    @Getter
    String mBody;

}