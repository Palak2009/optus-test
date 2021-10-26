package com.optus.codetest.utils

import android.content.Context
import com.optus.codetest.database.AppDatabase
import com.optus.codetest.network.ApiRepository
import com.optus.codetest.viewmodels.GeneralViewModelFactory

object Injector {

    fun provideMyViewModelFactory(context: Context): GeneralViewModelFactory {
        return GeneralViewModelFactory(provideRepository(context))
    }

    private fun provideRepository(context: Context): ApiRepository {
        return ApiRepository.getInstance(provideAppDatabase(context))
    }

    private fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

}