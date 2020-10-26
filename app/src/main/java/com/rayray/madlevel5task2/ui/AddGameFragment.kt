package com.rayray.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.rayray.madlevel5task2.R
import com.rayray.madlevel5task2.model.Game
import com.rayray.madlevel5task2.model.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game_add.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeGames()
    }

    private fun observeGames() {
       //todo
    }

    private fun onAddGame() {
        val gameTitle = tiTitle.text.toString()
        val gamePlatform = tiPlatform.text.toString()
        val day = tiDay.text.toString().toInt()
        val month = tiMonth.text.toString().toInt()
        val year = tiYear.text.toString().toInt()

        val localDate = LocalDate.of(year, month, day)

        if (gameTitle.isNotBlank() && gamePlatform.isNotBlank()
            && day.toString().isNotBlank() && month.toString().isNotBlank() && year.toString()
                .isNotBlank()
        ) {
            viewModel.insertGame(
                Game(
                    0, gameTitle, gamePlatform, Date.from(
                        localDate.atStartOfDay(
                            ZoneId.systemDefault()
                        ).toInstant()
                    )
                )
            )
        } else {
            Toast.makeText(activity, R.string.not_valid_game, Toast.LENGTH_SHORT).show()
        }
    }
}