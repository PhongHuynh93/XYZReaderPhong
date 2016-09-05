package dhbk.android.xyzreaderphong.interactor;

import dhbk.android.xyzreaderphong.Constant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huynhducthanhphong on 9/4/16.
 */
public class XYZApiAdapter {

    public static Retrofit getInstance() {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }
}
