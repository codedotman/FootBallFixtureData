package ng.sampleproject.sampleproject.view.home.matches

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by USER on 10/08/2019.
 */
@Module
abstract class MatchesFragmentProvider {


    @ContributesAndroidInjector(modules = arrayOf(MatchesModule::class))
    internal abstract fun contributeMatchProvider(): MatchesFragment
}