package ng.sampleproject.sampleproject.data.network

import javax.inject.Inject

import io.reactivex.Observable
import ng.sampleproject.sampleproject.data.models.*
import ng.sampleproject.sampleproject.data.network.FootballDataApi

/**
 * Created by USER on 24/07/2019.
 */

class Repository @Inject constructor(private val footballDataApi: FootballDataApi) {

    fun getCompetition(): Observable<CompetitionResponse>{
        return footballDataApi.getCompetitions()
    }

    fun getMatches(): Observable<MatchesResponse> {
        return footballDataApi.getMatches()
    }

    fun getTable(leagueID : Int): Observable<StandingResponse> {
        return footballDataApi.getLeagueTable(leagueID)
    }

    fun getClubs(leagueID : Int): Observable<TeamsResponse> {
        return footballDataApi.getClubs(leagueID)
    }

    fun getLeagueMatches(leagueID : Int): Observable<MatchesResponse> {
        return footballDataApi.getLeagueFixtures(leagueID)
    }

    fun getPlayers(leagueID : Int): Observable<TeamPlayerResponse> {
        return footballDataApi.getClubTeam(leagueID)
    }


}
