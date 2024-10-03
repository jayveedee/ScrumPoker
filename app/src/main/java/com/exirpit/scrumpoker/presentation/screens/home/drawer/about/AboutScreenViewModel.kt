package com.exirpit.scrumpoker.presentation.screens.home.drawer.about

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.exirpit.scrumpoker.R
import com.exirpit.scrumpoker.domain.model.version.Version
import com.exirpit.scrumpoker.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AboutScreenViewModel @Inject constructor(
    private val app: Application
) : BaseViewModel() {

    //--------------------------------------------------------------------------------------------//
    // Flows
    //--------------------------------------------------------------------------------------------//
    private val _versionTextStateFlow = MutableStateFlow("")
    val versionTextStateFlow = _versionTextStateFlow.asStateFlow()

    private val _lastUpdateTextStateFlow = MutableStateFlow("")
    val lastUpdateTextStateFlow = _lastUpdateTextStateFlow.asStateFlow()

    //--------------------------------------------------------------------------------------------//
    // Attributes
    //--------------------------------------------------------------------------------------------//
    private val openSourceLicenseURLStateFlow = "https://github.com/jayveedee/ScrumPoker/blob/master/LICENSE"
    private val privacyPolicyURLStateFlow = "https://github.com/jayveedee/ScrumPoker/blob/master/PRIVACY_POLICY.md"
    private val githubURLStateFlow = "https://github.com/jayveedee/ScrumPoker"
    private val websiteURLStateFlow = "https://exirpit.com/"

    //--------------------------------------------------------------------------------------------//
    // Initializer
    //--------------------------------------------------------------------------------------------//
    init {
        viewModelScope.launch {
            _versionTextStateFlow.update {
                getVersionText()
            }

            _lastUpdateTextStateFlow.update {
                getLastUpdateText()
            }
        }
    }

    //--------------------------------------------------------------------------------------------//
    // Public methods
    //--------------------------------------------------------------------------------------------//
    fun onOpenSourceLicenseTextClicked(context: Context) {
        openWebView(context, openSourceLicenseURLStateFlow)
    }

    fun onPrivacyPolicyTextClicked(context: Context) {
        openWebView(context, privacyPolicyURLStateFlow)
    }

    fun onGithubIconClicked(context: Context) {
        openWebView(context, githubURLStateFlow)
    }

    fun onWebsiteIconClicked(context: Context) {
        openWebView(context, websiteURLStateFlow)
    }

    //--------------------------------------------------------------------------------------------//
    // Private methods
    //--------------------------------------------------------------------------------------------//
    private fun openWebView(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }

        context.startActivity(intent)
    }

    private fun getVersionText(): String {
        val packageInfo = app.packageManager.getPackageInfo(app.packageName, 0)
        val version = Version.getVersionFromPackageInformation(packageInfo)

        val appName = app.getString(R.string.app_name)
        return "$appName.${version.major}.${version.minor}.${version.build}"
    }

    private fun getLastUpdateText(): String {
        val packageInfo = app.packageManager.getPackageInfo(app.packageName, 0)
        val lastUpdateTimeMilli = packageInfo.lastUpdateTime

        val lastUpdateDate = Instant.ofEpochMilli(lastUpdateTimeMilli)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()

        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

        return lastUpdateDate.format(formatter)
    }
}