class OverrideString(n: Int, b: Int) {
    require(b>5, "B should be greater than 5")
    override def toString() = n + " hello " + b
}
