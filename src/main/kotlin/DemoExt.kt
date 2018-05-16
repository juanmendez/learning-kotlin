@file:JvmName("Utils")
@file:JvmMultifileClass

import java.io.IOException

fun helloAgain() = "Hello from DemoExt"

@Throws(IOException::class)
fun throwUnsignedException(){
    throw IOException()
}