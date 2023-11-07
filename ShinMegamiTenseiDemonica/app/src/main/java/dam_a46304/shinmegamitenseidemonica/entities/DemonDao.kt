package dam_a46304.shinmegamitenseidemonica.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DemonDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertDemonList ( demonList : List<Demon>)
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertDemon ( demon : Demon )


    @Query(" SELECT * FROM `Demon`")
    fun getAllDemons (): LiveData<List<Demon>>
    @Query(" SELECT * FROM `Demon` WHERE `name` == :name")
    fun getDemon (name : String): LiveData<Demon>
    @Query(" SELECT * FROM `Demon` WHERE `race` == :race")
    fun getDemonsByRace (race:String): LiveData<List<Demon>>
    @Query(" SELECT * FROM `Demon` WHERE `race` == :race AND `lvl` >= :lvl ORDER BY lvl")
    fun getDemonToFuse(race: String, lvl:Int): List<Demon>

}