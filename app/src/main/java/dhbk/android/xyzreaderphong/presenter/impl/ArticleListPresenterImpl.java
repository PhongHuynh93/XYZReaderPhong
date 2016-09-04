package dhbk.android.xyzreaderphong.presenter.impl;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import dhbk.android.xyzreaderphong.interactor.ArticleListInteractor;
import dhbk.android.xyzreaderphong.presenter.ArticleListPresenter;
import dhbk.android.xyzreaderphong.view.ArticleListView;

public final class ArticleListPresenterImpl extends BasePresenterImpl<ArticleListView> implements ArticleListPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ArticleListInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public ArticleListPresenterImpl(@NonNull ArticleListInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean firstStart) {
        super.onStart(firstStart);

        // Your code here. Your view is available using mView and will not be null until next onStop()
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

        super.onPresenterDestroyed();
    }

    /**
     * todo call the interactor to load data to listview
     */
    @Override
    public void loadDataToRecyclerView() {

    }
}