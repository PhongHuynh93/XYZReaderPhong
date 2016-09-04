package dhbk.android.xyzreaderphong.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import dhbk.android.xyzreaderphong.R;
import dhbk.android.xyzreaderphong.view.ArticleListView;
import dhbk.android.xyzreaderphong.presenter.loader.PresenterFactory;
import dhbk.android.xyzreaderphong.presenter.ArticleListPresenter;
import dhbk.android.xyzreaderphong.injection.AppComponent;
import dhbk.android.xyzreaderphong.injection.ArticleListViewModule;
import dhbk.android.xyzreaderphong.injection.DaggerArticleListViewComponent;

import javax.inject.Inject;

public final class ArticleListActivity extends BaseActivity<ArticleListPresenter, ArticleListView> implements ArticleListView {
    @Inject
    PresenterFactory<ArticleListPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerArticleListViewComponent.builder()
                .appComponent(parentComponent)
                .articleListViewModule(new ArticleListViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<ArticleListPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
