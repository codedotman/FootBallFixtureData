package ng.sampleproject.sampleproject.view.LeagueDetails.clubs

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ng.sampleproject.sampleproject.view.LeagueDetails.table.TableFragment
import ng.sampleproject.sampleproject.view.LeagueDetails.table.TableModule

/**
 * Created by USER on 10/08/2019.
 */
@Module
abstract class ClubFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(ClubModule::class))
    internal abstract fun contributeClubProvider(): ClubFragment
}