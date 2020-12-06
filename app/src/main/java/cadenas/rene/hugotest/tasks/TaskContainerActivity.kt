package cadenas.rene.hugotest.tasks

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cadenas.rene.hugotest.R
import cadenas.rene.hugotest.behavior.InteractionsListener
import cadenas.rene.hugotest.tasks.task1.CounterFragment
import cadenas.rene.hugotest.tasks.task2.TemperatureConverterFragment
import cadenas.rene.hugotest.tasks.task3.FlightBookerFragment
import cadenas.rene.hugotest.tasks.task4.TimerFragment
import cadenas.rene.hugotest.tasks.task5.CrudFragment

class TaskContainerActivity : AppCompatActivity(), InteractionsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_container)

        val taskNumber = intent.getIntExtra("task", 0)

        val introFragment = TaskIntroFragment.newInstance(IntroductionModel.build(taskNumber))
        putFragment(introFragment)
    }

    override fun onShowTask() {

        when(intent.getIntExtra("task", 0)) {
            1 -> putFragment(CounterFragment())
            2 -> putFragment(TemperatureConverterFragment())
            3 -> putFragment(FlightBookerFragment())
            4 -> putFragment(TimerFragment())
            5 -> putFragment(CrudFragment())
        }

    }

    private fun putFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}