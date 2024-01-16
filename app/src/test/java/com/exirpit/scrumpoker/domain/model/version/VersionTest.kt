package com.exirpit.scrumpoker.domain.model.version

import android.content.pm.PackageInfo
import com.exirpit.scrumpoker.domain.model.exceptions.BaseException
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VersionTest {

    @Test
    fun `should return 1 when version is greater than other version`() {
        // Arrange
        val unit = Version(1,1,1,1)

        val v1 = Version(1,1,1,0)
        val v2 = Version(1,1,0,0)
        val v3 = Version(1,0,0,0)
        val v4 = Version(0,0,0,0)

        // Act
        val v1ComparisonActual = unit.compareTo(v1)
        val v2ComparisonActual = unit.compareTo(v2)
        val v3ComparisonActual = unit.compareTo(v3)
        val v4ComparisonActual = unit.compareTo(v4)

        // Assert
        assertEquals(1, v1ComparisonActual)
        assertEquals(1, v2ComparisonActual)
        assertEquals(1, v3ComparisonActual)
        assertEquals(1, v4ComparisonActual)
    }

    @Test
    fun `should return -1 when version is lesser than other version`() {
        // Arrange
        val unit = Version(1,1,1,1)

        val v1 = Version(2,1,1,1)
        val v2 = Version(1,2,0,1)
        val v3 = Version(1,1,2,1)
        val v4 = Version(1,1,1,2)

        // Act
        val v1ComparisonActual = unit.compareTo(v1)
        val v2ComparisonActual = unit.compareTo(v2)
        val v3ComparisonActual = unit.compareTo(v3)
        val v4ComparisonActual = unit.compareTo(v4)

        // Assert
        assertEquals(-1, v1ComparisonActual)
        assertEquals(-1, v2ComparisonActual)
        assertEquals(-1, v3ComparisonActual)
        assertEquals(-1, v4ComparisonActual)
    }

    @Test
    fun `should return 0 when version is the same as other version`() {
        // Arrange
        val unit1 = Version(1,1,1,1)
        val unit2 = Version(0,1,1,1)
        val unit3 = Version(0,0,1,1)
        val unit4 = Version(0,0,0,1)

        val v1 = Version(1,1,1,1)
        val v2 = Version(0,1,1,1)
        val v3 = Version(0,0,1,1)
        val v4 = Version(0,0,0,1)

        // Act
        val v1ComparisonActual = unit1.compareTo(v1)
        val v2ComparisonActual = unit2.compareTo(v2)
        val v3ComparisonActual = unit3.compareTo(v3)
        val v4ComparisonActual = unit4.compareTo(v4)

        // Assert
        assertEquals(0, v1ComparisonActual)
        assertEquals(0, v2ComparisonActual)
        assertEquals(0, v3ComparisonActual)
        assertEquals(0, v4ComparisonActual)
    }

    @Test
    fun `should return valid version when version name follows the version standard major, minor, build, revision`() {
        // Arrange
        val mockPackageInfo = mock(PackageInfo::class.java)
        whenever(mockPackageInfo.longVersionCode).thenReturn(1)
        mockPackageInfo.versionName = "1.1.1"

        val unit2 = Version(1,1,1,1)

        // Act
        val result = Version.getVersionFromPackageInformation(mockPackageInfo)

        // Assert
        assertEquals(unit2, result)
    }

    @Test
    fun `should return valid version when version name follows the version standard major, minor, build`() {
        // Arrange
        val mockPackageInfo = mock(PackageInfo::class.java)
        whenever(mockPackageInfo.longVersionCode).thenReturn(1)
        mockPackageInfo.versionName = "1.1"

        val unit2 = Version(1,1,1,0)

        // Act
        val result = Version.getVersionFromPackageInformation(mockPackageInfo)

        // Assert
        assertEquals(unit2, result)
    }

    @Test
    fun `should return valid version when version name follows the version standard major, minor`() {
        // Arrange
        val mockPackageInfo = mock(PackageInfo::class.java)
        whenever(mockPackageInfo.longVersionCode).thenReturn(1)
        mockPackageInfo.versionName = "1"

        val unit2 = Version(1,1,0,0)

        // Act
        val result = Version.getVersionFromPackageInformation(mockPackageInfo)

        // Assert
        assertEquals(unit2, result)
    }

    @Test
    fun `should return valid version when version name follows the version standard major`() {
        // Arrange
        val mockPackageInfo = mock(PackageInfo::class.java)
        whenever(mockPackageInfo.longVersionCode).thenReturn(1)
        mockPackageInfo.versionName = ""

        val unit2 = Version(1,0,0,0)

        // Act
        val result = Version.getVersionFromPackageInformation(mockPackageInfo)

        // Assert
        assertEquals(unit2, result)
    }

    @Test(expected = BaseException::class)
    fun `should throw exception if version name has words or letters`() {
        // Arrange
        val mockPackageInfo = mock(PackageInfo::class.java)
        whenever(mockPackageInfo.longVersionCode).thenReturn(1)
        mockPackageInfo.versionName = "asd.asd.beta"

        // Act
        Version.getVersionFromPackageInformation(mockPackageInfo)

        // Assert
    }

    @Test(expected = BaseException::class)
    fun `should throw exception if version name has capitalized words or letters`() {
        // Arrange
        val mockPackageInfo = mock(PackageInfo::class.java)
        whenever(mockPackageInfo.longVersionCode).thenReturn(1)
        mockPackageInfo.versionName = "ASD.ASD.BETA"

        // Act
        Version.getVersionFromPackageInformation(mockPackageInfo)

        // Assert
    }
}