package cadenas.rene.hugotest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cadenas.rene.hugotest.tasks.TaskContainerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val onClick = View.OnClickListener {
        val task = when (it.id) {
            R.id.task_1 -> 1
            R.id.task_2 -> 2
            R.id.task_3 -> 3
            R.id.task_4 -> 4
            R.id.task_5 -> 5
            else -> 0
        }

        val intent = Intent(this, TaskContainerActivity::class.java)
        intent.putExtra("task", task)
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    /**
     * Prepares each interaction view
     */
    private fun initViews() {
        task_1.setOnClickListener(onClick)
        task_2.setOnClickListener(onClick)
        task_3.setOnClickListener(onClick)
        task_4.setOnClickListener(onClick)
        task_5.setOnClickListener(onClick)
    }
}