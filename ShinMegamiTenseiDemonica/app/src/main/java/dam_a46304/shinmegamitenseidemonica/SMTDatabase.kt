package dam_a46304.shinmegamitenseidemonica

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dam_a46304.shinmegamitenseidemonica.entities.*
import dam_a46304.shinmegamitenseidemonica.workers.InitDemonTableWorker
import dam_a46304.shinmegamitenseidemonica.workers.InitRaceTableWorker
import dam_a46304.shinmegamitenseidemonica.workers.InitSkillTableWorker
//colocar resto das tabelas
@Database(entities = [Demon::class, Race::class, Skill::class], version = 7, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SMTDatabase: RoomDatabase() {

    abstract fun demonDao(): DemonDao
    abstract fun raceDao (): RaceDao
    abstract fun skillDao (): SkillDao

    companion object {
        // For Singleton instantiation
        @Volatile private var instance : SMTDatabase ? = null

        fun getInstance ( context : Context): SMTDatabase {
            if ( instance != null ) return instance !!

            synchronized ( this ) {
                instance = Room
                    . databaseBuilder (context,SMTDatabase::class.java, "SMTDatabase" ).allowMainThreadQueries()
                    . fallbackToDestructiveMigration()
                    . addCallback ( object : RoomDatabase.Callback(){
                        override fun onCreate ( db : SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<InitDemonTableWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                            val request1 = OneTimeWorkRequestBuilder<InitRaceTableWorker>().build()
                            WorkManager.getInstance(context).enqueue(request1)
                            val request2 = OneTimeWorkRequestBuilder<InitSkillTableWorker>().build()
                            WorkManager.getInstance(context).enqueue(request2)
                        }
                    })
                    . build()
            }
            return instance!!
        }
    }
}