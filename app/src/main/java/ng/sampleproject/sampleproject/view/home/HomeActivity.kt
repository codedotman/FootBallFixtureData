package ng.sampleproject.sampleproject.view.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.view.home.competitions.CompetitionFragment
import ng.sampleproject.sampleproject.view.home.matches.MatchesFragment

class HomeActivity : DaggerAppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (p0.itemId) {
            R.id.navigation_matches -> {
                toolbar.title = fixtures
                fragment = MatchesFragment()
            }

            R.id.navigation_competitions -> {
                toolbar.title = competitions
                fragment = CompetitionFragment()
            }
        }
        return loadFragment(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadFragment(MatchesFragment())

        navigation.setOnNavigationItemSelectedListener(this)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.home_container, fragment)
                    .commit()
            return true
        }
        return false
    }

    companion object {
        private val fixtures = "Today's Fixtures"
        private val competitions = "Competitions"
    }
}
