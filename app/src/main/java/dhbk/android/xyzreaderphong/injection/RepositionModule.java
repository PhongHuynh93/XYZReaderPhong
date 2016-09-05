package dhbk.android.xyzreaderphong.injection;

import android.content.Context;

import com.hannesdorfmann.sqlbrite.dao.DaoManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dhbk.android.xyzreaderphong.interactor.ItemTableDao;
import dhbk.android.xyzreaderphong.interactor.ItemsContract;

/**
 * Created by huynhducthanhphong on 9/4/16.
 * contains db
 */
@Module
public final class RepositionModule {
    /**
     * provides table used in this project
     *
     * @return
     */
//    @Local
    @Provides
    @Singleton
    public ItemTableDao providesTable(Context context) {
        ItemTableDao itemTable = new ItemTableDao();
        // create database
        DaoManager.with(context)
                .databaseName(ItemsContract.DB_NAME)
                .version(ItemsContract.DB_VERSION)
                .add(itemTable)
                .logging(true)
                .build();
        return itemTable;
    }

}
