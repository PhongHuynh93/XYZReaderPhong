package dhbk.android.xyzreaderphong.presenter.impl;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import dhbk.android.xyzreaderphong.interactor.ArticleListInteractor;
import dhbk.android.xyzreaderphong.interactor.XYZResponse;
import dhbk.android.xyzreaderphong.presenter.ArticleListPresenter;
import dhbk.android.xyzreaderphong.view.ArticleListView;
import hugo.weaving.DebugLog;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public final class ArticleListPresenterImpl extends BasePresenterImpl<ArticleListView> implements ArticleListPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ArticleListInteractor mInteractor;

    private CompositeSubscription mCompositeSubscription;


    // The view is available using the mView variable
    @Inject
    public ArticleListPresenterImpl(@NonNull ArticleListInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        mCompositeSubscription = new CompositeSubscription();

    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
        super.onPresenterDestroyed();
    }

    /**
     * call the interactor to load data from database to listview
     * fix this to load from database
     */
    @Override
    public void loadDataToRecyclerViewFromDb() {
        Subscription getDataFromDbSubcription = mInteractor.getDataFromDb()
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
                    public void onNext(List<XYZResponse> xyzResponses) {
                        if (mView != null) {
                            mView.addNewDataToRecyclerview(xyzResponses);
                        }
                    }
                });
        mCompositeSubscription.add(getDataFromDbSubcription);
    }

    /**
     * call the interactor to load data from network to listview
     */
    @DebugLog
    @Override
    public void loadDataToRecyclerViewFromNetwork() {
//          - check the network
        // check network connection
        if (mView != null && !mView.isConnectedToNetwork()) {
            // TODO: 9/6/16 register the view the listen when the network is on
            mView.stopShowRefreshIndicator();
            return;
        }

        if (mView != null) {
            mView.showRefreshIndicator();
        }

        Subscription downloadDataFromNetworkSubscription = mInteractor.downloadDataFromNetwork()
                .subscribeOn(Schedulers.io())
                .doOnNext(xyzResponses -> mInteractor.removeOldData())
                .doOnNext(xyzResponses -> {
                    mInteractor.startTransaction();
                    try {
                        for (XYZResponse rowXyz : xyzResponses) {
                            mInteractor.insertToDb(rowXyz);
                        }
                    } finally {
                        mInteractor.endTransaction();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(xyzResponses -> {
//                        -update the list
                    if (mView != null) {
                        mView.stopShowRefreshIndicator();
                        mView.addNewDataToRecyclerview(xyzResponses);
                    }
                });
        mCompositeSubscription.add(downloadDataFromNetworkSubscription);
    }
}