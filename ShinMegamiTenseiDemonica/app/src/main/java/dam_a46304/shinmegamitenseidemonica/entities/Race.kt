package dam_a46304.shinmegamitenseidemonica.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity( tableName = "Race")
data class Race(
    @PrimaryKey val name: String,
                val alignment: String,
                val description: String,
                val results: List<String>
) : Parcelable {




}
