package superprivate {
    class Super {
        //This becomes usper private
        private[this] def func() = {println("Hello")}
    }

    object Super {
        val obj = new Super()
        obj.func()
    }
}
