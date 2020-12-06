package cadenas.rene.hugotest.tasks.task1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cadenas.rene.hugotest.R
import kotlinx.android.synthetic.main.fragment_counter.*


class CounterFragment : Fragment() {

    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_counter.text = "$counter"
        button.setOnClickListener {
            text_counter.text = "${++counter}"
        }
    }

}