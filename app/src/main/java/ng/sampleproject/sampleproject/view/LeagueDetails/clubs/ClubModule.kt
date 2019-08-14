package ng.sampleproject.sampleproject.view.LeagueDetails.clubs

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ng.sampleproject.sampleproject.data.network.Repository
import ng.sampleproject.sampleproject.view.home.competitions.CompetitionViewModelFactory

/**
 * Created by USER on 10/08/2019.
 */
@Module
class ClubModule {

    @Provides
    fun provideViewModel(repository: Repository): ClubViewModel {
        return ClubViewModel(repository)
    }

    @Provides
    fun getViewModelFactory(myRepository: Repository): ViewModelProvider.Factory {
        return CompetitionViewModelFactory(myRepository)
    }
}