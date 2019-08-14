package ng.sampleproject.sampleproject.view.home.matches

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
 * Created by USER on 10/08/2019.
 */
class MatchesViewModel @Inject
constructor(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()


    fun getMatches(): LiveData<MatchesResponse> {

        val data = MutableLiveData<MatchesResponse>()

        disposables.add(repository.getMatches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (result.matches.isEmpty()){
                                data.setValue(MatchesResponse(errorMessage = "empty result"))

                            }else{
                                data.setValue(result)
                            } }, { error ->
                    data.setValue(MatchesResponse(errorMessage = error.message.toString()))
                }
                ) )
        return data
    }

    override fun onCleared() {
        disposables.clear()
    }
}