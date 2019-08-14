package ng.sampleproject.sampleproject.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by USER on 10/08/2019.
 */
data class MatchesResponse (
        @SerializedName("matches")
        @Expose
        var matches: List<Match> = ArrayList(),
        var errorMessage: String = ""
)

data class Match (
        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("utcDate")
        @Expose
        var utcDate: String? = null,
        @SerializedName("status")
        @Expose
        var status: String? = null,
        @SerializedName("matchday")
        @Expose
        var matchday: Int? = null,
        @SerializedName("stage")
        @Expose
        var stage: String? = null,
        @SerializedName("group")
        @Expose
        var group: String? = null,
        @SerializedName("lastUpdated")
        @Expose
        var lastUpdated: String? = null,
        @SerializedName("score")
        @Expose
        var score: Score? = null,
        @SerializedName("homeTeam")
        @Expose
        var homeTeam: HomeTeam? = null,
        @SerializedName("awayTeam")
        @Expose
        var awayTeam: AwayTeam? = null
)

data class FullTime (

        @SerializedName("homeTeam")
        @Expose
        var homeTeam: Int? = null,
        @SerializedName("awayTeam")
        @Expose
        var awayTeam: Int? = null

)

data class HomeTeam (

        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null
)

data class AwayTeam (

        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null
)

data class Score (

        @SerializedName("winner")
        @Expose
        var winner: Any? = null,
        @SerializedName("duration")
        @Expose
        var duration: String? = null,
        @SerializedName("fullTime")
        @Expose
        var fullTime: FullTime? = null


)





