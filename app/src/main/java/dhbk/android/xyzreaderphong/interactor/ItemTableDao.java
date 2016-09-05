package dhbk.android.xyzreaderphong.interactor;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.hannesdorfmann.sqlbrite.dao.Dao;

import rx.Observable;

/**
 * Created by huynhducthanhphong on 9/4/16.
 *
 * this is a table
 */
public class ItemTableDao extends Dao{
    /**
     * Create here the database table for the given dao
     *
     * @param database
     */
    @Override
    public void createTable(SQLiteDatabase database) {
        /**
         * todo create table with 4 column
         */
        CREATE_TABLE(
                ItemsContract.TABLE_NAME,
                ItemsContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT",
                ItemsContract.TITLE + " TEXT NOT NULL",
                ItemsContract.SERVER_ID + " TEXT",
                ItemsContract.BODY + " TEXT NOT NULL",
                ItemsContract.THUMB_URL + " TEXT NOT NULL",
                ItemsContract.AUTHOR + " TEXT NOT NULL",
                ItemsContract.PHOTO_URL + " TEXT NOT NULL",
                ItemsContract.ASPECT_RATIO + " REAL NOT NULL DEFAULT 1.5",
//                ItemsContract.PUBLISHED_DATE + " INTEGER NOT NULL DEFAULT 0"
                ItemsContract.PUBLISHED_DATE + " TEXT"
        ).execute(database);
    }

    /**
     * This method will be called, if a Database has been updated and a database
     * table scheme may be needed
     *
     * @param db         the database
     * @param oldVersion old database version
     * @param newVersion new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // add data to db
    public Observable<Long> insert(XYZResponse xyzResponse) {
        ContentValues values = ItemsContractMapper.contentValues()
                .mServerId(xyzResponse.getId())
                .mTitle(xyzResponse.getTitle())
                .mAuthor(xyzResponse.getAuthor())
                .mBody(xyzResponse.getBody())
                .mThumbUrl(xyzResponse.getThumb())
                .mPhotoUrl(xyzResponse.getPhoto())
                .mAspectRadio(xyzResponse.getAspectRatio())
                .mPublishedDate(xyzResponse.getPublishedDate())
                .build();

        return insert(ItemsContract.TABLE_NAME, values);
    }
}
