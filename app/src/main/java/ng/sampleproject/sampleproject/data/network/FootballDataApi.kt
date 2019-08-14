package ng.sampleproject.sampleproject.data.network


import io.reactivex.Observable
import ng.sampleproject.sampleproject.data.models.*
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by USER on 21/07/2019.
 */

interface FootballDataApi {

    @Headers("X-Auth-Token: 7b149ed85096435c8dce4239b7d72620")
    @GET("competitions?plan=TIER_ONE")
    fun getCompetitions(): Observable<CompetitionResponse>

    @Headers("X-Auth-Token: 7b149ed85096435c8dce4239b7d72620")
    @GET("matches")
    fun getMatches(): Observable<MatchesResponse>

    @Headers("X-Auth-Token: 7b149ed85096435c8dce4239b7d72620")
    @GET("competitions/{id}/standings")
    fun getLeagueTable(@Path("id") id: Int): Observable<StandingResponse>

    @Headers("X-Auth-Token: 7b149ed85096435c8dce4239b7d72620")
    @GET("competitions/{id}/teams")
    fun getClubs(@Path("id") id: Int): Observable<TeamsResponse>

    @Headers("X-Auth-Token: 7b149ed85096435c8dce4239b7d72620")
    @GET("competitions/{id}/matches")
    fun getLeagueFixtures(@Path("id") id: Int): Observable<MatchesResponse>

    @Headers("X-Auth-Token: 7b149ed85096435c8dce4239b7d72620")
    @GET("teams/{id}")
    fun getClubTeam(@Path("id") id: Int): Observable<TeamPlayerResponse>

}
