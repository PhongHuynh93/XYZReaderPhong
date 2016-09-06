package dhbk.android.xyzreaderphong.interactor.impl;

import java.util.List;

import javax.inject.Inject;

import dhbk.android.xyzreaderphong.interactor.ArticleListInteractor;
import dhbk.android.xyzreaderphong.interactor.ItemTableDao;
import dhbk.android.xyzreaderphong.interactor.XYZApiService;
import dhbk.android.xyzreaderphong.interactor.XYZResponse;
import rx.Observable;

public final class ArticleListInteractorImpl implements ArticleListInteractor {
    private final XYZApiService mXyzApiService;
    private final ItemTableDao mItemTableDao;

    @Inject
    public ArticleListInteractorImpl(XYZApiService xyzApiService, ItemTableDao itemTableDao) {
        mXyzApiService = xyzApiService;
        mItemTableDao = itemTableDao;
    }

    /**
     * download data from network
     *
     */
    @Override
    public Observable<List<XYZResponse>> downloadDataFromNetwork() {
        return mXyzApiService.searchXYZ();
    }

    /**
     * insert xyzResponse to the database
     *
     */
    @Override
    public Observable<Long> insertToDb(XYZResponse xyzResponse) {
        // : 9/5/16 insert to db
        return mItemTableDao.insert(xyzResponse);
    }

//    add delete database

    /**
     * get list of data from database
     */
    @Override
    public Observable<List<XYZResponse>> getDataFromDb() {
        return mItemTableDao.queryData();
    }

    /**
     * remove all datas in db
     */
    @Override
    public void removeOldData() {
        mItemTableDao.removeOldData();
    }

    /**
     * start the transaction before insert a large data in db
     */
    @Override
    public void startTransaction() {
        mItemTableDao.startTransaction();
    }

    /**
     * stop the transaction even it is a successful or fail transaction.
     */
    @Override
    public void endTransaction() {
        mItemTableDao.endTransaction();
    }
}