package ceneax.app.lib.astatine

import org.junit.Test

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println("======= start ========")
        TestA {}()
    }
}

class TestA(block: () -> Unit) : () -> Unit {
    override fun invoke() {
        println("new TestA and execute invoke")
    }
}