package dhbk.android.xyzreaderphong.interactor;

import com.hannesdorfmann.sqlbrite.objectmapper.annotation.Column;
import com.hannesdorfmann.sqlbrite.objectmapper.annotation.ObjectMappable;

/**
 * 7 - make an constract, contains columns for db
 */
@ObjectMappable
public class ItemsContract {
    public static final String DB_NAME = "XYZ_reader_database";

    public static final String TABLE_NAME = "items";

    //     7b - the column for this table
    /**
     * Type: INTEGER PRIMARY KEY AUTOINCREMENT
     */
    public static final String _ID = "_id";
    /**
     * Type: TEXT
     */
    public static final String SERVER_ID = "server_id";
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

    // create field map with constant column
    @Column(_ID)
    int mId;
    @Column(SERVER_ID)
    String mServerId;
    @Column(TITLE)
    String mTitle;
    @Column(AUTHOR)
    String mAuthor;
    @Column(BODY)
    String mBody;
    @Column(THUMB_URL)
    String mThumbUrl;
    @Column(PHOTO_URL)
    String mPhotoUrl;
    @Column(ASPECT_RATIO)
    double mAspectRadio;
    @Column(PUBLISHED_DATE)
    int mPublishedDate;
}
