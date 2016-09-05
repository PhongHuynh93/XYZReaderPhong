package dhbk.android.xyzreaderphong.interactor.impl;

import javax.inject.Inject;

import dhbk.android.xyzreaderphong.interactor.ArticleListInteractor;
import dhbk.android.xyzreaderphong.interactor.XYZApiService;
import dhbk.android.xyzreaderphong.interactor.XYZResponse;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class ArticleListInteractorImpl implements ArticleListInteractor {
    private final XYZApiService mXyzApiService;

    @Inject
    public ArticleListInteractorImpl(XYZApiService xyzApiService) {
        mXyzApiService = xyzApiService;
    }


    @Override
    public void downloadDataFromNetwork(DownloadDataFromNetworkCallback callback) {
        mXyzApiService.searchXYZ()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XYZResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(XYZResponse xyzResponse) {
                        callback.onSuccess(xyzResponse);
                    }
                });
    }
}