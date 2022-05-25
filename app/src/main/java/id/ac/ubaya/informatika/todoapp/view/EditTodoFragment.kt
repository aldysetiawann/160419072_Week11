package id.ac.ubaya.informatika.todoapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.todoapp.R
import id.ac.ubaya.informatika.todoapp.databinding.FragmentEditTodoBinding
import id.ac.ubaya.informatika.todoapp.model.Todo
import id.ac.ubaya.informatika.todoapp.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

class EditTodoFragment : Fragment(), RadioClickListener, TodoSaveChangesListener {
    private lateinit var viewModel:DetailTodoViewModel
    private lateinit var dataBinding:FragmentEditTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater, R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
//        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        dataBinding.radiolistener = this
        dataBinding.listener = this


        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
//            txtTitle.setText(it.title)
//            txtNotes.setText(it.notes)
//
//            when (it.priority) {
//                3 -> {
//                    radioHigh.isChecked = true
//                }
//                2 -> {
//                    radioMedium.isChecked = true
//                }
//                else -> {
//                    radioLow.isChecked = true
//                }
//            }
        })
    }

//    override fun onRadioClick(v: View, obj: Todo) {
//        obj.priority = v.tag.toString().toInt()
//    }

    override fun onTodoSaveChanges(v: View, obj: Todo) {
//        val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
        Log.d("cobacek", obj.toString())
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo updated", Toast.LENGTH_SHORT).show()
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = v.tag.toString().toInt()
    }
}