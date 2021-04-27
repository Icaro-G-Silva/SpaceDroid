package com.example.launchcontrol.deltav.domain

import junit.framework.TestCase
import org.junit.Test

class DeltaVUseCaseTest : TestCase() {

    val useCase = DeltaVUseCase()

    @Test
    fun testCalculateDeltaVWhenTotalMassEqualsZero() {
        val result = useCase.calculateDeltaV(0.0, 0.0, 0.0,0.0)

        assertTrue(result is DeltaVReturn.ErrorZero)
    }

    @Test
    fun testCalculateDeltaVWhenDryMassEqualsZero() {
        val result = useCase.calculateDeltaV(1.0, 0.0, 0.0,0.0)

        assertTrue(result is DeltaVReturn.ErrorZero)
    }

    @Test
    fun testCalculateDeltaVWhenDeltaVLessThanZero() {
        val result = useCase.calculateDeltaV(1.0, 99999.0, 0.1,9999.0)

        assertTrue(result is DeltaVReturn.ErrorLessThanZero)
    }

    @Test
    fun testCalculateDeltaVWhenAllValuesIsValid() {
        val result = useCase.calculateDeltaV(1.0, 1.0, 1.0,1.0)

        assertTrue(result is DeltaVReturn.Success)
        assertEquals(0.0, (result as DeltaVReturn.Success).value)
    }
}