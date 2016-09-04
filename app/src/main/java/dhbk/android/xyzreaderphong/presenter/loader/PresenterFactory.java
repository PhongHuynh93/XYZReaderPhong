package dhbk.android.xyzreaderphong.presenter.loader;

import android.support.annotation.NonNull;

import dhbk.android.xyzreaderphong.presenter.BasePresenter;

/**
 * Factory to implement to create a presenter
 */
public interface PresenterFactory<T extends BasePresenter> {
    @NonNull
    T create();
}
