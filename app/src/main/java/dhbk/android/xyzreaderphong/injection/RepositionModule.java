package dhbk.android.xyzreaderphong.injection;

import android.content.Context;

import com.hannesdorfmann.sqlbrite.dao.DaoManager;

import dagger.Module;
import dagger.Provides;
import dhbk.android.xyzreaderphong.interactor.ItemTable;
import dhbk.android.xyzreaderphong.interactor.ItemsContract;

/**
 * Created by huynhducthanhphong on 9/4/16.
 * contains db
 */
@Module
public final class RepositionModule {

    public RepositionModule() {
    }

    /**
     * provides table used in this project
     * @return
     */
    @Local
    @Provides
    public ItemTable providesTable(Context context) {
        ItemTable itemTable = new ItemTable();
        // create database
        DaoManager.with(context)
                .databaseName(ItemsContract.DB_NAME)
                .version(1)
                .add(itemTable)
                .logging(true)
                .build();
        return itemTable;
    }

}
