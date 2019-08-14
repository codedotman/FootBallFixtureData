package ng.sampleproject.sampleproject.di.component

import javax.inject.Singleton

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ng.sampleproject.sampleproject.LiveFootballApplication
import ng.sampleproject.sampleproject.di.builders.ActivityBuilder
import ng.sampleproject.sampleproject.di.module.LiveFootballMainModule
import ng.sampleproject.sampleproject.di.module.LiveFootballModule

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, LiveFootballModule::class, LiveFootballMainModule::class, ActivityBuilder::class))
interface LiveFootballComponent : AndroidInjector<LiveFootballApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<LiveFootballApplication>()
}
