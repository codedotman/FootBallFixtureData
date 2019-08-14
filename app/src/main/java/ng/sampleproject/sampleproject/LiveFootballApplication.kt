package ng.sampleproject.sampleproject


import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ng.sampleproject.sampleproject.di.component.DaggerLiveFootballComponent

/**
 * Created by USER on 08/08/2019.
 */

class LiveFootballApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerLiveFootballComponent.builder().create(this)
    }

}
