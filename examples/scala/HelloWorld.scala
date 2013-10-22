package timezra.codemime

object HelloWorld {

  /** doc */ /* block */ // inline
  @throws(classOf[Exception])
  def main(args: Array[String]) {
    val greeting = "Hello"
    var name = "World"
    println(s"${greeting}, ${name}!");
    println(<span id="salutation">{greeting}, {name}!</span>);
  }
}
