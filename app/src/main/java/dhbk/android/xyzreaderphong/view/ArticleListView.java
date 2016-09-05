package dhbk.android.xyzreaderphong.view;

import android.support.annotation.UiThread;

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
}