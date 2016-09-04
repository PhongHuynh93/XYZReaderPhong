package dhbk.android.xyzreaderphong.injection;

import android.content.Context;

import dhbk.android.xyzreaderphong.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    App getApp();
}