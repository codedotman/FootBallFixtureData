package ng.sampleproject.sampleproject.view.home.competitions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.schedulers.Schedulers
import ng.sampleproject.sampleproject.data.network.Repository
import ng.sampleproject.sampleproject.data.models.CompetitionResponse

/**
 * Created by USER on 24/07/2019.
 */

class CompetitionViewModel @Inject
constructor(private val repository: Repository
) : ViewModel() {
    private val disposables = CompositeDisposable()

    fun getCompetitions(): LiveData<CompetitionResponse> {

        val data = MutableLiveData<CompetitionResponse>()


        disposables.add(repository.getCompetition()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.competitions.isEmpty()){
                                data.setValue(CompetitionResponse(errorMessage = "empty result"))

                            }else{
                                data.setValue(result)
                            } }, { error ->
                    data.setValue(CompetitionResponse(errorMessage = error.message.toString()))
                }))
        return data
    }


    override fun onCleared() {
        disposables.clear()
    }
}
