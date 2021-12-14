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

//        val Tv : TextView = findViewById(R.id.Tv)

//        firebaseAnalytics = Firebase.analytics

        // Access a Cloud Firestore instance from your Activity

        val db = Firebase.firestore
        val storage = Firebase.storage

        var storageRef = storage.reference
        var imagesRef: StorageReference? = storageRef.child("images")
        var spaceRef = storageRef.child("images/space.jpg")

        storage_iv = findViewById<ImageView>(R.id.storage_iv)
        storage_btn = findViewById<Button>(R.id.storage_btn)
        setupPermissions()

        //EditTextのクリックイベントを設定
        storage_btn.setOnClickListener {
            openGalleryForImage()
        }

        // Create a new user with a first and last name
//        val user = hashMapOf(
//            "first" to "Ada",
//            "last" to "Lovelace",
//            "born" to 1815
//        )
//
//        // Add a new document with a generated ID
//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }
//        val user = hashMapOf(
//            "first" to "Alan",
//            "middle" to "Mathison",
//            "last" to "Turing",
//            "born" to 1912
//        )
//
//          // Add a new document with a generated ID
//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }

//        db.collection("users")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                    Tv.text = document.data.toString()
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
//            }

//        val cities = db.collection("cities")
//
//        val data1 = hashMapOf(
//            "name" to "San Francisco",
//            "state" to "CA",
//            "country" to "USA",
//            "capital" to false,
//            "population" to 860000,
//            "regions" to listOf("west_coast", "norcal")
//        )
//        cities.document("SF").set(data1)
//
//        val data2 = hashMapOf(
//            "name" to "Los Angeles",
//            "state" to "CA",
//            "country" to "USA",
//            "capital" to false,
//            "population" to 3900000,
//            "regions" to listOf("west_coast", "socal")
//        )
//        cities.document("LA").set(data2)
//
//        val data3 = hashMapOf(
//            "name" to "Washington D.C.",
//            "state" to null,
//            "country" to "USA",
//            "capital" to true,
//            "population" to 680000,
//            "regions" to listOf("east_coast")
//        )
//        cities.document("DC").set(data3)
//
//        val data4 = hashMapOf(
//            "name" to "Tokyo",
//            "state" to null,
//            "country" to "Japan",
//            "capital" to true,
//            "population" to 9000000,
//            "regions" to listOf("kanto", "honshu")
//        )
//        cities.document("TOK").set(data4)
//
//        val data5 = hashMapOf(
//            "name" to "Beijing",
//            "state" to null,
//            "country" to "China",
//            "capital" to true,
//            "population" to 21500000,
//            "regions" to listOf("jingjinji", "hebei")
//        )
//        cities.document("BJ").set(data5)
//
//        val data6 = hashMapOf(
//            "name" to "Chiba",
//            "state" to null,
//            "country" to "Tokyo",
//            "capital" to false,
//            "population" to 2150000,
//            "regions" to listOf("kanto", "honshu")
//        )
//        cities.document("CB").set(data6)

//        val docRef = db.collection("cities").document("SF")
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
//                    Tv.text = "${document.get("name")}"
//                } else {
//                    Log.d(TAG, "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "get failed with ", exception)
//            }

//        // Create a reference to the cities collection
//        val citiesRef = db.collection("cities")
//
//        // Create a query against the collection.
//        val query = citiesRef.whereEqualTo("state", "CA")
//        Tv.text = query.toString()
//
//        val capitalCities = db.collection("cities").whereEqualTo("capital", true)
    }

    private fun openGalleryForImage() {
        //ギャラリーに画面を遷移するためのIntent
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_TAKE)
    }


    // onActivityResultにイメージ設定
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){
            2 -> {
                if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_GALLERY_TAKE){
                    //選択された写真にImageViewを変更
                    storage_iv.setImageURI(data?.data) // handle chosen image
                }
            }
        }
    }

    //パーミッションのチェックを設定するためのメソッド
    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    //パーミッションをリクエストするためのメソッド
    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            RECORD_REQUEST_CODE)
    }

    //パーミッションの許可の結果による実行されるメソッド
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        applicationContext,
                        "デバイス内の写真やメディアへのアクセスが許可されませんでした。",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "デバイス内の写真やメディアへのアクセスが許可されました。",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }
}