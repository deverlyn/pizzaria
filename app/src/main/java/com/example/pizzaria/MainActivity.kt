package com.example.pizzaria

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzaria.api.ApiInterface
import com.example.pizzaria.api.RetrofitInstance
import com.example.pizzaria.ui.pizzaria.EscolhaActivity
import com.example.pizzaria.utils.SignInBody
import kotlinx.android.synthetic.main.login.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        entrar.setOnClickListener {

            val user = usuario.text.toString().trim()
            val password = senha.text.toString().trim()

            if (user.isEmpty()){

                Toast.makeText(
                    applicationContext,
                    "Digite um usuário válido",
                    Toast.LENGTH_SHORT).show()

                usuario.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){

                Toast.makeText(applicationContext,
                    "Digite uma senha válida",
                    Toast.LENGTH_SHORT).show()

                senha.requestFocus()
                return@setOnClickListener
            }

            signIn(user, password)

        }

    }

    private fun signIn(usuario: String, senha: String){

        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val loginInfo = SignInBody(usuario, senha)

        retrofitInstance.login(loginInfo).enqueue(object : Callback<ResponseBody>{
            override fun onFailure(p0: Call<ResponseBody>, p1: Throwable) {
                Toast.makeText(
                    applicationContext,
                    p1.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(p0: Call<ResponseBody>, p1: Response<ResponseBody>) {
                if (p1.code() == 200){
                    Toast.makeText(applicationContext, "Bem-Vindo!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, EscolhaActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext, p1.code(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}