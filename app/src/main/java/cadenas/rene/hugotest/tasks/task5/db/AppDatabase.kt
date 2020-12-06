package cadenas.rene.hugotest.tasks.task5.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cadenas.rene.hugotest.tasks.task5.db.dao.UserDao
import cadenas.rene.hugotest.tasks.task5.db.entities.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}