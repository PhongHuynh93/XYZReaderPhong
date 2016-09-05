package dhbk.android.xyzreaderphong.interactor;

public interface ArticleListInteractor extends BaseInteractor {
    void downloadDataFromNetwork(DownloadDataFromNetworkCallback callback);

    interface DownloadDataFromNetworkCallback {
        void onSuccess(XYZResponse xyzResponse);
        void onFailed();
    }
}