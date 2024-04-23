package ru.myapplication.Game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.myapplication.Game.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bin: ActivityMainBinding
    private val rounds = mutableListOf<Round>()
    private var currentRound = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bin.root)
        bin.quest.text = "Вопрос"
        fillRounds()
        updateUI()
        bin.button1.setOnClickListener{raund(1)}
        bin.button2.setOnClickListener{ raund(2) }
        bin.button3.setOnClickListener{ raund(3) }
        bin.button4.setOnClickListener{ raund(4) }
    }
    private fun fillRounds(){
        rounds.run{
            add(Round("Кто такой Илон Маск?","Гений","Плейбой", "Филонтроп", "Миллиардер",3,100))
            add(Round("Какого цвета солнце","черное","белое", "желтое", "синее",1,1000))
            add(Round("Сколько спутников у Марса?","0","1", "2", "4",3,10000))
            add(Round("Как называется столица России?","Москва","Хабаровск", "Воронеж", "Борисоглебск",2,100000))
            add(Round("Какой язык программирования самый популярный в 2024?","Питон","Шарп", "1С", "Котлин",4,1000000))
        }
    }
    private fun updateUI(){
        bin.quest.text = rounds[currentRound].question
        bin.tvValue.text = rounds[currentRound].valu.toString()
        bin.button1.text = rounds[currentRound].vopros1
        bin.button2.text = rounds[currentRound].vopros2
        bin.button3.text = rounds[currentRound].vopros3
        bin.button4.text = rounds[currentRound].vopros4
    }
    private fun proverka(givenId: Int) = (givenId == rounds[currentRound].id)
    private fun goNextRound(): Boolean{
        if(currentRound>=rounds.size - 1) return false
        currentRound++
        updateUI()
        return true
    }
    private fun raund(givenId: Int){
        if(proverka(givenId)){
            if(!goNextRound()){
                Toast.makeText(this, "Вы победили! Ура!", Toast.LENGTH_SHORT).show()
                finish()
            }
        } else{
            Toast.makeText(this, "К сожалению, вы проиграли", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    class Round (val question: String, val vopros1: String, val vopros2: String, val vopros3: String, val vopros4: String, val id: Int, val valu: Int)
}
