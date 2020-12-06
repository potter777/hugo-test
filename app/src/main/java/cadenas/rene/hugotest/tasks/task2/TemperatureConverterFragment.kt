package cadenas.rene.hugotest.tasks.task2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import cadenas.rene.hugotest.BuildConfig
import cadenas.rene.hugotest.R
import kotlinx.android.synthetic.main.fragment_temperature_converter.*
import java.lang.NumberFormatException


class TemperatureConverterFragment : Fragment() {

    /**
     * Watches when the fahrenheit input change its value.
     */
    private val fahrenheitWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val value = s?.toString()?.toFloatOrNull()?.let { "${(it - 32f) * (5f/9f)}" }?:""
            input_celsius.setText(value)
        }
    }

    private val celsiusWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val value = s?.toString()?.toFloatOrNull()?.let { "${it * (9f/5f) + 32f}" }?:""
            input_fahrenheit.setText(value)
        }
    }

    private val onFocus = View.OnFocusChangeListener { v, hasFocus ->
        when(v.id) {
            R.id.input_celsius -> {
                if (hasFocus) {
                    input_celsius.addTextChangedListener(celsiusWatcher)
                } else {
                    input_celsius.removeTextChangedListener(celsiusWatcher)
                }
            }
            R.id.input_fahrenheit -> {
                if (hasFocus) {
                    input_fahrenheit.addTextChangedListener(fahrenheitWatcher)
                }else {
                    input_fahrenheit.removeTextChangedListener(fahrenheitWatcher)
                }
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_temperature_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        input_celsius.onFocusChangeListener = onFocus
        input_fahrenheit.onFocusChangeListener = onFocus
    }

}