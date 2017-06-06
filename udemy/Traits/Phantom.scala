class isEven

def ensureEven(i: Int): Int with isEven = {
    require(i%2==0, "Int should be even")
    i.asInstanceOf[Int with isEven]
}

def onlyEven(x: Int with isEven) = println(s"is even number $x")
