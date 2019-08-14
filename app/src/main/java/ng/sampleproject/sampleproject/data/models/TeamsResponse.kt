package ng.sampleproject.sampleproject.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TeamsResponse (

    @SerializedName("teams")
    @Expose
    var teams: List<Teams> = ArrayList(),
    var errorMessage: String = ""


)
data class Teams (
        @SerializedName("id")
        @Expose
        var id: Int = 0,

        @SerializedName("name")
        @Expose
        var name: String = "",

        @SerializedName("crestUrl")
        @Expose
        var crestUrl: String? = ""
)


