package ng.sampleproject.sampleproject.view.LeagueDetails.table

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ng.sampleproject.sampleproject.data.network.Repository
import ng.sampleproject.sampleproject.data.models.StandingResponse
import javax.inject.Inject

/**
 * Created by USER on 10/08/2019.
 */
class TableViewModel @Inject
constructor(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()


    fun getTable(leagueID : Int): LiveData<StandingResponse> {

        val data = MutableLiveData<StandingResponse>()

        disposables.add(repository.getTable(leagueID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.standings.isEmpty()){
                                data.setValue(StandingResponse(errorMessage = "error"))

                            }else{
                                data.setValue(result)
                            } }, { error ->
                    data.setValue(StandingResponse(errorMessage = "error"))
                }
                ) )
        return data
    }

    override fun onCleared() {
        disposables.clear()
    }
}