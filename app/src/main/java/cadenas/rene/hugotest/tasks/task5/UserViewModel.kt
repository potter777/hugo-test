package cadenas.rene.hugotest.tasks.task5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cadenas.rene.hugotest.tasks.task5.db.AppDatabase
import cadenas.rene.hugotest.tasks.task5.db.entities.User
import kotlinx.coroutines.launch

class UserViewModel(private val db: AppDatabase) : ViewModel() {

    private val users = MutableLiveData<List<User>>()

    fun listenUsers(): LiveData<List<User>> = users

    fun get() {
        viewModelScope.launch {
            val items = db.userDao().get()
            users.postValue(items)
        }
    }

    fun create(user: User): LiveData<User> {
        val live = MutableLiveData<User>()
        viewModelScope.launch {
            db.userDao().insert(user)
            live.postValue(user)
        }
        return live
    }

    fun update(user: User): LiveData<User> {
        val live = MutableLiveData<User>()
        viewModelScope.launch {
            db.userDao().update(user)
            live.postValue(user)
        }
        return live
    }

    fun delete(user: User): LiveData<User> {
        val live = MutableLiveData<User>()
        viewModelScope.launch {
            db.userDao().delete(user)
            live.postValue(user)
        }
        return live
    }

}