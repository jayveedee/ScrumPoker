package com.exirpit.scrumpoker.domain.model.version

import org.junit.Assert.assertEquals
import org.junit.Test


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

        // Act

        // Assert

    }

    @Test
    fun `should return 0 when version is the same as other version`() {
        // Arrange

        // Act

        // Assert

    }
}