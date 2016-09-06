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

//    @Override
//    public void downloadDataFromNetwork(DownloadDataFromNetworkCallback callback) {
//        mXyzApiService.searchXYZ()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<XYZResponse>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<XYZResponse> xyzResponse) {
//                        callback.onSuccess(xyzResponse);
//                    }
//                });
//    }


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
//        mItemTableDao.insert(xyzResponse)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//
//                    }
//                });
    }


    /**
     * get list of data from database
     */
    @Override
    public Observable<List<XYZResponse>> getDataFromDb() {
        return mItemTableDao.queryData();
    }
}