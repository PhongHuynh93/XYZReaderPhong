package dhbk.android.xyzreaderphong.view.impl;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dhbk.android.xyzreaderphong.R;
import dhbk.android.xyzreaderphong.injection.AppComponent;
import dhbk.android.xyzreaderphong.injection.ArticleListViewModule;
import dhbk.android.xyzreaderphong.injection.DaggerArticleListViewComponent;
import dhbk.android.xyzreaderphong.interactor.XYZResponse;
import dhbk.android.xyzreaderphong.presenter.ArticleListPresenter;
import dhbk.android.xyzreaderphong.presenter.loader.PresenterFactory;
import dhbk.android.xyzreaderphong.view.ArticleListView;

public final class ArticleListActivity extends BaseActivity<ArticleListPresenter, ArticleListView> implements ArticleListView, SwipeRefreshLayout.OnRefreshListener {
    private static final String FIRST_TIME_LOAD_DATA = "first_time";
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
    @BindString(R.string.all_not_connect_to_network)
    String mNotConnectToNetworkMessage;
    @BindString(R.string.all_fail_to_connect_to_network)
    String mFailToConnectToNetworkMessage;

    private boolean mFirstTimeLoadData = true;
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

        // todo - get the data from db + load data to recyclerview
        if (mPresenter != null) {
            mPresenter.loadDataToRecyclerViewFromDb();
        }

        // if the first time the activity start (if condif change, it is not the first time)
        // load app from network
        if (mFirstTimeLoadData) {
            if (mPresenter != null) {
                mPresenter.loadDataToRecyclerViewFromNetwork();
            }
        }

        mFirstTimeLoadData = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(FIRST_TIME_LOAD_DATA, mFirstTimeLoadData);
    }

    // TODO: 9/4/16 add broadcast receiver to listen when the network was called.

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerArticleListViewComponent.builder()
                .appComponent(parentComponent)
                .articleListViewModule(new ArticleListViewModule(this))
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<ArticleListPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    /**
     *  - will call when the swipe action is trigger
     */
    @Override
    public void onRefresh() {
        if (mPresenter != null) {
            mPresenter.loadDataToRecyclerViewFromNetwork();
        }
    }

    /**
     * check to see if there are any active networks.
     *
     * @return false: there are not any active networks
     */
    @Override
    public boolean isConnectedToNetwork() {
        // check network connection
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null || !ni.isConnected()) {
            Toast.makeText(ArticleListActivity.this, mNotConnectToNetworkMessage, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * show the refresh indicator
     */
    @Override
    public void showRefreshIndicator() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    /**
     * stop showing refresh indicator
     */
    @Override
    public void stopShowRefreshIndicator() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * show a toast to indicate that that there was something wrong when loading the data from network
     */
    @Override
    public void showFailLoadingDataMessage() {
        Toast.makeText(ArticleListActivity.this, mFailToConnectToNetworkMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addNewDataToRecyclerview(List<XYZResponse> xyzResponse) {
        mAdapter.replaceAnotherData(xyzResponse);
    }
}
