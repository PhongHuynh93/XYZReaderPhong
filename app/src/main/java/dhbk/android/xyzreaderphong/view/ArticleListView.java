package dhbk.android.xyzreaderphong.view;

import android.support.annotation.UiThread;

@UiThread
public interface ArticleListView {

    /**
     * check to see if there are any active networks.
     * @return
     */
    boolean isConnectedToNetwork();
}