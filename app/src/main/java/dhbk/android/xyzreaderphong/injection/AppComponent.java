package dhbk.android.xyzreaderphong.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dhbk.android.xyzreaderphong.App;

@Singleton
@Component(modules = {AppModule.class, RepositionModule.class, RetrofitModule.class})
public interface AppComponent {
    Context getAppContext();

    App getApp();
}