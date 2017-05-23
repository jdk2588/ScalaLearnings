object isSorted {

    @annotation.tailrec
    def check[A](as: Array[A], c: (A,A) => Boolean): Boolean = {
        def loop(n: Int): Boolean = 
            if (n >= as.length-1) true
            else if (c(as(n), as(n+1))) false
            else loop(n+1)
        loop(0)
    }

    def main(args: Array[String]) = 
        println(check(Array(4,5,3), (a:Any, b:Any) => a > b))
}
