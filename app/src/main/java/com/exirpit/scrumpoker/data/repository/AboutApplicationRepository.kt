package com.exirpit.scrumpoker.data.repository

import android.content.Context
import android.net.Uri
import com.exirpit.scrumpoker.domain.model.version.Version
import com.exirpit.scrumpoker.domain.repository.IAboutApplicationRepository
import java.time.LocalDateTime

class AboutApplicationRepository(
    private val context: Context
) : IAboutApplicationRepository {

    override fun getVersion(): Version {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)

        return Version.getVersionFromPackageInformation(packageInfo)
    }

    override fun getLastUpdated(): LocalDateTime {
        TODO("Not yet implemented")
    }

    override fun getPrivacyPolicy(): Uri {
        TODO("Not yet implemented")
    }
}