package structural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

data class DisplayDataType(val index: Float, val data: String)

class DataDisplay {
    fun displayData(data: DisplayDataType) {
        println("${data.index} - ${data.data}")
    }
}

data class DatabaseData(val position: Int, val amount: Int)

class DatabaseDataGenerator {
    fun generateData(): List<DatabaseData> {
        var list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(2, 2))
        return list
    }
}

interface DatabaseDataConverter {
    fun convertData(data: List<DatabaseData>): List<DisplayDataType>
}

class DataDisplayAdapter(val display: DataDisplay) : DatabaseDataConverter {
    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
        val returnList = arrayListOf<DisplayDataType>()

        for (dd in data) {
            val ddt = DisplayDataType(dd.position.toFloat(), dd.amount.toString())
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }

}

class AdapterTest {
    @Test
    fun adapterTest() {
        val generator = DatabaseDataGenerator()
        val generatedData = generator.generateData()
        val adapter = DataDisplayAdapter(DataDisplay())
        val convertData = adapter.convertData(generatedData)

        Assertions.assertThat(convertData.size).isEqualTo(1)
    }
}