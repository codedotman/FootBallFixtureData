package ng.sampleproject.sampleproject.view.home.competitions

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ng.sampleproject.sampleproject.data.network.Repository
import ng.sampleproject.sampleproject.view.LeagueDetails.clubs.ClubViewModel
import ng.sampleproject.sampleproject.view.LeagueDetails.leagueMatches.LeagueMatchesViewModel
import ng.sampleproject.sampleproject.view.LeagueDetails.table.TableViewModel
import ng.sampleproject.sampleproject.view.home.matches.MatchesViewModel

class CompetitionViewModelFactory constructor(private val mRepository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(CompetitionViewModel::class.java))
            CompetitionViewModel(mRepository) as T
        else if (modelClass.isAssignableFrom(MatchesViewModel::class.java))
            MatchesViewModel(mRepository) as T
        else if (modelClass.isAssignableFrom(TableViewModel::class.java))
            TableViewModel(mRepository) as T
        else if (modelClass.isAssignableFrom(ClubViewModel::class.java))
            ClubViewModel(mRepository) as T
        else if (modelClass.isAssignableFrom(LeagueMatchesViewModel::class.java))
            LeagueMatchesViewModel(mRepository) as T
        else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}