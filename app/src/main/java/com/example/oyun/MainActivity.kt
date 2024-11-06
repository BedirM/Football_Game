package com.example.oyun

import android.content.DialogInterface
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.oyun.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score = 0
    var resimliste = ArrayList<ImageView>()
    var runnable = Runnable {  }
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.logo.visibility = View.VISIBLE // Logoyu görünür yapmak


        // MediaPlayer oluşturma ve müziği başlatma
        var mediaPlayer = MediaPlayer.create(this, R.raw.sound)
        mediaPlayer.isLooping = true // Müzik döngüde çalsın
        mediaPlayer.start() // Müziği başlat




        Handler().postDelayed({
            binding.textView.visibility = View.GONE
        }, 4000)


        binding.imageView.tag = "degerli"
        binding.imageView1.tag = "normal"
        binding.imageView2.tag = "normal"
        binding.imageView3.tag = "normal"
        binding.imageView4.tag = "normal"
        binding.imageView5.tag = "degerli"
        binding.imageView6.tag = "degerli"
        binding.imageView7.tag = "normal"
        binding.imageView8.tag = "normal"



        resimliste.add(binding.imageView)
        resimliste.add(binding.imageView1)
        resimliste.add(binding.imageView2)
        resimliste.add(binding.imageView3)
        resimliste.add(binding.imageView4)
        resimliste.add(binding.imageView5)
        resimliste.add(binding.imageView6)
        resimliste.add(binding.imageView7)
        resimliste.add(binding.imageView8)

        gizle()

        object : CountDownTimer(15000,1000){
            override fun onTick(p0: Long) {
                binding.zaman.text = "Time: ${p0/1000}"
            }

            override fun onFinish() {
                binding.zaman.text = "Süre Bitti."
                handler.removeCallbacks(runnable)
                for (image in resimliste) {
                    image.visibility = View.INVISIBLE
                }
                var alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Yeniden Başlatmak İster Misin?")
                alert.setPositiveButton("Evet",DialogInterface.OnClickListener{dialogInterface , i ->
                    var main1 = intent
                    finish()
                    startActivity(main1)
                    mediaPlayer.stop()
                    mediaPlayer.start()

                })
                alert.setNegativeButton("Hayır",DialogInterface.OnClickListener{dialogInterface , i ->
                    Toast.makeText(this@MainActivity,"Oyun Bitti",Toast.LENGTH_SHORT).show()
                    mediaPlayer.stop()

                })

                alert.show()
            }
        }.start()

    }
    fun scoreart(view: View){
        when(view.tag){
            "degerli" -> score +=2
            "normal" -> score +=1
        }
        binding.score.text = "Score: $score"
    }
    fun gizle() {
        runnable = object : Runnable{
            override fun run() {
                for (image in resimliste) {
                    image.visibility = View.INVISIBLE
                }
                var random = java.util.Random()
                var randomindex = random.nextInt(9)
                resimliste[randomindex].visibility = View.VISIBLE

                handler.postDelayed(runnable , 500)
            }
        }
        handler.post(runnable)

    }


}


