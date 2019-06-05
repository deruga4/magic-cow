package com.derekl.magiccow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
//    private lateinit var textMessage: TextView
//    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                textMessage.setText(R.string.title_home)
//                println("Home nav")
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
//                textMessage.setText(R.string.title_add)
//                println("add new nav")
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        MobileAds.initialize(this, "ca-app-pub-7909349290148664~5312392155")

        var choices = arrayListOf("subway",
            "smoke's",
            "wendys",
            "jerk chicken",
            "mandarin",
            "bang me baby",
            "popeyes",
            "csgo")


        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

//        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")

        rollBtn.setOnClickListener {
            println(choices.toString())
            val rand = Random()
            val ind = rand.nextInt(choices.size)
            val choice = choices[ind]

            var text = StringBuffer()

            text.append(" ${"_".repeat(choice.length + 2)} \n")
                .append("/ $choice /\n")
                .append(" ${"-".repeat(choice.length + 2)} \n")
                .append("    \\   ^__^\n")
                .append("     \\  (oo)\\_______\n")
                .append("        (__)\\       )\\/\\\n")
                .append("            ||----w |\n")
                .append("            ||     ||\n")

            cowTxt.text = text.toString()
//            statusTxt.text = ""
        }

        addBtn.setOnClickListener {
            val newFood = addTxt.text
            if (newFood.length > 0){
                choices.add(newFood.toString())
//                statusTxt.text = "Successfully added $newFood"
                addTxt.setText("")
                Toast.makeText(getApplicationContext(),"Successfully added $newFood", Toast.LENGTH_SHORT).show()
            }
        }

//        textMessage = findViewById(R.id.cowTxt)
//        textMessage.setText("")
//        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
