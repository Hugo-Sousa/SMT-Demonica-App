package dam_a46304.shinmegamitenseidemonica.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RaceDao {

    @Insert( onConflict = OnConflictStrategy . REPLACE )
    suspend fun insertRaceList ( raceList : List<Race>)
    @Insert( onConflict = OnConflictStrategy . REPLACE )
    suspend fun insertRace ( race : Race )
    @Query(" SELECT * FROM `Race`")
    fun getAllRaces (): LiveData<List<Race>>
    @Query(" SELECT * FROM `Race` WHERE `name` == :name")
    fun getRace (name:String): Race
}