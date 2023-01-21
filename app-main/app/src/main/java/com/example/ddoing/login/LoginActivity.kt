package com.example.ddoing.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ddoing.MainActivity
import com.example.ddoing.R
import com.example.ddoing.databinding.ActivityLoginBinding
import com.example.ddoing.planAdd.PlanAdd
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val TAG = "GoogleLoginActivity"
    private val RC_SIGN_IN = 62870  // Can be any integer unique to the Activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //구글 로그인
        binding.loginWithGoogle.setOnClickListener {
            // val nextIntent = Intent(this, MainActivity::class.java)
            // startActivity(nextIntent)
            signIn()
        }

        //회원가입
        binding.signUp.setOnClickListener {
            val nextIntent = Intent(this, SignUp::class.java)
            startActivity(nextIntent)
        }

        //계획추가
        binding.login.setOnClickListener {
            val nextIntent = Intent(this, PlanAdd::class.java)
            startActivity(nextIntent)
        }
    }

    private fun signIn() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception

            if (task.isSuccessful) {

                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)

                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e)
                    Toast.makeText(this, "Google Sign In failed :(", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.w(TAG, exception.toString())
            }

        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val intentToMainActivity = Intent(this, MainActivity::class.java)
                    startActivity(intentToMainActivity)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Sign In Failed :(", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Welcome Back!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}