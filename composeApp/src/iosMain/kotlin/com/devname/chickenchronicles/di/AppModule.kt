package com.devname.chickenchronicles.di

import com.devname.chickenchronicles.game.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { GameViewModel() }
}
