package dhbk.android.xyzreaderphong.interactor;

import java.util.List;

import rx.Observable;

public interface ArticleListInteractor extends BaseInteractor {
    /**
     * download data from network
     */
    Observable<List<XYZResponse>> downloadDataFromNetwork();

    /**
     * insert xyzResponse to the database
     * @param xyzResponse
     */
    Observable<Long> insertToDb(XYZResponse xyzResponse);

    /**
     * get list of data from database
     */
    Observable<List<XYZResponse>> getDataFromDb();

    /**
     * remove all datas in db
     */
    Observable<Integer> removeOldData();

    /**
     * start the transaction before insert a large data in db
     */
    void startTransaction();

    /**
     * stop the transaction even it is a successful or fail transaction.
     */
    void endTransaction();
}