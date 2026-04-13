package com.example.demo_unit_testing_with_junit

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ScoreViewModelTest {

    private lateinit var viewModel: ScoreViewModel

    @Before
    fun setup() {
        viewModel = ScoreViewModel()
    }

    @Test
    fun addPoints_increasesScore() {
        viewModel.addPoints(10)
        assertEquals(10, viewModel.score.value)
    }

    @Test
    fun addPoints_withCombo_doublesPoints() {
        viewModel.switchCombo() // Activate combo
        viewModel.addPoints(10)
        assertEquals(20, viewModel.score.value)
    }

    @Test
    fun score_neverGoesBelowZero() {
        viewModel.addPoints(-10)
        assertEquals(0, viewModel.score.value)
    }

    @Test
    fun level_increasesEvery50Points() {
        // Test boundary: 49 points (Level 1)
        viewModel.addPoints(49)
        assertEquals(1, viewModel.level.value)

        // Test boundary: 50 points (Level 2)
        viewModel.addPoints(1)
        assertEquals(2, viewModel.level.value)
        
        // Test boundary: 100 points (Level 3)
        viewModel.addPoints(50)
        assertEquals(3, viewModel.level.value)
    }

    @Test
    fun reset_clearsAllState() {
        viewModel.addPoints(100)
        viewModel.switchCombo()
        
        viewModel.reset()
        
        assertEquals(0, viewModel.score.value)
        assertEquals(1, viewModel.level.value)
        assertEquals(false, viewModel.isComboActive.value)
    }
}
