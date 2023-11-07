package dam_a46304.shinmegamitenseidemonica.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
data class User(val username: String? = null,
                val password: String? = null,
                val email: String? = null,
                val demonStock: List<Demon>? = null){
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}