package com.example.dbtest

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class MainActivity : AppCompatActivity() {
    private val REQUEST_GALLERY_TAKE = 2
    private val RECORD_REQUEST_CODE = 1000

    private lateinit var storage_iv: ImageView
    private lateinit var storage_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Tv : TextView = findViewById(R.id.Tv)

        // Access a Cloud Firestore instance from your Activity

        val db = Firebase.firestore
        val storage = Firebase.storage

//        var storageRef = storage.reference
//        var imagesRef: StorageReference? = storageRef.child("images")
//        var spaceRef = storageRef.child("images/space.jpg")

//        storage_iv = findViewById<ImageView>(R.id.storage_iv)
//        storage_btn = findViewById<Button>(R.id.storage_btn)

//        val test : TextView = findViewById(R.id.test)

        Log.d("Test","1")
        val storageRef = storage.reference
        val dir = storageRef.child("neet.png")
        Log.d("Test","2")
        val byteLength=1024*1024L
        dir.getBytes(byteLength).addOnSuccessListener{
                bytes ->
            Log.d("Test","3")
            val data = String(bytes, Charsets.UTF_8)
            val array=data.split("/n")
            var res=""
            val i=1;
            Log.d("Test","4")
            for(item in array){
                res=i.toString().plus(item).plus("\n")
            }
            Tv.text=res
        }.addOnFailureListener {  }



//        setupPermissions()
//
//        //EditTextのクリックイベントを設定
//        storage_btn.setOnClickListener {
//            openGalleryForImage()
//        }
    }

//    private fun openGalleryForImage() {
//        //ギャラリーに画面を遷移するためのIntent
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, REQUEST_GALLERY_TAKE)
//    }
//
//
//    // onActivityResultにイメージ設定
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        when (requestCode){
//            2 -> {
//                if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_GALLERY_TAKE){
//                    //選択された写真にImageViewを変更
//                    storage_iv.setImageURI(data?.data) // handle chosen image
//                }
//            }
//        }
//    }
//
//    //パーミッションのチェックを設定するためのメソッド
//    private fun setupPermissions() {
//        val permission = ContextCompat.checkSelfPermission(this,
//            Manifest.permission.READ_EXTERNAL_STORAGE)
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            makeRequest()
//        }
//    }
//
//    //パーミッションをリクエストするためのメソッド
//    private fun makeRequest() {
//        ActivityCompat.requestPermissions(this,
//            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//            RECORD_REQUEST_CODE)
//    }
//
//    //パーミッションの許可の結果による実行されるメソッド
//    override fun onRequestPermissionsResult(requestCode: Int,
//                                            permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            RECORD_REQUEST_CODE -> {
//                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(
//                        applicationContext,
//                        "デバイス内の写真やメディアへのアクセスが許可されませんでした。",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                } else {
//                    Toast.makeText(
//                        applicationContext,
//                        "デバイス内の写真やメディアへのアクセスが許可されました。",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                return
//            }
//        }
//    }
}