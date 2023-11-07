package dam_a46304.shinmegamitenseidemonica.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity( tableName = "Demon")
@IgnoreExtraProperties
data class Demon(
        @PrimaryKey val name: String = "",
        val align: String = "",
        val imgURL: String = "",
        val lvl: Int = 0,
        val race: String = "",
        val resists: String = "",
        var skills: List<String> = listOf(""),
        val stats: List<Int> = listOf(0),
        val origin: String = "",
        val background: String = ""
) : Parcelable {

    fun getLvlString(): String = String.format("Lvl: %d",lvl)
    fun getPhysString(): String = resists[0].toString().capitalize()
    fun getGunString(): String = resists[1].toString().capitalize()
    fun getFireString(): String = resists[2].toString().capitalize()
    fun getIceString(): String = resists[3].toString().capitalize()
    fun getZapString(): String = resists[4].toString().capitalize()
    fun getWindString(): String = resists[5].toString().capitalize()
    fun getLightString(): String = resists[6].toString().capitalize()
    fun getCurseString(): String = resists[7].toString().capitalize()

    fun getHP():Int = stats[0]
    fun getMP():Int = stats[1]
    fun getST():Int = stats[2]
    fun getMA():Int = stats[3]
    fun getVI():Int = stats[4]
    fun getAG():Int = stats[5]
    fun getLU():Int = stats[6]

    fun getHPString():String = stats[0].toString()
    fun getMPString():String = stats[1].toString()
    fun getSTString():String = stats[2].toString()
    fun getMAString():String = stats[3].toString()
    fun getVIString():String = stats[4].toString()
    fun getAGString():String = stats[5].toString()
    fun getLUString():String = stats[6].toString()
}
