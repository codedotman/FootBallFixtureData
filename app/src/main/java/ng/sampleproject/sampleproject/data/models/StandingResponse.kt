package ng.sampleproject.sampleproject.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class StandingResponse (

        @SerializedName("standings")
        @Expose
        var standings: List<Standing> = ArrayList(),
        var errorMessage: String = ""
)

data class Standing (

        @SerializedName("table")
        @Expose
        var table: List<Table> = ArrayList()

)

data class Table (

        @SerializedName("position")
        @Expose
        var position: Int? = null,
        @SerializedName("team")
        var team: Team = Team(),
        @SerializedName("playedGames")
        @Expose
        var playedGames: Int? = null,
        @SerializedName("points")
        @Expose
        var points: Int? = null,
        @SerializedName("goalsFor")
        @Expose
        var goalsFor: Int? = null,
        @SerializedName("goalsAgainst")
        @Expose
        var goalsAgainst: Int? = null,
        @SerializedName("goalDifference")
        @Expose
        var goalDifference: Int? = null,
        var errorMessage: String = ""



)

data class Team (

        @SerializedName("id")
        @Expose
        var id: Int = 0,
        @SerializedName("name")
        @Expose
        var name: String? = null,
        @SerializedName("crestUrl")
        @Expose
        var crestUrl: String? = ""

)





