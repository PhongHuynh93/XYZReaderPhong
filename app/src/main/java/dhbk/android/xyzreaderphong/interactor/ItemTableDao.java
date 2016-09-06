package dhbk.android.xyzreaderphong.interactor;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.hannesdorfmann.sqlbrite.dao.Dao;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import rx.Observable;

/**
 * Created by huynhducthanhphong on 9/4/16.
 *
 * this is a table
 */
public class ItemTableDao extends Dao{
    private BriteDatabase.Transaction mTransaction;

    /**
     * Create here the database table for the given dao
     *
     * @param database
     */
    @Override
    public void createTable(SQLiteDatabase database) {
        /**
         *  create table with 4 column
         */
        CREATE_TABLE(
                XYZResponse.TABLE_NAME,
                XYZResponse._ID + " INTEGER PRIMARY KEY AUTOINCREMENT",
                XYZResponse.TITLE + " TEXT NOT NULL",
                XYZResponse.SERVER_ID + " TEXT",
                XYZResponse.BODY + " TEXT NOT NULL",
                XYZResponse.THUMB_URL + " TEXT NOT NULL",
                XYZResponse.AUTHOR + " TEXT NOT NULL",
                XYZResponse.PHOTO_URL + " TEXT NOT NULL",
                XYZResponse.ASPECT_RATIO + " REAL NOT NULL DEFAULT 1.5",
                XYZResponse.PUBLISHED_DATE + " TEXT"
        ).execute(database);
        //                ItemsContract.PUBLISHED_DATE + " INTEGER NOT NULL DEFAULT 0"

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
        ContentValues values = XYZResponseMapper.contentValues()
                .mServerId(xyzResponse.getMServerId())
                .mTitle(xyzResponse.getMTitle())
                .mAuthor(xyzResponse.getMAuthor())
                .mBody(xyzResponse.getMBody())
                .mThumbUrl(xyzResponse.getMThumbUrl())
                .mPhotoUrl(xyzResponse.getMPhotoUrl())
                .mAspectRadio(xyzResponse.getMAspectRadio())
                .mPublishedDate(xyzResponse.getMPublishedDate())
                .build();

        return insert(XYZResponse.TABLE_NAME, values);
    }

    public Observable<List<XYZResponse>> queryData() {
        /**
         *  query the db to get the cursor
         * translate cursor by mapToList (CustomerMapper.MAPPER is auto created)
         */
        return query(
                SELECT(
                        XYZResponse._ID,
                        XYZResponse.TITLE,
                        XYZResponse.SERVER_ID,
                        XYZResponse.BODY,
                        XYZResponse.THUMB_URL,
                        XYZResponse.AUTHOR,
                        XYZResponse.PHOTO_URL,
                        XYZResponse.ASPECT_RATIO,
                        XYZResponse.PUBLISHED_DATE
                ).FROM(XYZResponse.TABLE_NAME))
                .run()
                .mapToList(XYZResponseMapper.MAPPER);
    }

    public void startTransaction() {
        mTransaction = newTransaction();
    }

    public void endTransaction() {
        mTransaction.end();
    }

    public void removeOldData() {

    }
}
