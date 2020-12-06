package cadenas.rene.hugotest.tasks.task4

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import cadenas.rene.hugotest.R
import cadenas.rene.hugotest.debug
import kotlinx.android.synthetic.main.fragment_timer.*
import java.text.SimpleDateFormat
import java.util.*

class TimerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var timer: CountDownTimer? = null

    /**
     * Current second in timer
     */
    private var current = 0

    /**
     * The objective goal for timer. this can be adjusted even a timer is active
     */
    private var objective = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                text_duration.text = "Duración: $progress segs"

                timer?.let {
                    if (progress < objective) {
                        it.cancel()
                        restartValues()
                        Toast.makeText(context, "Timer was canceled", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(context, "Timer was set", Toast.LENGTH_SHORT).show()
                        startTimer()
                        objective = progress
                    }
                }?:kotlin.run {
                    objective = progress
                }


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        buttonStart.setOnClickListener {
            if (seekBar.progress > 0) {
                buttonStart.visibility = View.GONE
                buttonRestart.visibility = View.VISIBLE
                startTimer()
            }
        }
        buttonRestart.setOnClickListener { startTimer() }
    }

    /**
     * Starts the timer, if a timer is running, it will stop
     */
    private fun startTimer() {

        progressBar.progress = 0
        timer?.cancel()


        /**
         * If there is in progress, recalculate timer
         */
        val goal:Long = ((objective - current) * 1000).toLong()

        timer = object: CountDownTimer(goal, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val max = seekBar.progress
                current+= 1
                debug("max: $max, current: $current, progress: ${(current.toFloat() / max.toFloat())}")
                progressBar.progress = ((current.toFloat() / max.toFloat()) * 100).toInt()
            }

            override fun onFinish() {
                AlertDialog.Builder(requireContext())
                    .setMessage("Completed!")
                    .setPositiveButton("Ok") { d, _ -> d.dismiss() }
                    .show()
                restartValues()
            }

        }
        timer?.start()
    }

    fun restartValues() {
        buttonStart.visibility = View.VISIBLE
        buttonRestart.visibility = View.GONE
        current = 0
        progressBar.progress = 0
        timer = null
    }

}