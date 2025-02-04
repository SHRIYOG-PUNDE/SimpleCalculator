package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    private var tvInput : TextView? = null
    var lastNumeric = true
    var lastDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        tvInput = findViewById(R.id.tvInput)

    }
    fun onDigit(view : View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }
    fun onClear(view: View){
        tvInput?.text = ""
        lastNumeric = false
        lastDot = false
    }
    fun onDecimalPointClick(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as  Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (prefix.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                }
                else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() + two.toDouble()).toString()
                }
                else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                }
                else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() * two.toDouble()).toString()
                }

            }catch (e: ArithmeticException ){
                e.printStackTrace()
            }
        }
    }
    private fun isOperatorAdded(value : String) : Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("*") ||
                    value.contains("/") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }


}


