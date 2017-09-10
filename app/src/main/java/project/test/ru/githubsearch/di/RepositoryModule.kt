package project.test.ru.githubsearch.di

import dagger.Module
import dagger.Provides
import project.test.ru.githubsearch.api.Api
import project.test.ru.githubsearch.repository.SearchRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideSearchRepository(api: Api) = SearchRepository(api)

}