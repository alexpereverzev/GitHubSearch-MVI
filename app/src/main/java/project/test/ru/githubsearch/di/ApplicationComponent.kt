package project.test.ru.githubsearch.di

import dagger.Component
import project.test.ru.githubsearch.App
import project.test.ru.githubsearch.view.MainActivity
import javax.inject.Singleton

@Component(modules = arrayOf(ApiModule::class, InteractorModule::class, RepositoryModule::class, PresenterModule::class))
@Singleton
interface ApplicationComponent {

    fun inject(app: App)
    fun inject(mainActivity: MainActivity)
}