package project.test.ru.githubsearch

import android.app.Application
import com.facebook.stetho.Stetho
import project.test.ru.githubsearch.di.*


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        appComponent = DaggerApplicationComponent.builder()
                .apiModule(ApiModule())
                .interactorModule(InteractorModule())
                .presenterModule(PresenterModule())
                .repositoryModule(RepositoryModule())
                .build()

        appComponent.inject(this)
    }

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}