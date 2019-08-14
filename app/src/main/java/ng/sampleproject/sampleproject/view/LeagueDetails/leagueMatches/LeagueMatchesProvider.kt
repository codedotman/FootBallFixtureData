package ng.sampleproject.sampleproject.view.LeagueDetails.leagueMatches

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by USER on 11/08/2019.
 */
@Module
abstract class LeagueMatchesProvider {

    @ContributesAndroidInjector(modules = arrayOf(LeagueMatchesModule::class))
    internal abstract fun contributeLeagueMatchesProvider(): LeagueMatchesFragment
}