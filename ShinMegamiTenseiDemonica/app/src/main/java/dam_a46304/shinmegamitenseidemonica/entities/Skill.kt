package dam_a46304.shinmegamitenseidemonica.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import dam_a46304.shinmegamitenseidemonica.R

@Entity( tableName = "Skill")
data class Skill(
    @PrimaryKey val name:String,
                val element:String,
){

    fun getImage(): String {

        var image:String = ""

        when(element){
            "phy" -> image = "https://static.wikia.nocookie.net/megamitensei/images/0/01/PhysIcon_SMTIV.png/revision/latest/scale-to-width-down/12?cb=20130502052917"
            "gun" -> image = "https://static.wikia.nocookie.net/megamitensei/images/9/9d/GunIcon2.png/revision/latest/scale-to-width-down/12?cb=20130502050223"
            "fir" -> image = "https://static.wikia.nocookie.net/megamitensei/images/3/3a/FireIcon_SMTIV.png/revision/latest/scale-to-width-down/12?cb=20130502052524"
            "ice" -> image = "https://static.wikia.nocookie.net/megamitensei/images/5/52/IceIcon_SMTIV.png/revision/latest/scale-to-width-down/12?cb=20130502052838"
            "ele" -> image = "https://static.wikia.nocookie.net/megamitensei/images/3/31/ElecIcon_SMTIV.png/revision/latest/scale-to-width-down/12?cb=20130502052816"
            "win" -> image = "https://static.wikia.nocookie.net/megamitensei/images/5/5c/ForceIcon.png/revision/latest/scale-to-width-down/12?cb=20130502045510"
            "exp" -> image = "https://static.wikia.nocookie.net/megamitensei/images/9/96/ExpelIcon_SMTIV.png/revision/latest/scale-to-width-down/12?cb=20130502052652"
            "cur" -> image = "https://static.wikia.nocookie.net/megamitensei/images/6/61/CurseIcon_SMTIV.png/revision/latest/scale-to-width-down/12?cb=20130502052455"
            "rec" -> image = "https://static.wikia.nocookie.net/megamitensei/images/c/c9/HealIcon_SMTIV.png/revision/latest?cb=20160930151951"
            "pas" -> image = "https://static.wikia.nocookie.net/megamitensei/images/c/c7/PassiveIcon_SMTIV.png/revision/latest?cb=20160930152019"
            "sup" -> image = "https://static.wikia.nocookie.net/megamitensei/images/a/a2/TMS_Support_Icon.png/revision/latest?cb=20160710174229"
            "alm" -> image = "https://static.wikia.nocookie.net/megamitensei/images/2/24/TMS_Almighty_Icon.png/revision/latest?cb=20160721001602"
            "ail" -> image = "https://static.wikia.nocookie.net/megamitensei/images/c/c7/AilmentIcon_SMTIV.png/revision/latest?cb=20160930151844"
        }

        return image
    }
}
