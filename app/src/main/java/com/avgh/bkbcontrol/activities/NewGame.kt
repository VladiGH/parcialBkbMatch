package com.avgh.bkbcontrol.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.avgh.bkbcontrol.AppConstants
import com.avgh.bkbcontrol.R
import kotlinx.android.synthetic.main.new_game.*

class NewGame: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_game)

        bt_guardar_nombres.setOnClickListener {
            if(et_equipo_der_name.text.isNotEmpty() && et_equipo_izq_name.text.isNotEmpty()){
                val intent = Intent(this@NewGame, GamePlay::class.java)
                intent.putExtra(AppConstants.KEY_TEAM1_NEWGAME_TO_THEGAME, et_equipo_izq_name.text.toString())
                intent.putExtra(AppConstants.KEY_TEAM2_NEWGAME_TO_THEGAME, et_equipo_der_name.text.toString())
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Rellena los campos requeridos", Toast.LENGTH_LONG).show()
            }
        }
    }



}