package com.exirpit.scrumpoker.domain.model.version

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
}