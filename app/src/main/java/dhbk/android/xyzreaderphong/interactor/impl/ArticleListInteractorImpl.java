package dhbk.android.xyzreaderphong.interactor.impl;

import java.util.List;

import javax.inject.Inject;

import dhbk.android.xyzreaderphong.interactor.ArticleListInteractor;
import dhbk.android.xyzreaderphong.interactor.ItemTableDao;
import dhbk.android.xyzreaderphong.interactor.XYZApiService;
import dhbk.android.xyzreaderphong.interactor.XYZResponse;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class ArticleListInteractorImpl implements ArticleListInteractor {
    private final XYZApiService mXyzApiService;
    private final ItemTableDao mItemTableDao;

    @Inject
    public ArticleListInteractorImpl(XYZApiService xyzApiService, ItemTableDao itemTableDao) {
        mXyzApiService = xyzApiService;
        mItemTableDao = itemTableDao;
    }

    @Override
    public void downloadDataFromNetwork(DownloadDataFromNetworkCallback callback) {
        mXyzApiService.searchXYZ()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<XYZResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<XYZResponse> xyzResponse) {
                        callback.onSuccess(xyzResponse);
                    }
                });
    }

    /**
     * insert xyzResponse to the database
     * @param xyzResponse
     */
    @Override
    public void insertToDb(XYZResponse xyzResponse) {
        // TODO: 9/5/16 insert to db
        mItemTableDao.insert(xyzResponse);
    }
}