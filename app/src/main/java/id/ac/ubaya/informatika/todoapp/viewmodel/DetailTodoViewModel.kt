package id.ac.ubaya.informatika.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.todoapp.model.Todo
import id.ac.ubaya.informatika.todoapp.model.TodoDatabase
import id.ac.ubaya.informatika.todoapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    fun fetch(uuid:Int){
        launch {
            val db = buildDB(getApplication())
            todoLD.value = db.todoDao().selectTodo(uuid)
        }
    }

    fun update(title:String, notes:String, priority:Int, uuid:Int){
        launch {
            val db = buildDB(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }


    fun addTodo(todo:Todo) {
        launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            db.todoDao().insertAll(todo)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}