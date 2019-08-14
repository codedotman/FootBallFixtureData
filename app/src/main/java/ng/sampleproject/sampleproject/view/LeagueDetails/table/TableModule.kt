package ng.sampleproject.sampleproject.view.LeagueDetails.table

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ng.sampleproject.sampleproject.data.network.Repository
import ng.sampleproject.sampleproject.view.home.competitions.CompetitionViewModelFactory

/**
 * Created by USER on 10/08/2019.
 */
@Module
class TableModule {

    @Provides
    fun provideViewModel(repository: Repository): TableViewModel {
        return TableViewModel(repository)
    }

    @Provides
    fun getViewModelFactory(myRepository: Repository): ViewModelProvider.Factory {
        return CompetitionViewModelFactory(myRepository)
    }
}