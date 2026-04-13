package com.example.demo_advanced_testing_techniques

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.demo_advanced_testing_techniques.data.User
import com.example.demo_advanced_testing_techniques.data.UserDao
import com.example.demo_advanced_testing_techniques.data.UserDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RoomDaoTest {
    private lateinit var database: UserDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userDao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetUser() = runBlocking {
        val user = User("1", "Test User", "test@example.com")
        userDao.insertUser(user)
        val allUsers = userDao.getAllUsers().first()
        assertEquals(1, allUsers.size)
        assertEquals(user, allUsers[0])
    }
}
