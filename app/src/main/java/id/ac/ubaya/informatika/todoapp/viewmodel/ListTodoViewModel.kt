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
import java.util.*
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    fun refresh() {
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            todoLD.value = db.todoDao().selectTodoUndone()
        }
    }


//    fun refresh(){
//        launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
//
//            todoLD.value = db.todoDao().selectAllTodo()
//        }
//    }

//    fun clearTask(todo:Todo){
//        launch {
////            val db = Room.databaseBuilder(getApplication(),
////                TodoDatabase::class.java, "tododb").build()
//            val db = buildDB(getApplication())
//            db.todoDao().deleteTodo(todo)
//            todoLD.value = db.todoDao().selectAllTodo()
//        }
//    }

    fun doneTask(uuid: Int){
        launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            db.todoDao().todoDone(uuid)
            todoLD.value = db.todoDao().selectTodoUndone()
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}