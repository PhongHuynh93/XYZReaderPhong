package dhbk.android.xyzreaderphong.presenter;

import dhbk.android.xyzreaderphong.view.ArticleListView;

public interface ArticleListPresenter extends BasePresenter<ArticleListView> {
    /**
     * call the interactor to load data to listview
     */
    void loadDataToRecyclerView();
}