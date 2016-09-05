package dhbk.android.xyzreaderphong.view;

import android.support.annotation.UiThread;

import java.util.List;

import dhbk.android.xyzreaderphong.interactor.XYZResponse;

@UiThread
public interface ArticleListView {

    /**
     * check to see if there are any active networks.
     * @return
     */
    boolean isConnectedToNetwork();

    /**
     * show the refresh indicator
     */
    void showRefreshIndicator();

    /**
     * stop showing refresh indicator
     */
    void stopShowRefreshIndicator();

    /**
     * show a toast to indicate that that there was something wrong when loading the data from network
     */
    void showFailLoadingDataMessage();

    /**
     * add list of data to adapter
     * @param xyzResponse
     */
    void addNewDataToRecyclerview(List<XYZResponse> xyzResponse);
}