package cadenas.rene.hugotest.tasks.task5.db.dao

import androidx.room.*
import cadenas.rene.hugotest.tasks.task5.db.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM User")
    suspend fun get(): List<User>

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)

}