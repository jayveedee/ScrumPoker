package com.exirpit.scrumpoker.domain.repository

import android.net.Uri
import com.exirpit.scrumpoker.domain.model.version.Version
import java.time.LocalDateTime

interface IAboutApplicationRepository {
    fun getVersion(): Version
    fun getLastUpdated(): LocalDateTime
    fun getPrivacyPolicy(): Uri
}