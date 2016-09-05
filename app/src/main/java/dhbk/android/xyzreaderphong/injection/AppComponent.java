package dhbk.android.xyzreaderphong.injection;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dhbk.android.xyzreaderphong.App;
import dhbk.android.xyzreaderphong.interactor.ItemTableDao;
import dhbk.android.xyzreaderphong.interactor.XYZApiService;

@Singleton
@Component(modules = {AppModule.class, RepositionModule.class, RetrofitModule.class})
public interface AppComponent {
    Context getAppContext();

    App getApp();

    ItemTableDao getItemTableDao();

    XYZApiService getXYZApiService();
}