package com.codiyapa.digiconv.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codiyapa.digiconv.R
import kotlinx.android.synthetic.main.fragment_home.*
import soup.neumorphism.ShapeType


class HomeFragment : Fragment() {

    private lateinit var view1 : View
    private  var neu1 : Int = 0
    private  var neu2 : Int = 0
    private  var neu3 : Int = 0
    private var neu4 : Int = 0
    private var count : Int = 0
    private var from : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view1 =  inflater.inflate(R.layout.fragment_home, container, false)
        return view1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        neumorphCardView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                neumorphCardView.setShapeType(ShapeType.PRESSED)
                true
            }else if(event.action == MotionEvent.ACTION_UP){
                neumorphCardView.setShapeType(ShapeType.FLAT)
                if(count == 0) {
                    if (neu1 == 0) {
                        neu1 = 1
                        from = 1
                        check.visibility = View.VISIBLE
                        count += 1
                        arrow.visibility = View.VISIBLE
                        ui_text2.visibility = View.VISIBLE
                    } else {
                        neu1 = 0
                        check.visibility = View.GONE
                        count-= 1
                        arrow.visibility = View.GONE
                        ui_text2.visibility = View.GONE
                    }
                }else{
                    if(neu1 == 1){
                        neu1 = 0
                        from = 0
                        check.visibility = View.GONE
                        count-= 1
                        arrow.visibility = View.GONE
                        ui_text2.visibility = View.GONE
                    }else{
                        check.visibility = View.VISIBLE
                        val bundle = Bundle()
                        bundle.putInt("from", from)
                        bundle.putInt("to", 1)
                        val fragment = ToFragment()
                        fragment.arguments = bundle
                        requireFragmentManager().beginTransaction().add(R.id.container, fragment).commit()}
                }
                Log.d("count neu1", count.toString()+" "+neu1.toString())
                true
            } else false
        })

        neumorphCardView2.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                neumorphCardView2.setShapeType(ShapeType.PRESSED)
                true
            }else if(event.action == MotionEvent.ACTION_UP){
                neumorphCardView2.setShapeType(ShapeType.FLAT)
                if(count == 0) {
                    if (neu2 == 0) {
                        neu2 = 1
                        from = 2
                        check2.visibility = View.VISIBLE
                        count += 1
                        arrow.visibility = View.VISIBLE
                        ui_text2.visibility = View.VISIBLE
                    } else {
                        neu2 = 0
                        check2.visibility = View.GONE
                        count-= 1
                        arrow.visibility = View.GONE
                        ui_text2.visibility = View.GONE
                    }
                }else{
                    if(neu2 == 1){
                        neu2 = 0
                        from = 0
                        check2.visibility = View.GONE
                        count-= 1
                        arrow.visibility = View.GONE
                        ui_text2.visibility = View.GONE
                    }else{
                        check2.visibility = View.VISIBLE
                        val bundle = Bundle()
                        bundle.putInt("from", from)
                        bundle.putInt("to", 2)
                        val fragment = ToFragment()
                        fragment.arguments = bundle
                        requireFragmentManager().beginTransaction().add(R.id.container, fragment).commit()}
                }
                true
            } else false
        })

        neumorphCardView3.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                neumorphCardView3.setShapeType(ShapeType.PRESSED)
                true
            }else if(event.action == MotionEvent.ACTION_UP){
                neumorphCardView3.setShapeType(ShapeType.FLAT)
                if(count == 0) {
                    if (neu3 == 0) {
                        neu3 = 1
                        from = 3
                        check3.visibility = View.VISIBLE
                        count += 1
                        arrow.visibility = View.VISIBLE
                        ui_text2.visibility = View.VISIBLE
                    } else {
                        neu3= 0
                        check3.visibility = View.GONE
                        count-= 1
                        arrow.visibility = View.GONE
                        ui_text2.visibility = View.GONE
                    }
                }else{
                    if(neu3 == 1){
                        neu3 = 0
                        check3.visibility = View.GONE
                        from = 0
                        count-= 1
                        arrow.visibility = View.GONE
                        ui_text2.visibility = View.GONE
                    }else{
                        check3.visibility = View.VISIBLE
                        val bundle = Bundle()
                        bundle.putInt("from", from)
                        bundle.putInt("to", 3)
                        val fragment = ToFragment()
                        fragment.arguments = bundle
                        requireFragmentManager().beginTransaction().add(R.id.container, fragment).commit()}
                }
                true
            } else false
        })
        neumorphCardView4.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                neumorphCardView4.setShapeType(ShapeType.PRESSED)
                true
            }else if(event.action == MotionEvent.ACTION_UP){
                neumorphCardView4.setShapeType(ShapeType.FLAT)
                if(count == 0) {
                    if (neu4 == 0) {
                        neu4 = 1
                        from = 4
                        check4.visibility = View.VISIBLE
                        count += 1
                        arrow.visibility = View.VISIBLE
                        ui_text2.visibility = View.VISIBLE
                    } else {
                        neu4 = 0
                        check4.visibility = View.GONE
                        count-= 1
                        arrow.visibility = View.GONE
                        ui_text2.visibility = View.GONE
                    }
                }else{
                    if(neu4 == 1){
                        neu4 = 0
                        from = 4
                        check4.visibility = View.GONE
                        count-= 1
                        arrow.visibility = View.GONE
                        ui_text2.visibility = View.GONE
                    }else{
                        check4.visibility = View.VISIBLE

                        val bundle = Bundle()
                        bundle.putInt("from", from)
                        bundle.putInt("to", 4)
                        val fragment = ToFragment()
                        fragment.arguments = bundle
                        requireFragmentManager().beginTransaction().add(R.id.container, fragment).commit()
                    }
                }
                true
            } else false
        })



    }

}