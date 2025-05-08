package com.emirhanok20.chickenchronicles.di

import com.emirhanok20.chickenchronicles.data.repository.AppRepository
import com.emirhanok20.chickenchronicles.data.repository.AppRepositoryImpl
import com.emirhanok20.chickenchronicles.navigation.MainViewModel
import com.emirhanok20.chickenchronicles.navigation.game.GameViewModel
import com.emirhanok20.chickenchronicles.utility.ui.math_quiz.MathQuizViewModel
import com.emirhanok20.chickenchronicles.utility.ui.mini_game.MiniGameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AppRepository> { AppRepositoryImpl() }
    viewModel { GameViewModel(get()) }
    viewModel { MathQuizViewModel() }
    viewModel { MiniGameViewModel() }
    viewModel { MainViewModel() }
}
