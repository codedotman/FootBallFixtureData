package ng.sampleproject.sampleproject.view.LeagueDetails.table

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by USER on 10/08/2019.
 */
@Module
abstract class TableFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(TableModule::class))
    internal abstract fun contributeTableProvider(): TableFragment
}