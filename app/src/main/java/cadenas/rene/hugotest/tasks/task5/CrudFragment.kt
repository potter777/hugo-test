package cadenas.rene.hugotest.tasks.task5

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cadenas.rene.hugotest.R
import cadenas.rene.hugotest.tasks.task5.db.entities.User
import kotlinx.android.synthetic.main.fragment_crud.*
import org.koin.android.viewmodel.ext.android.viewModel


class CrudFragment : Fragment() {

    companion object {
        const val RC_EDITOR = 100
    }

    private val myViewModel by viewModel<UserViewModel>()
    private lateinit var adapter: UserAdapter
    private val observer = Observer<List<User>> { users -> users.forEach { adapter.add(it) } }
    private val onSelected: (user: User) -> Unit = { callEditor(it) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_EDITOR) {
            data?.getParcelableExtra<User>("user")?.let {
                when(data.getStringExtra("action")) {
                    "CREATED" -> adapter.add(it)
                    "UPDATED" -> adapter.update()
                    "DELETED" -> adapter.remove(it)
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = UserAdapter(onSelected)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crud, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.listenUsers().observe(viewLifecycleOwner, observer)

        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CrudFragment.adapter
        }

        if (adapter.itemCount == 0) {
            myViewModel.get()
        }

        floatingActionButton.setOnClickListener {
            callEditor(null)
        }
    }

    private fun callEditor(user: User?) {
        val dialog = UserEditorFragment.newInstance(user)
        dialog.setTargetFragment(this, RC_EDITOR)
        dialog.show(requireFragmentManager(), "Editor")
    }

}