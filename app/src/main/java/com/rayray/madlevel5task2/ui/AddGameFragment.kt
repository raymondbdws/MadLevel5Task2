package com.rayray.madlevel5task2.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rayray.madlevel5task2.R
import com.rayray.madlevel5task2.model.Game
import com.rayray.madlevel5task2.model.GameViewModel
import kotlinx.android.synthetic.main.fragment_game_add.*
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    var month = c.get(Calendar.MONTH)
    var day = c.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tiDay.setOnClickListener {
            val dpd = DatePickerDialog(
                this.requireContext(),
                { view: DatePicker, year: Int, month: Int, day: Int ->
                    //update UI
                    tiDay.setText("$day")
                    tiMonth.setText("$month")
                    tiYear.setText("$year")
                    Toast.makeText(context, "$year $month $day", Toast.LENGTH_SHORT).show()
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        saveFab.setOnClickListener {
            onAddGame()
            findNavController().popBackStack()
        }
        observeGames()

    }

    private fun observeGames() {
        //todo
    }

    private fun onAddGame() {
        val gameTitle = tiTitle.text.toString()
        val gamePlatform = tiPlatform.text.toString()
//        val day = tiDay.text.toString()
//        val month = tiMonth.text.toString()
//        val year = tiYear.text.toString()
//        val day = tiDay.text.toString()
//        val month = tiMonth.text.toString()
//        val year = tiYear.text.toString()

        val formatter = DateTimeFormatter.ofPattern("yyyy MM dd")
        val parsedDate = LocalDate.parse("$year $month $day", formatter)
        val result: ZonedDateTime = parsedDate.atStartOfDay(ZoneId.systemDefault())



        if (gameTitle.isNotBlank() && gamePlatform.isNotBlank()) {
            viewModel.insertGame(
                Game(
                    gameTitle, gamePlatform, Date.from(result.toInstant())
                )
            )
        } else {
            Toast.makeText(activity, R.string.not_valid_game, Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseToDate(date: String): Date {
        return java.sql.Date.valueOf(date)
    }
}