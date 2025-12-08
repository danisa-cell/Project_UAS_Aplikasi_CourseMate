import java.io.Serializable

data class Quiz(
    val question: String,
    val options: ArrayList<String>,
    val correctIndex: Int
) : Serializable
