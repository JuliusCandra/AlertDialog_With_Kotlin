package com.dev_candra.alertidialog_with_kotlin

import android.content.DialogInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.teks2
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var text1 : TextView
    private lateinit var text2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text1 = findViewById(R.id.teks1)
        text2 = findViewById(R.id.teks2)
        Aksi()

    }

    private fun Aksi() {
        // membuat alert dialog biasa
        btn1.apply {
            setOnClickListener {
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                alertDialog.setTitle("Dialog saya")
                alertDialog.setMessage("membuat dialog pertama".toUpperCase())
                alertDialog.setIcon(R.drawable.ic_launcher_background)
                alertDialog.setPositiveButton("Yes") { dialog: DialogInterface?, which: Int ->
                    Toast.makeText(this@MainActivity, "Yes", Toast.LENGTH_SHORT).show()
                }
                alertDialog.setNegativeButton("No") { dialog: DialogInterface?, which: Int ->
                    Toast.makeText(this@MainActivity, "No", Toast.LENGTH_SHORT).show()
                    dialog?.dismiss()
                }
                alertDialog.show()
            }
        }
        // membuat alertdialog dengan single choice
            btn2.apply {
                setOnClickListener {
                    // membuat array pada item
                    val item1 = arrayOf("item 1", "item 2", "item 3", "item 4")
                    val builderBtn2 = AlertDialog.Builder(this@MainActivity)
                    builderBtn2.setTitle("membuat dialog kedua".toUpperCase())
                    builderBtn2.setSingleChoiceItems(item1,-1){
                        dialog: DialogInterface?, i: Int ->
                        text1.text = item1[i]
                        dialog?.dismiss()
                    }
                    builderBtn2.setNeutralButton("Cancel"){
                        dialog: DialogInterface?, which: Int ->
                        // just dismiss the alert dialog
                        dialog?.cancel()
                    }
                    //create and show dialog
                    val mDialog = builderBtn2.create()
                    mDialog.show()
                }
            }
        btn3.apply {
            setOnClickListener {
                val dialog2 = AlertDialog.Builder(this@MainActivity)
                val colorArray = arrayOf("Orange","Blue","Red","Green","Purple")
                val checkedColorArray = booleanArrayOf(true, /* untuk yang orange */ false /* blue */
                ,false /* red */ , true /* green */,false /* purple */)
                val colorList = Arrays.asList(*colorArray)
                dialog2.setTitle("Select Color".toUpperCase())
                // make alert dialog
                dialog2.setMultiChoiceItems(colorArray,checkedColorArray){
                    dialog, which, isChecked ->
                    // update the current focussed item's checked status
                    checkedColorArray[which] = isChecked
                    // get the current focussed item
                    val getcurrentItem = colorList[which]
                    // notify the current section
                    Toast.makeText(applicationContext,getcurrentItem+ " " + isChecked,Toast.LENGTH_SHORT).show()
                }
                // set positive button
                dialog2.setPositiveButton("OK"){
                    dialog: DialogInterface?, which: Int ->
                    // do something when click positive button
                    text2.text = "Your preffered colors =... \n"
                    for (i in checkedColorArray.indices){
                        val checked = checkedColorArray[i]
                        if (checked){
                            text2.text = text2.text.toString() + colorList[i] + "\n"
                        }
                    }
                }
                // set netral button cancel
                dialog2.setNeutralButton("Cancel"){
                    dialog: DialogInterface?, which: Int ->  dialog?.dismiss()
                }
                val dialog = dialog2.create()
                // show dialog
                dialog.show()
            }
        }
        initalBar()
    }

    private fun initalBar(){
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.title = "Candra Julius Sinaga"
            actionBar.subtitle = "Contoh Alert Dilaog"
        }
    }

}
