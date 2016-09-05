package dhbk.android.xyzreaderphong.interactor;

import dhbk.android.xyzreaderphong.Constant;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by huynhducthanhphong on 9/4/16.
 *
 * define endpoint
 */
public interface XYZApiService {
    @GET(Constant.END_POINT)
    Observable<XYZResponse> searchXYZ();
}
