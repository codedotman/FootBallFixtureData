package ng.sampleproject.sampleproject.di.module

import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import ng.sampleproject.sampleproject.LiveFootballApplication
import ng.sampleproject.sampleproject.data.network.FootballDataApi
import ng.sampleproject.sampleproject.data.network.Repository

/**
 * Created by USER on 08/08/2019.
 */
@Module
class LiveFootballMainModule {

    @Singleton
    @Provides
    fun provideContext(application: LiveFootballApplication): Context {
        return application
    }

    @Provides
    internal fun provideRepository(footballDataApi: FootballDataApi): Repository {
        return Repository(footballDataApi)
    }
}
