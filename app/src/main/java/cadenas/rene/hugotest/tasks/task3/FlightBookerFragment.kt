package cadenas.rene.hugotest.tasks.task3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import cadenas.rene.hugotest.R
import kotlinx.android.synthetic.main.fragment_flight_booker.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class FlightBookerFragment : Fragment() {

    private val options = listOf("one-way flight", "return flight")
    private lateinit var format: SimpleDateFormat
    private val regex = Regex("\\d{2}.\\d{2}.\\d{4}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        format.isLenient = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flight_booker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item, options)

        spinner.onItemSelectedListener = object:  AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                input2.isEnabled = position == 1
                if (position == 1) {
                    button.isEnabled = false
                    input2.text = input1.text
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        format.format(Date()).let {
            input1.setText(it)
            input2.setText(it)
        }

        button.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Done!")
                .setMessage("You have booked a ${spinner.selectedItem} on ${input1.text}.")
                .setPositiveButton("Ok") { d, _ -> d.dismiss() }
                .show()
        }

        /**
            Alert!!!
            The following is intended for Test only.
            It is open for many date errors, in a real case it is mandatory to use a date library
         */

        input1.addTextChangedListener {
            try {
                if (it.toString().matches(regex)) {
                    input1.error = null
                    button.isEnabled = true
                }else {
                    button.isEnabled = false
                    throw ParseException("", 0)
                }
            }catch (exception: Exception) {
                input1.error = "Incorrect date"
            }
        }

        input2.addTextChangedListener {
            try {
                if (it.toString().matches(regex)) {
                    input2.error = null

                    val date1 = format.parse(input1.text.toString())
                    val date2 = format.parse(it.toString())

                    val start = date1!!.time
                    val end = date2!!.time

                    button.isEnabled = end > start

                }else {
                    button.isEnabled = false
                    throw ParseException("", 0)
                }
            }catch (exception: Exception) {
                input2.error = "Incorrect date"
            }
        }



    }

    /**
     * Next way it works but has some bugs with SimpleDateFormat, for that reason i use regex instead this
     */

    /*

     input1.addTextChangedListener {
            try {
                format.parse(it.toString())
            } catch (exception: Exception){
                debug("error parsing values")
                input1.error = "Incorrect date"
            }
        }

        input2.addTextChangedListener {
            try {

                val date1 = format.parse(input1.text.toString())
                val date2 = format.parse(it.toString())

                debug("$date1 - ${date1?.time}")
                debug("$date2 - ${date2?.time}")

                val start = date1!!.time
                val end = date2!!.time

                button.isEnabled = end > start

            }catch (exception: Exception) {
                input2.error = "incorrect date"
            }
        }

     */


}