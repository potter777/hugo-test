package cadenas.rene.hugotest.tasks

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import cadenas.rene.hugotest.R
import cadenas.rene.hugotest.behavior.InteractionsListener
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_task_intro.*

@Parcelize
data class IntroductionModel(
    val id: Int = 0,
    @StringRes val title: Int = 0,
    @StringRes val challenge: Int = 0,
    @StringRes val message: Int = 0
): Parcelable {
    companion object {
        fun build(taskNumber: Int): IntroductionModel? {
            return when(taskNumber) {
                1 ->
                    IntroductionModel(taskNumber, R.string.task_1_title, R.string.task_1_challenge, R.string.task_1_summary)
                2 ->
                    IntroductionModel(taskNumber, R.string.task_2_title, R.string.task_2_challenge, R.string.task_2_summary)
                3 ->
                    IntroductionModel(taskNumber, R.string.task_3_title, R.string.task_3_challenge, R.string.task_3_summary)
                4 ->
                    IntroductionModel(taskNumber, R.string.task_4_title, R.string.task_4_challenge, R.string.task_4_summary)
                5 ->
                    IntroductionModel(taskNumber, R.string.task_5_title, R.string.task_5_challenge, R.string.task_5_summary)
                else -> null
            }
        }
    }
}

class TaskIntroFragment : Fragment() {

    companion object {

        fun newInstance(config: IntroductionModel?): TaskIntroFragment {
            return TaskIntroFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("task", config)
                }
            }
        }

    }

    private lateinit var interactions: InteractionsListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        interactions = context as InteractionsListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<IntroductionModel>("task")?.let {

            text_title.text = getString(it.title)
            text_challenge.text = getString(it.challenge)
            text_summary.text = getString(it.message)

        }

        button.setOnClickListener { interactions.onShowTask() }
    }

}