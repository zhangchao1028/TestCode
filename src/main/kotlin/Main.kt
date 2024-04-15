import chapi.domain.core.CodeContainer
import chapi.domain.core.CodeDataStruct
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.streams.asStream

fun main(args: Array<String>) {

    analyse();
}

private val impl = chapi.ast.javaast.JavaAnalyser()

fun analyse() {
     getFilesByPath("/Users/u0045881/Documents/soft/chapi-test/").forEach { analysisByFile(it) }


}

fun getFilesByPath(path: String, predicate: (File) -> Boolean = { true }): List<File> {
    var http=null
    var http2=null
    var file = File(path).walk().asStream().parallel()
        .filter { it.isFile }
        .filter { predicate(it) }
        .toList()

    return file
}

fun File.readContent(): String {
    val text = readText()
    // fix for Window issue
//    if (text.startsWith("\uFEFF")) {
//        return text.substring(1);
//    }
    return text
}

fun analysisByFile(file: File): List<CodeDataStruct> {
    val codeContainer = impl.analysis(file.readContent(), file.name)

    val jsonString = Json.encodeToString(codeContainer)
    println("key1 value: $jsonString")
    var result = codeContainer.Containers.flatMap { container ->
        container.DataStructures.map {
            it.apply {
                it.Imports = codeContainer.Imports
                it.FilePath = file.absolutePath
            }
        }
    }

    println("key1 value: $codeContainer")
    return result
}