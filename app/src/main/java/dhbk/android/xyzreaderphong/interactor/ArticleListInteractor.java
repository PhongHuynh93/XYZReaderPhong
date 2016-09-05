package dhbk.android.xyzreaderphong.interactor;

import java.util.List;

public interface ArticleListInteractor extends BaseInteractor {
    /**
     * download data from network
     * @param callback to invoke the presenter if success or failed when loading data
     */
    void downloadDataFromNetwork(DownloadDataFromNetworkCallback callback);

    /**
     * insert xyzResponse to the database
     * @param xyzResponse
     */
    void insertToDb(XYZResponse xyzResponse);

    interface DownloadDataFromNetworkCallback {
        void onSuccess(List<XYZResponse> xyzResponse);
        void onFailed();
    }
}