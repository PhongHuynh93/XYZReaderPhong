package dhbk.android.xyzreaderphong;

import android.app.Application;
import android.support.annotation.NonNull;

import dhbk.android.xyzreaderphong.injection.AppComponent;
import dhbk.android.xyzreaderphong.injection.AppModule;
import dhbk.android.xyzreaderphong.injection.DaggerAppComponent;
import dhbk.android.xyzreaderphong.injection.RepositionModule;
import dhbk.android.xyzreaderphong.injection.RetrofitModule;

public final class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .repositionModule(new RepositionModule())
                .retrofitModule(new RetrofitModule())
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}