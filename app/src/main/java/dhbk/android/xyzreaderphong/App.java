package dhbk.android.xyzreaderphong;

import android.app.Application;
import android.support.annotation.NonNull;

import dhbk.android.xyzreaderphong.injection.AppComponent;
import dhbk.android.xyzreaderphong.injection.AppModule;
import dhbk.android.xyzreaderphong.injection.DaggerAppComponent;

public final class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}