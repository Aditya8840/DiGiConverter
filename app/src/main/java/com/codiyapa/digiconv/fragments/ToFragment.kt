package com.codiyapa.digiconv.fragments

import android.os.Bundle
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.text.method.TextKeyListener
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.codiyapa.digiconv.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_to.*
import org.w3c.dom.Text
import soup.neumorphism.ShapeType
import kotlin.check
import android.app.Activity
import android.view.inputmethod.InputMethodManager


class ToFragment : Fragment() {

    private lateinit var view1 : View
    private var from : Int = 0
    private var to : Int = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view1 =  inflater.inflate(R.layout.fragment_to, container, false)
        from = requireArguments().getInt("from")
        to = requireArguments().getInt("to")
        return view1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var from_str = ""
        var to_str = ""
        var num = ""
        when (from) {
            1 -> from_str = "Binary"
            2 -> from_str = "Octal"
            3 -> from_str = "Decimal"
            4 -> from_str = "HexaDecimal"
            else -> { // Note the block
                print("  ")
            }
        }
        when (to) {
            1 -> to_str = "Binary"
            2 -> to_str = "Octal"
            3 -> to_str = "Decimal"
            4 -> to_str = "HexaDecimal"
            else -> { // Note the block
                print("  ")
            }
        }

        neumorphTextView.text = "Enter "+from_str+" Number"
        textView4.text = to_str+" Number"
        when (from) {
            1 -> number.setKeyListener(DigitsKeyListener.getInstance("01."));
            2 -> number.setKeyListener(DigitsKeyListener.getInstance("01234567."));
            3 -> number.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
            4 -> {
                number.inputType = InputType.TYPE_CLASS_TEXT
            }
            else -> { // Note the block
                print("  ")
            }
        }
        var result = ""
        neumorphCardView5.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                neumorphCardView5.setShapeType(ShapeType.PRESSED)
                true
            } else if (event.action == MotionEvent.ACTION_UP) {
                neumorphCardView5.setShapeType(ShapeType.FLAT)
                hideKeyboard(requireActivity())
                if(number.text.isEmpty()){
                    number.error = "Enter Number"
                }else {
                    num = number.text.toString()
                    when (from) {
                        1 -> {
                            when (to) {
                                2 -> {
                                    result = binary_octal(num)
                                    result_text.text = result
                                }
                                3 -> {
                                    result = binary_decimal(num)
                                    result_text.text = result
                                }
                                4 ->{
                                    result = binary_hexa(num)
                                    result_text.text = result
                                }
                                else -> { // Note the block
                                    print("  ")
                                }
                            }
                        }
                    2 -> {
                        var res1 = ""
                        when (to) {
                            1 -> {
                                res1 = octal_binary(num)
                                result = res1
                                result_text.text = result
                            }
                            3 -> {
                                res1 = octal_binary(num)
                                result = binary_decimal(res1)
                                result_text.text = result
                            }
                            4 ->{
                                res1 = octal_binary(num)
                                result = binary_hexa(res1)
                                result_text.text = result
                            }
                            else -> { // Note the block
                                print("  ")
                            }
                        }
                    }
                    3 -> {
                        var res1 = ""
                        when (to) {
                            1 -> {
                                res1 = decimal_binary(num)
                                result = res1
                                result_text.text = result
                            }
                            2 -> {
                                res1 = decimal_binary(num)
                                result = binary_octal(res1)
                                result_text.text = result
                            }
                            4 ->{
                                res1 = decimal_binary(num)
                                result = binary_hexa(res1)
                                result_text.text = result
                            }
                            else -> { // Note the block
                                print("  ")
                            }
                        }
                    }
                    4 -> {
                        var res1 = ""
                        when (to) {
                            1 -> {
                                res1 = hex_binary(num)
                                result = res1
                                result_text.text = result
                            }
                            2 -> {
                                res1 = hex_binary(num)
                                result = binary_octal(res1)
                                result_text.text = result
                            }
                            3 ->{
                                res1 = hex_binary(num)
                                result = binary_decimal(res1)
                                result_text.text = result
                            }
                            else -> { // Note the block
                                print("  ")
                            }
                        }
                    }
                    }
                }
                true
            } else false
        })

        neumorphCardView8.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                neumorphCardView8.setShapeType(ShapeType.PRESSED)
                true
            } else if (event.action == MotionEvent.ACTION_UP) {
                neumorphCardView8.setShapeType(ShapeType.FLAT)
                requireFragmentManager().beginTransaction().add(R.id.container, HomeFragment()).commit()
                true
            } else false
        })

    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }



    private fun binary_decimal(binary : String): String {
        if(binary.contains(".")){
            var bina : Float = 0F
            var pow = 1F
            val lis = binary.split(".").toTypedArray()
            val bin = lis[0].reversed()
            val ary = lis[1]
            for(i in bin){
                bina += (i - '0')*pow
                pow *= 2
            }
            pow = 2F
            for(i in ary){
                bina += (i - '0')/pow
                pow *= 2
            }
            return bina.toString()
        }else{
            var bin = 0F
            var pow = 1F
            val bina = binary.reversed()
            for(i in bina){
                bin += (i - '0')*pow
                pow *= 2
            }
            return bin.toString()
        }
    }

    private fun binary_octal(binary : String): String {
        var cha = binary
        var oct = ""
        if(cha.contains(".")) {
            var char = cha.split(".").toTypedArray()
            var fir = char[0].reversed()
            var sec = char[1].chunked(3).toTypedArray()
            var chunked = fir.chunked(3).toTypedArray()
            for (i in chunked) {
                var pow = 1
                var oct_int = 0
                for (j in i) {
                    oct_int += (j - '0') * pow
                    pow *= 2
                }
                oct += oct_int.toString()
            }
            oct = oct.reversed()
            oct += "."
            for (i in sec) {
                var pow = 4
                var oct_int = 0
                for (j in i) {
                    oct_int += (j - '0') * pow
                    pow /= 2
                }
                oct += oct_int.toString()
            }
        }else{
            var fir = cha.reversed()
            var chunked = fir.chunked(3).toTypedArray()
            for(i in chunked){
                var pow = 1
                var oct_int = 0
                for(j in i){
                    oct_int += (j-'0')*pow
                    pow*=2
                }
                oct += oct_int.toString()
            }
            oct = oct.reversed()
        }
        return oct
    }

    private fun binary_hexa(binary : String): String {
        var cha = binary
        var oct = ""
        if(cha.contains(".")){
            var char = cha.split(".").toTypedArray()
            var fir = char[0].reversed()
            var sec = char[1].chunked(4).toTypedArray()
            var chunked = fir.chunked(4).toTypedArray()
            for(i in chunked){
                var pow = 1
                var oct_int = 0
                for(j in i){
                    oct_int += (j-'0')*pow
                    pow*=2
                }
                when(oct_int){
                    10 -> oct+="A"
                    11-> oct+="B"
                    12 -> oct+="C"
                    13 -> oct+="D"
                    14 -> oct+="E"
                    15 -> oct+= "F"
                    else -> {
                        oct += oct_int.toString()
                    }
                }
            }
            oct = oct.reversed()

            oct+="."
            for(i in sec){
                var pow = 8
                var oct_int = 0
                for(j in i){
                    oct_int += (j-'0')*pow
                    pow/=2
                }
                when(oct_int){
                    10 -> oct+="A"
                    11-> oct+="B"
                    12 -> oct+="C"
                    13 -> oct+="D"
                    14 -> oct+="E"
                    15 -> oct+= "F"
                    else -> {
                        oct += oct_int.toString()
                    }
                }
            }
        }else{
            var fir = cha.reversed()
            var chunked = fir.chunked(4).toTypedArray()
            for(i in chunked){
                var pow = 1
                var oct_int = 0
                for(j in i){
                    oct_int += (j-'0')*pow
                    pow*=2
                }
                when(oct_int){
                    10 -> oct+="A"
                    11-> oct+="B"
                    12 -> oct+="C"
                    13 -> oct+="D"
                    14 -> oct+="E"
                    15 -> oct+= "F"
                    else -> {
                        oct += oct_int.toString()
                    }
                }
            }
            oct = oct.reversed()
        }

        return oct
    }

    private fun hex_binary(binary : String): String {
        var hex = ""
        if (binary.contains(".")) {
            val lis = binary.split(".").toTypedArray()
            for (i in lis[0]) {
                when (i) {
                    '0' -> hex += "0000"
                    '1' -> hex += "0001"
                    '2' -> hex += "0010"
                    '3' -> hex += "0011"
                    '4' -> hex += "0100"
                    '5' -> hex += "0101"
                    '6' -> hex += "0110"
                    '7' -> hex += "0111"
                    '8' -> hex += "1000"
                    '9' -> hex += "1001"
                    'A' -> hex += "1010"
                    'B' -> hex += "1011"
                    'C' -> hex += "1100"
                    'D' -> hex += "1101"
                    'E' -> hex += "1110"
                    'F' -> hex += "1111"
                    'a' -> hex += "1010"
                    'b' -> hex += "1011"
                    'c' -> hex += "1100"
                    'd' -> hex += "1101"
                    'e' -> hex += "1110"
                    'f' -> hex += "1111"
                    else -> {
                        val i = 0
                    }
                }
            }
            hex += "."
            for (i in lis[1]) {
                when (i) {
                    '0' -> hex += "0000"
                    '1' -> hex += "0001"
                    '2' -> hex += "0010"
                    '3' -> hex += "0011"
                    '4' -> hex += "0100"
                    '5' -> hex += "0101"
                    '6' -> hex += "0110"
                    '7' -> hex += "0111"
                    '8' -> hex += "1000"
                    '9' -> hex += "1001"
                    'A' -> hex += "1010"
                    'B' -> hex += "1011"
                    'C' -> hex += "1100"
                    'D' -> hex += "1101"
                    'E' -> hex += "1110"
                    'F' -> hex += "1111"
                    'a' -> hex += "1010"
                    'b' -> hex += "1011"
                    'c' -> hex += "1100"
                    'd' -> hex += "1101"
                    'e' -> hex += "1110"
                    'f' -> hex += "1111"
                    else -> {
                        val i = 0
                    }
                }
            }
        }else{
            for (i in binary) {
                when (i) {
                    '0' -> hex += "0000"
                    '1' -> hex += "0001"
                    '2' -> hex += "0010"
                    '3' -> hex += "0011"
                    '4' -> hex += "0100"
                    '5' -> hex += "0101"
                    '6' -> hex += "0110"
                    '7' -> hex += "0111"
                    '8' -> hex += "1000"
                    '9' -> hex += "1001"
                    'A' -> hex += "1010"
                    'B' -> hex += "1011"
                    'C' -> hex += "1100"
                    'D' -> hex += "1101"
                    'E' -> hex += "1110"
                    'F' -> hex += "1111"
                    'a' -> hex += "1010"
                    'b' -> hex += "1011"
                    'c' -> hex += "1100"
                    'd' -> hex += "1101"
                    'e' -> hex += "1110"
                    'f' -> hex += "1111"
                    else -> {
                        val i = 0
                    }

                }
            }
        }
        return hex
    }

    private fun octal_binary(binary : String): String {
        var hex = ""
        if (binary.contains(".")) {
            val lis = binary.split(".").toTypedArray()
            for (i in lis[0]) {
                when (i) {
                    '0' -> hex += "000"
                    '1' -> hex += "001"
                    '2' -> hex += "010"
                    '3' -> hex += "011"
                    '4' -> hex += "100"
                    '5' -> hex += "101"
                    '6' -> hex += "110"
                    '7' -> hex += "111"
                    else -> {
                        val i = 0
                    }
                }
            }
            hex += "."
            for (i in lis[1]) {
                when (i) {
                    '0' -> hex += "000"
                    '1' -> hex += "001"
                    '2' -> hex += "010"
                    '3' -> hex += "011"
                    '4' -> hex += "100"
                    '5' -> hex += "101"
                    '6' -> hex += "110"
                    '7' -> hex += "111"
                    else -> {
                        val i = 0
                    }
                }
            }
        }else{
            for (i in binary) {
                when (i) {
                    '0' -> hex += "000"
                    '1' -> hex += "001"
                    '2' -> hex += "010"
                    '3' -> hex += "011"
                    '4' -> hex += "100"
                    '5' -> hex += "101"
                    '6' -> hex += "110"
                    '7' -> hex += "111"
                    else -> {
                        val i = 0
                    }

                }
            }
        }
        return hex
    }

    private fun decimal_binary(binary : String): String {
        var dec = ""
        if(binary.contains(".")){
            val lis = binary.split(".").toTypedArray()
            var inte = lis[0].toInt()
            while (inte != 0){
                dec += (inte % 2).toString()
                inte /= 2
            }
            dec = dec.reversed()
            dec += "."
            var inte2 = ("0."+lis[1]).toFloat()
            for (i in 1..5){
                var flo = inte2*2F
                var integ : Int = flo.toInt()
                dec += integ
                flo -= integ.toFloat()
                if(flo == 0F){
                    break
                }
                inte2 = flo
            }
            return dec

        }else{

            var inte = binary.toInt()
            print(inte)
            print("\n")
            while (inte != 0){
                dec += (inte % 2).toString()
                inte /= 2
            }
            dec = dec.reversed()
            return dec
        }
    }


}