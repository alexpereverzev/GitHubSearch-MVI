package project.test.ru.githubsearch.di

import dagger.Module
import dagger.Provides
import project.test.ru.githubsearch.interactor.SearchInteractor
import project.test.ru.githubsearch.view.MainPresenter
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    internal fun provideMainPresenter(searchInteractor: SearchInteractor) = MainPresenter(searchInteractor)

}