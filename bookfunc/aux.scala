class Number(x: Int, y: Int) {
    override def toString() = s"$x/$y"
    def this(z: Int) = this(10,z)

    def +(x: Int): Number = {
        new Number(x*x, y*y)
    }
}
