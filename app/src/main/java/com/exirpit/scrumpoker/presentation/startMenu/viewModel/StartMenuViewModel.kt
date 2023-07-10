package com.exirpit.scrumpoker.presentation.startMenu.viewModel

import android.content.Context
import com.exirpit.scrumpoker.presentation.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StartMenuViewModel(
    context: Context
) : BaseViewModel() {

    private val _versionCode = MutableStateFlow(0L)
    val versionCode = _versionCode.asStateFlow()
    private val _versionName = MutableStateFlow("")
    val versionName = _versionName.asStateFlow()

    init {
        fetchVersionInformation(context)
    }

    private fun fetchVersionInformation(context: Context) {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)

        _versionCode.value = packageInfo.longVersionCode
        _versionName.value = packageInfo.versionName
    }
}