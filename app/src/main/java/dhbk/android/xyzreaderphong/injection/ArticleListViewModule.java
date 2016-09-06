package dhbk.android.xyzreaderphong.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import dhbk.android.xyzreaderphong.interactor.ArticleListInteractor;
import dhbk.android.xyzreaderphong.interactor.ItemTableDao;
import dhbk.android.xyzreaderphong.interactor.XYZApiService;
import dhbk.android.xyzreaderphong.interactor.impl.ArticleListInteractorImpl;
import dhbk.android.xyzreaderphong.presenter.ArticleListPresenter;
import dhbk.android.xyzreaderphong.presenter.impl.ArticleListPresenterImpl;
import dhbk.android.xyzreaderphong.presenter.loader.PresenterFactory;
import dhbk.android.xyzreaderphong.view.impl.ArticleListAdapter;

@Module
public final class ArticleListViewModule {
    private final Context mActivityContext;

    public ArticleListViewModule(Context activityContext) {
        mActivityContext = activityContext;
    }

    @Provides
    public ArticleListInteractor provideInteractor(XYZApiService xyzApiService, ItemTableDao itemTableDao) {
        return new ArticleListInteractorImpl(xyzApiService, itemTableDao);
    }

    @Provides
    public PresenterFactory<ArticleListPresenter> providePresenterFactory(@NonNull final ArticleListInteractor interactor) {
        return () -> new ArticleListPresenterImpl(interactor);
    }

    @Provides
    public ArticleListAdapter provideArticleListAdapter() {
        return new ArticleListAdapter(mActivityContext);
    }
}
