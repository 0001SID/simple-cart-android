package com.example.cart

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var quantity = 1
    private var total = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun disTotal(res: Int){
        val totalPrice = findViewById<TextView>(R.id.totalPrice)
        total = res
        totalPrice.text = res.toString()
    }

    private fun disQuantity(res:Int){
        val quantityView = findViewById<TextView>(R.id.quantity)
        quantityView.text = res.toString()
    }

    private fun disChickenPrice(res:Int){
        val chickenPrice = findViewById<TextView>(R.id.chickenPrice)
        chickenPrice.text = res.toString()
    }


    fun increment(view:View){
        if(quantity == 5){
            Toast.makeText(applicationContext,"You are allowed to order only 5 Plate at a time",Toast.LENGTH_SHORT).show()
        }
        else{
            quantity += 1
            disQuantity(quantity)
            disTotal(total + 500)
            disChickenPrice(quantity*500)
        }


    }

    fun decrement(view: View){
        if(quantity == 1){
            Toast.makeText(applicationContext,"Quantity can not be less than 1",Toast.LENGTH_SHORT).show()
        }
        else{
            quantity -= 1
            disQuantity(quantity)
            disTotal(total - 500)
            disChickenPrice(quantity*500)
        }

    }

    fun cheesClick(view: View){
        val cheesTextView = findViewById<TextView>(R.id.cheesTextView)
        val cheesCheckBox = findViewById<CheckBox>(R.id.cheesCheckBox)


        if(cheesCheckBox.isChecked){
            cheesTextView.visibility = View.VISIBLE
            disTotal(total + 100)
        }
        else{
            cheesTextView.visibility = View.GONE
            disTotal(total - 100)
        }

    }

    fun butterClick(view: View){
        val butterTextView = findViewById<TextView>(R.id.butterTextView)
        val butterCheckBox = findViewById<CheckBox>(R.id.butterCheckBox)


        if(butterCheckBox.isChecked){
            butterTextView.visibility = View.VISIBLE
            disTotal(total + 150)
        }
        else{
            butterTextView.visibility = View.GONE
            disTotal(total - 150)
        }

    }

    fun chickenClick(view: View){
        val chickenTextView = findViewById<TextView>(R.id.chickenTextView)
        val chickenCheckBox = findViewById<CheckBox>(R.id.chickenCheckBox)


        if(chickenCheckBox.isChecked){
            chickenTextView.visibility = View.VISIBLE
            disTotal(total + 300)
        }
        else{
            chickenTextView.visibility = View.GONE
            disTotal(total - 300)
        }

    }

    fun submitOrder(view: View){
        val cheesCheckBox = findViewById<CheckBox>(R.id.cheesCheckBox)
        val butterCheckBox = findViewById<CheckBox>(R.id.butterCheckBox)
        val chickenCheckBox = findViewById<CheckBox>(R.id.chickenCheckBox)

        var msg = "Contents in Package:\n"
        msg += "\n  - Chicken Biriani\t$quantity"
        if(cheesCheckBox.isChecked){msg += "\n  - Extra chees\t1"}
        if(butterCheckBox.isChecked){msg += "\n  - Extra butter\t1"}
        if(chickenCheckBox.isChecked){msg += "\n  - Extra chicken\t1"}
        msg += "\n\n\nTOTAL PRICE: $total"


        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, "Order for Chicken Biriani")
            putExtra(Intent.EXTRA_TEXT,msg)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


}
