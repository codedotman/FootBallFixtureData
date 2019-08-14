package ng.sampleproject.sampleproject.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CompetitionResponse (

    @SerializedName("competitions")
    @Expose
    var competitions: List<Competition> = ArrayList(),
    var errorMessage: String = ""
)

data class Competition (
        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null

)

