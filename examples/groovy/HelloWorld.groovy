package timezra.codemime

class HelloWorld {
	static void main(args) {
		def name = args[0].replaceAll(/\\w+/, { it[0].toUpperCase() + ((it.size() > 1) ? it[1..-1] : '') })
		println "Hello, ${name}!"
	}
}
