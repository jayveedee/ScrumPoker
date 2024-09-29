package com.exirpit.scrumpoker.presentation

import androidx.lifecycle.ViewModel
import com.exirpit.scrumpoker.data.preferences.SPPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    preferences: SPPreferences
) : ViewModel()