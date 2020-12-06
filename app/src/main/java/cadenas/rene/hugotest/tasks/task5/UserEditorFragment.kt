package cadenas.rene.hugotest.tasks.task5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import cadenas.rene.hugotest.R
import cadenas.rene.hugotest.tasks.task5.db.entities.User
import kotlinx.android.synthetic.main.fragment_user_editor.*
import org.koin.android.viewmodel.ext.android.viewModel


class UserEditorFragment : DialogFragment() {

    private val myViewModel by viewModel<UserViewModel>()

    companion object {

        fun newInstance(user: User? = null): UserEditorFragment {
            return UserEditorFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("user", user)
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mode(arguments?.getParcelable("user"))
        button_cancel_create.setOnClickListener { dismiss() }
        button_create.setOnClickListener {
            if (checkValues()) {
                val user = User(
                    name = input_name.text.toString(),
                    surName = input_surname.text.toString()
                )
                myViewModel.create(user).observe(viewLifecycleOwner, {
                    Toast.makeText(context, "Created", Toast.LENGTH_SHORT).show()
                    sendResult("CREATED", it)
                    dismiss()
                })
            }
        }
        button_edit.setOnClickListener {
            if (checkValues()) {
                arguments?.getParcelable<User>("user")?.let {
                    it.name = input_name.text.toString()
                    it.surName = input_surname.text.toString()
                    myViewModel.update(it).observe(viewLifecycleOwner, { user ->
                        Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show()
                        sendResult("UPDATED", user)
                        dismiss()
                    })
                }
            }
        }
        button_delete.setOnClickListener {
            arguments?.getParcelable<User>("user")?.let {
                myViewModel.delete(it).observe(viewLifecycleOwner) { user ->
                    Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show()
                    sendResult("DELETED", user)
                    dismiss()
                }
            }
        }
    }

    private fun mode(user: User?) {
        user?.let {
            // edit mode
            input_name.setText(it.name)
            input_surname.setText(it.surName)
            layout_edit.visibility = View.VISIBLE
        }?:kotlin.run {
            // create mode
            layout_create.visibility = View.VISIBLE
        }
    }

    private fun checkValues(): Boolean {

        if (input_name.text!!.isEmpty()) {
            input_name.error = "*"
            return false
        }

        if (input_surname.text!!.isEmpty()) {
            input_surname.error = "*"
            return false
        }

        return true
    }

    fun sendResult(action: String, user: User?) {
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, Intent().apply {
            putExtra("action", action)
            putExtra("user", user)
        })
    }

}