package dhbk.android.xyzreaderphong.injection;

import android.support.annotation.NonNull;

import dhbk.android.xyzreaderphong.interactor.ArticleListInteractor;
import dhbk.android.xyzreaderphong.interactor.impl.ArticleListInteractorImpl;
import dhbk.android.xyzreaderphong.presenter.loader.PresenterFactory;
import dhbk.android.xyzreaderphong.presenter.ArticleListPresenter;
import dhbk.android.xyzreaderphong.presenter.impl.ArticleListPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class ArticleListViewModule {
    @Provides
    public ArticleListInteractor provideInteractor() {
        return new ArticleListInteractorImpl();
    }

    @Provides
    public PresenterFactory<ArticleListPresenter> providePresenterFactory(@NonNull final ArticleListInteractor interactor) {
        return new PresenterFactory<ArticleListPresenter>() {
            @NonNull
            @Override
            public ArticleListPresenter create() {
                return new ArticleListPresenterImpl(interactor);
            }
        };
    }
}
