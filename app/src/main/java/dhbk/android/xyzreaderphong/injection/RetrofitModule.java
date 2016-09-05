package dhbk.android.xyzreaderphong.injection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dhbk.android.xyzreaderphong.interactor.XYZApiAdapter;
import dhbk.android.xyzreaderphong.interactor.XYZApiService;
import retrofit2.Retrofit;

/**
 * Created by huynhducthanhphong on 9/5/16.
 * <p>
 * <p>
 * contains retrofit instance and retrofit service for other class to use to connect to this
 */
@Module
public class RetrofitModule {
    // To send network requests to an API, we need to use the Retrofit instance
    @Provides
    @Singleton
    public Retrofit provideRetrofitInstance() {
        return XYZApiAdapter.getInstance();
    }

    // the para is the retrofit instance above
    // the return is the artist models define in SpotifyApiService
    @Provides
    @Singleton
    public XYZApiService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(XYZApiService.class);
    }
}
