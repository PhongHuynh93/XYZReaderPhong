package dhbk.android.xyzreaderphong.injection;

import dhbk.android.xyzreaderphong.view.impl.ArticleListActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ArticleListViewModule.class)
public interface ArticleListViewComponent {
    void inject(ArticleListActivity activity);
}