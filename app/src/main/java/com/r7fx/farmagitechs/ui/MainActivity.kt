package com.r7fx.farmagitechs.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.r7fx.farmagitechs.R
import com.r7fx.farmagitechs.domain.base.Result
import com.r7fx.farmagitechs.domain.repository.MainRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainRepository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        lifecycleScope.launch {
//            val result = mainRepository.login("magang", "android")
//            when(result.status) {
//                Result.Status.SUCCESS -> Log.d("SDKSDK", "SUCCESS: ${result.data}")
//                Result.Status.ERROR -> Log.d("SDKSDK", "ERROR: ${result.message}")
//            }
//
//            val result2 = mainRepository.getPatientList()
//            when(result2.status) {
//                Result.Status.SUCCESS -> Log.d("SDKSDK", "SUCCESS: ${result2.data}")
//                Result.Status.ERROR -> Log.d("SDKSDK", "ERROR: ${result2.message}")
//            }
//        }
    }
}