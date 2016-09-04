package dhbk.android.xyzreaderphong.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dhbk.android.xyzreaderphong.R;
import dhbk.android.xyzreaderphong.injection.AppComponent;
import dhbk.android.xyzreaderphong.injection.ArticleListViewModule;
import dhbk.android.xyzreaderphong.injection.DaggerArticleListViewComponent;
import dhbk.android.xyzreaderphong.presenter.ArticleListPresenter;
import dhbk.android.xyzreaderphong.presenter.loader.PresenterFactory;
import dhbk.android.xyzreaderphong.view.ArticleListView;

public final class ArticleListActivity extends BaseActivity<ArticleListPresenter, ArticleListView> implements ArticleListView {
    @Inject
    PresenterFactory<ArticleListPresenter> mPresenterFactory;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ButterKnife.bind(this);

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
