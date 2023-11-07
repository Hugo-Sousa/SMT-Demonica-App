package dam_a46304.shinmegamitenseidemonica.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SkillDao {

    @Insert( onConflict = OnConflictStrategy . REPLACE )
    suspend fun insertSkillList ( raceList : List<Skill>)
    @Insert( onConflict = OnConflictStrategy . REPLACE )
    suspend fun insertSkill ( race : Skill )
    @Query(" SELECT * FROM `Skill`")
    fun getAllSkills (): LiveData<List<Skill>>
    @Query(" SELECT * FROM `Skill` WHERE `name` IN (:name)")
    fun getSkillsByName (name:List<String>): List<Skill>
}