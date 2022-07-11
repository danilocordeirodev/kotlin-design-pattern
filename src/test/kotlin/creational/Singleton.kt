import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

object Singleton {
    init {
        println("Initializing $this")
    }

    fun log(): Singleton = apply { println("Singleton: $this") }
}

class SingletonTest {
    @Test
    fun testSingleton() {
        println("Start")

        val singleton1 = Singleton.log()
        val singleton2 = Singleton.log()

        Assertions.assertThat(singleton1).isSameAs(Singleton)
        Assertions.assertThat(singleton2).isSameAs(Singleton)
    }
}