package ng.sampleproject.sampleproject.view.LeagueDetails.leagueMatches

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ng.sampleproject.sampleproject.data.network.Repository
import ng.sampleproject.sampleproject.data.models.MatchesResponse
import javax.inject.Inject

/**
 * Created by USER on 11/08/2019.
 */
class LeagueMatchesViewModel @Inject
constructor(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val TAG = LeagueMatchesViewModel::class.java.simpleName


    fun getLeagueMatches(leagueID : Int): LiveData<MatchesResponse> {

        val data = MutableLiveData<MatchesResponse>()

        disposables.add(repository.getLeagueMatches(leagueID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.matches.isEmpty()){
                                data.setValue(MatchesResponse(errorMessage = "empty result"))

                            }else{
                                data.setValue(result)
                            } }, { error ->
                    data.setValue(MatchesResponse(errorMessage = "error"))
                }
                ) )
        return data
    }

    override fun onCleared() {
        disposables.clear()
    }
}