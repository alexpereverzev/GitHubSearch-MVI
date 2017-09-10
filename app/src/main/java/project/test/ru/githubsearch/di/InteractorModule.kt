package project.test.ru.githubsearch.di

import dagger.Module
import dagger.Provides
import project.test.ru.githubsearch.interactor.SearchInteractor
import project.test.ru.githubsearch.repository.SearchRepository
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    internal fun provideSearchInteractor(searchRepository: SearchRepository) = SearchInteractor(searchRepository)

}
