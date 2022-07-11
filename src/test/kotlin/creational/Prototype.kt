import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

abstract class Shape: Cloneable {
    var id: String? = null
    var type: String? = null

    abstract fun draw()

    public override fun clone(): Any {
        var clone: Any? = null
        try {
            clone = super.clone()
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return clone!!

    }
}

class Rectangle: Shape() {
    override fun draw() {
        println("Inside rectangle::draw")
    }

    init {
        type = "Rectangle"
    }
}

class Square: Shape() {
    override fun draw() {
        println("Inside square::draw")
    }

    init {
        type = "Square"
    }
}

class Circle: Shape() {
    override fun draw() {
        println("Inside circle::draw")
    }

    init {
        type = "Circle"
    }
}

object ShapeCache {
    private val shapeMap = hashMapOf<String?, Shape>()

    fun loadCache() {
        val circle = Circle()
        val square = Square()
        val rectangle = Rectangle()

        shapeMap["1"] = circle
        shapeMap["2"] = square
        shapeMap["3"] = rectangle

    }

    fun getShape(shapeId: String): Shape {
        val cachedShape = shapeMap[shapeId]
        return cachedShape?.clone() as Shape
    }
}

class PrototypeTest {
    @Test
    fun testPrototype() {
        ShapeCache.loadCache()

        val clonedShape = ShapeCache.getShape("1")

        clonedShape.draw()

        Assertions.assertThat(clonedShape.type).isEqualTo("Circle")
    }
}