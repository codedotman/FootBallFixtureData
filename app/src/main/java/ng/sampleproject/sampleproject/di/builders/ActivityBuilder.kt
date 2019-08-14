package ng.sampleproject.sampleproject.di.builders

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ng.sampleproject.sampleproject.view.LeagueDetails.LeagueDetailsActivity
import ng.sampleproject.sampleproject.view.LeagueDetails.clubs.ClubFragmentProvider
import ng.sampleproject.sampleproject.view.LeagueDetails.leagueMatches.LeagueMatchesProvider
import ng.sampleproject.sampleproject.view.LeagueDetails.table.TableFragmentProvider
import ng.sampleproject.sampleproject.view.home.competitions.CompetitionFragmentProvider
import ng.sampleproject.sampleproject.view.home.HomeActivity
import ng.sampleproject.sampleproject.view.home.matches.MatchesFragmentProvider

/**
 * Created by USER on 09/08/2019.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(CompetitionFragmentProvider::class, MatchesFragmentProvider::class))
    internal abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = arrayOf(TableFragmentProvider::class, ClubFragmentProvider::class, LeagueMatchesProvider::class))
    internal abstract fun contributeLeagueDetailsActivity(): LeagueDetailsActivity
}
