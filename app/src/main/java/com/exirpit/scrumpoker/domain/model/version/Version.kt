package com.exirpit.scrumpoker.domain.model.version

import android.content.pm.PackageInfo
import com.exirpit.scrumpoker.data.common.exceptions.BaseException

data class Version(
    val major: Long = 0,
    val minor: Long = 0,
    val build: Long = 0,
    val revision: Long = 0
) : Comparable<Version> {

    override fun compareTo(other: Version): Int {
        // Compare major version
        if (major > other.major) return 1
        else if (major < other.major) return -1

        // Compare minor version
        if (minor > other.minor) return 1
        else if (minor < other.minor) return -1

        // Compare build version
        if (build > other.build) return 1
        else if (build < other.build) return -1

        // Compare revision version
        if (revision > other.revision) return 1
        else if (revision < other.revision) return -1

        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Version

        if (major != other.major) return false
        if (minor != other.minor) return false
        if (build != other.build) return false
        return revision == other.revision
    }

    override fun hashCode(): Int {
        var result = major.hashCode()
        result = 31 * result + minor.hashCode()
        result = 31 * result + build.hashCode()
        result = 31 * result + revision.hashCode()
        return result
    }

    override fun toString(): String {
        return "Version(major=$major, minor=$minor, build=$build, revision=$revision)"
    }

    companion object {
        fun getVersionFromPackageInformation(packageInfo: PackageInfo): Version {
            val versionCode = packageInfo.longVersionCode
            var versionName = packageInfo.versionName

            if (versionName.isNullOrEmpty()) {
                versionName = ""
            }

            if (versionName.matches(Regex(".*[a-zA-Z].*"))) {
                throw BaseException("VersionName contained letters/words which are invalid")
            }

            val versionAttributesStrings = versionName.split('.')

            if (versionAttributesStrings.size == 1 && versionAttributesStrings[0].isEmpty()) {
                return Version(versionCode)
            }

            val versionAttributes = versionAttributesStrings.map {
                it.toLong()
            }

            return when (versionAttributes.size) {
                1 -> {
                    Version(versionAttributes[0], versionCode)
                }

                2 -> {
                    Version(versionAttributes[0], versionAttributes[1], versionCode)
                }

                3 -> {
                    Version(versionAttributes[0], versionAttributes[1], versionAttributes[2], versionCode)
                }

                else -> {
                    throw BaseException("Version attributes from versionName went over standardized amount which is 3")
                }
            }
        }
    }
}