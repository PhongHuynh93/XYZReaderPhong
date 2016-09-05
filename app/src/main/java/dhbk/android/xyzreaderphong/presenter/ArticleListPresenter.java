package dhbk.android.xyzreaderphong.presenter;

import dhbk.android.xyzreaderphong.view.ArticleListView;

public interface ArticleListPresenter extends BasePresenter<ArticleListView> {
    /**
     * call the interactor to load data from database to listview
     */
    void loadDataToRecyclerViewFromDb();

    /**
     * call the interactor to load data from network to listview
     */
    void loadDataToRecyclerViewFromNetwork();
}