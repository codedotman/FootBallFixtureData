package ng.sampleproject.sampleproject.view.LeagueDetails.leagueMatches

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ng.sampleproject.sampleproject.data.network.Repository
import ng.sampleproject.sampleproject.view.home.competitions.CompetitionViewModelFactory

/**
 * Created by USER on 11/08/2019.
 */
@Module
class LeagueMatchesModule {

    @Provides
    fun provideViewModel(repository: Repository): LeagueMatchesViewModel {
        return LeagueMatchesViewModel(repository)
    }

    @Provides
    fun getViewModelFactory(myRepository: Repository): ViewModelProvider.Factory {
        return CompetitionViewModelFactory(myRepository)
    }
}