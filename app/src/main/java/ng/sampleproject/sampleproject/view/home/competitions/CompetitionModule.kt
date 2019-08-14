package ng.sampleproject.sampleproject.view.home.competitions



import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ng.sampleproject.sampleproject.data.network.Repository

/**
 * Created by USER on 09/08/2019.
 */
@Module
class CompetitionModule {

    @Provides
    fun provideViewModel(repository: Repository): CompetitionViewModel {
        return CompetitionViewModel(repository)
    }

    @Provides
    fun getViewModelFactory(myRepository: Repository): ViewModelProvider.Factory {
        return CompetitionViewModelFactory(myRepository)
    }
}
