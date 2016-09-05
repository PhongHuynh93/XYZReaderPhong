package dhbk.android.xyzreaderphong;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.Time;
import android.widget.Toast;

public class UpdaterService extends IntentService {
    private static final String BROADCAST_ACTION_STATE_CHANGE = "state_change";
    private static final String EXTRA_REFRESHING = "refreshing";

    public UpdaterService() {
        super("UpdaterService");
    }

    public static void startActionDownloadInfo(Context context) {
        Intent intent = new Intent(context, UpdaterService.class);
        context.startService(intent);
    }

    /**
     * run service in the background
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Time time = new Time();

        // check network connection
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            // if not have network, not call the service
            Toast.makeText(UpdaterService.this, "Not online, not refreshing.", Toast.LENGTH_SHORT).show();
            return;
        }

        // if there is an active network, send broadcast
        sendStickyBroadcast(
                new Intent(BROADCAST_ACTION_STATE_CHANGE).putExtra(EXTRA_REFRESHING, true));

        // TODO: 9/4/16 use retrofit to get the data from the url
    }

}
