package dam_a46304.shinmegamitenseidemonica

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromStringList(skills: List<String>): String {
        return Gson().toJson(skills)
    }

    @TypeConverter
    fun fromIntList(stats: List<Int>): String {
        return Gson().toJson(stats)
    }

    @TypeConverter
    fun toStringList(skills: String): List<String> {
        val collectionType =  object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(skills, collectionType)
    }

    @TypeConverter
    fun toIntList(stats: String): List<Int> {
        val collectionType =  object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(stats, collectionType)
    }
}