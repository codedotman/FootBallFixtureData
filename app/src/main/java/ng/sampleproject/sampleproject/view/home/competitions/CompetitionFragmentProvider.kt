package ng.sampleproject.sampleproject.view.home.competitions

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by USER on 10/08/2019.
 */
@Module
abstract class CompetitionFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(CompetitionModule::class))
    internal abstract fun contributeCompetitionProvider(): CompetitionFragment
}