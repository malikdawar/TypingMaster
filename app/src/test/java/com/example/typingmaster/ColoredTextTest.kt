package com.example.typingmaster

import com.example.typingmaster.ui.screens.home.model.ColoredText
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * ColoredTextTest- Unit tests for the ColoredText
 * @author Malik Dawar
 */
class ColoredTextTest {
    @Test
    fun `create ColoredText instance with text and color`() {
        val text = "Hello"
        val color = 0xFF0000 // Example color value

        val coloredText = ColoredText(text, color)

        assertEquals(text, coloredText.text)
        assertEquals(color, coloredText.color)
    }

    @Test
    fun `create ColoredText instances with different text and color`() {
        val text1 = "Hello"
        val text2 = "World"
        val color1 = 0xFF0000
        val color2 = 0x00FF00

        val coloredText1 = ColoredText(text1, color1)
        val coloredText2 = ColoredText(text2, color2)

        assertEquals(text1, coloredText1.text)
        assertEquals(color1, coloredText1.color)

        assertEquals(text2, coloredText2.text)
        assertEquals(color2, coloredText2.color)
    }
}
