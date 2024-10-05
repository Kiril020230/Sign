package com.rajkishorbgp.sign_up_sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rajkishorbgp.sign_up_sign_in.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("USER")
        if (userName != null) {
            readData(userName)
        }

        binding.logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Log out!",Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    private fun readData(userName: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userName).get().addOnSuccessListener {
            if (it.exists()){

                Toast.makeText(this,"Successfully Read",Toast.LENGTH_SHORT).show()


                binding.userEmai.text = intent.getStringExtra("EMAIL").toString()

            }else{
                Toast.makeText(this,"User Doesn't Exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }
}