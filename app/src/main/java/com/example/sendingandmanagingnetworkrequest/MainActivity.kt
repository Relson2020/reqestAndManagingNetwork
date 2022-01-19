package com.example.sendingandmanagingnetworkrequest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sendingandmanagingnetworkrequest.databinding.ActivityMainBinding
import java.io.IOException



class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

//        lifecycleScope.launch {
//           image()
//        }

        binding.networkCheckButton.setOnClickListener {
            val connectionResult = isOnline()
            if (connectionResult) {
                Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Not-Connected", Toast.LENGTH_SHORT).show()
            }
        }
      //  Glide.with(this).load(imageUrl).into(binding.imageView)
    }

    private fun isOnline(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val check = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val checkResult = check.waitFor()
            return (checkResult == 0)
        }catch (e : IOException){ }
        catch (e : InterruptedException){}
        return false
    }

//    private suspend fun image(){
//        val imageUrl = URL("https://i.imgur.com/tGbaZCY.jpg")
//        val connect = imageUrl.openConnection()
//        connect.connect()
//        val inputStream = connect.getInputStream()
//        val bitMap = BitmapFactory.decodeStream(inputStream)
//        inputStream.close()
//        binding.imageView.setImageBitmap(bitMap)
//    }
}