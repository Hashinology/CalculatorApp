package com.hashinology.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hashinology.calculatorapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var number: String? = null

    var firstNumber: Double = 0.0
    var lastNumber: Double = 0.0

    var status: String? = null
    var operators = false

    val myFormatter: DecimalFormat = DecimalFormat("######.######")

    lateinit var history: String
    lateinit var currentResult: String

    var dot = true
    var btnACControl = true
    var btnEqualsControl = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.btn0.setOnClickListener {
            numberClick("0")
        }
        binding.btn1.setOnClickListener {
            numberClick("1")
        }
        binding.btn2.setOnClickListener {
            numberClick("2")
        }
        binding.btn3.setOnClickListener {
            numberClick("3")
        }
        binding.btn4.setOnClickListener {
            numberClick("4")
        }
        binding.btn5.setOnClickListener {
            numberClick("5")
        }
        binding.btn6.setOnClickListener {
            numberClick("6")
        }
        binding.btn7.setOnClickListener {
            numberClick("7")
        }
        binding.btn8.setOnClickListener {
            numberClick("8")
        }
        binding.btn9.setOnClickListener {
            numberClick("9")
        }
        binding.btnAC.setOnClickListener {
            number = null
            status = null
            binding.textViewResult.setText("0")
            binding.textViewHistopry.setText("")
            firstNumber = 0.0
            lastNumber = 0.0
            dot = true
            btnACControl = true
        }
        binding.btnDel.setOnClickListener {
            if (btnACControl){
                binding.textViewResult.setText("0")
            }else{
                number = number!!.substring(0, number!!.length -1)
                if (number!!.length == 0){
                    btnDel.setClickable(false)
                }else if (number!!.contains(".")){
                    dot = false
                }else{
                    dot = true
                }
                binding.textViewResult.setText(number)
            }
        }
        binding.btnDot.setOnClickListener(View.OnClickListener {
            if (dot){
                if (number == null){
                    number = "0."
                }else{
                    number = number + "."
                }
            }
            binding.textViewResult.setText(number)

            dot = false
        })
        binding.btnEquals.setOnClickListener {
            if (operators){
                if (status == "Sum"){
                    plus()
                }else if (status == "Substraction"){
                    minus()
                }else if (status == "Multiplication"){
                    multiply()
                }else if (status == "Division"){
                    divide()
                }else{
                    firstNumber = binding.textViewResult.toString().toDouble()
                }
                operators = false
                btnEqualsControl = true
            }
        }
        binding.btnPlus.setOnClickListener {
            history = binding.textViewHistopry.text.toString()
            currentResult = binding.textViewResult.text.toString()

            binding.textViewHistopry.text = history + currentResult +"+"

            if (operators){
                if (status == "Multiplication"){
                    multiply()
                }else if (status == "Division"){
                    divide()
                }else if (status == "Subtraction"){
                    minus()
                }else{
                    plus()
                }
            }
            status = "Sum"
            operators = false
            number = null
        }
        binding.btnMinus.setOnClickListener {
            history = binding.textViewHistopry.text.toString()
            currentResult = binding.textViewResult.text.toString()

            binding.textViewHistopry.text = history + currentResult +"-"

            if (operators){
                if (status == "Multiplication"){
                    multiply()
                }else if (status == "Division"){
                    divide()
                }else if (status == "Addition"){
                    plus()
                }else{
                    minus()
                }
            }
            status = "Subtraction"
            operators = false
            number = null
        }
        binding.btnMultiply.setOnClickListener {
            history = binding.textViewHistopry.text.toString()
            currentResult = binding.textViewResult.text.toString()

            binding.textViewHistopry.text = history + currentResult +"*"

            if (operators){
                if (status == "Subtraction"){
                    minus()
                }else if (status == "Division"){
                    divide()
                }else if (status == "Adding"){
                    plus()
                }else{
                    multiply()
                }
            }
            status = "Multiplication"
            operators = false
            number = null
        }
        binding.btnDivide.setOnClickListener {
            history = binding.textViewHistopry.text.toString()
            currentResult = binding.textViewResult.text.toString()

            binding.textViewHistopry.text = history + currentResult +"/"

            if (operators){
                if (status == "Subtraction"){
                    minus()
                }else if (status == "Multiplication"){
                    multiply()
                }else if (status == "Adding"){
                    plus()
                }else{
                    divide()
                }
            }
            status = "Division"
            operators = false
            number = null
        }
    }

    public fun numberClick(view: String) {
        if (number == null){
            number = view
        }else if(btnEqualsControl){
            firstNumber = 0.0
            lastNumber = 0.0
            number = view
        }
        else{
            number = number + view
        }
        binding.textViewResult.setText(number)

        operators = true

        btnACControl = false
        binding.btnDel.setClickable(true)
        btnEqualsControl = false
    }

    fun plus(){
        lastNumber = binding.textViewResult.text.toString().toDouble()
        firstNumber = firstNumber + lastNumber
        binding.textViewResult.setText(myFormatter.format(firstNumber))
        dot = true
    }

    fun minus(){
        if (firstNumber == 0.0){
            firstNumber = binding.textViewResult.text.toString().toDouble()
        }else{
            lastNumber = binding.textViewResult.text.toString().toDouble()
            firstNumber = firstNumber - lastNumber
        }
        binding.textViewResult.setText(myFormatter.format(firstNumber))
        dot = true
    }
    fun multiply(){
        if (firstNumber == 0.0){
            firstNumber = 1.0
            lastNumber = binding.textViewResult.text.toString().toDouble()
            firstNumber = firstNumber * lastNumber
        }else{
            lastNumber = binding.textViewResult.text.toString().toDouble()
            firstNumber = firstNumber * lastNumber
        }
        binding.textViewResult.setText(myFormatter.format(firstNumber))
        dot = true
    }
    fun divide(){
        if (firstNumber == 0.0){
            lastNumber = binding.textViewResult.text.toString().toDouble()
            firstNumber = lastNumber / 1
        }else{
            lastNumber = binding.textViewResult.text.toString().toDouble()
            firstNumber = firstNumber / lastNumber
        }
        binding.textViewResult.setText(myFormatter.format(firstNumber))
        dot = true
    }
}