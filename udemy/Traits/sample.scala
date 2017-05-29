class Boards {
    val arr = Array.ofDim[Repr](10)
    arr(0) = A    
}

trait Repr

object A extends Repr {
    override def toString = "A"
}

object B extends Repr {
    override def toString = "B"
}
