package com.example.demo_advanced_testing_techniques.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    val name: String,
    val email: String
)

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
    
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: String): User?
}

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
