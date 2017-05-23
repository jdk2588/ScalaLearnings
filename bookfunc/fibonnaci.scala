object fibonacci {
    def fibonacci(n: int): int = {
        @annotation.tailrec
        def go(n: int, x: int, y: int): int = {
            if (n==0) x 
            else go(n-1, y, x+y)
        }
        go(n,0,1)
    }

    def main(args: array[string]) = println(fibonacci(args(0).toint))
}
