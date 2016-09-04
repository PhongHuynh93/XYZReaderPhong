package dhbk.android.xyzreaderphong.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import dhbk.android.xyzreaderphong.R;
import dhbk.android.xyzreaderphong.injection.AppComponent;
import dhbk.android.xyzreaderphong.injection.ArticleListViewModule;
import dhbk.android.xyzreaderphong.injection.DaggerArticleListViewComponent;
import dhbk.android.xyzreaderphong.presenter.ArticleListPresenter;
import dhbk.android.xyzreaderphong.presenter.loader.PresenterFactory;
import dhbk.android.xyzreaderphong.view.ArticleListView;

public final class ArticleListActivity extends BaseActivity<ArticleListPresenter, ArticleListView> implements ArticleListView, SwipeRefreshLayout.OnRefreshListener {
    @Inject
    PresenterFactory<ArticleListPresenter> mPresenterFactory;
    @Inject
    ArticleListAdapter mAdapter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindInt(R.integer.grid_column_count)
    int columnCount;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        ButterKnife.bind(this);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.


//       5  listen when swipe
        mSwipeRefreshLayout.setOnRefreshListener(this);

        // show a toolbar
        setSupportActionBar(mToolbar);

//        but not showing the title in toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // create a recyclerview with 2 columns
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        // set adapter
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();

        // load data to recyclerview
        if (mPresenter != null) {
            mPresenter.loadDataToRecyclerView();
        }
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

    /**
     * todo - will call when the swipe action is trigger
     */
    @Override
    public void onRefresh() {

    }
}
