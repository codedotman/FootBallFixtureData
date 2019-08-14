package ng.sampleproject.sampleproject.view.LeagueDetails.clubs

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ng.sampleproject.sampleproject.data.network.Repository
import ng.sampleproject.sampleproject.data.models.*
import javax.inject.Inject

/**
 * Created by USER on 10/08/2019.
 */
class ClubViewModel @Inject
constructor(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()


    fun getClub(leagueID : Int): LiveData<TeamsResponse> {

        val data = MutableLiveData<TeamsResponse>()

        disposables.add(repository.getClubs(leagueID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.teams.isEmpty()){
                                data.setValue(TeamsResponse(errorMessage = "empty result"))
                            }else{
                                data.setValue(result)

                            } }, { error ->
                    data.setValue(TeamsResponse(errorMessage = "error"))
                }
                ) )
        return data
    }

    fun getClubTeam(leagueID : Int): LiveData<TeamPlayerResponse> {

        val data = MutableLiveData<TeamPlayerResponse>()

        disposables.add(repository.getPlayers(leagueID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> data.setValue(result) }, { error ->
                    data.setValue(TeamPlayerResponse(errorMessage = error.message.toString()))
                }
                ) )
        return data
    }

    override fun onCleared() {
        disposables.clear()
    }
}