package ng.sampleproject.sampleproject.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by USER on 12/08/2019.
 */
data class TeamPlayerResponse (
    @SerializedName("crestUrl")
    var crestUrl: String = "",
    @SerializedName("squad")
    var squad: List<Player> = ArrayList(),
    @SerializedName("name")
    var name: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("shortName")
    var shortName: String = "",
    var errorMessage: String = ""

)

@Parcelize
data class Player(
        @SerializedName("role")
        var role: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("position")
        var position: String = "",
        var errorMessage: String = ""

): Parcelable