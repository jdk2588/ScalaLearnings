package onlytopackage.path {
    class Super {
        protected[onlytopackage] def f() {println("f")}
    }

    class Sub extends Super {
        f()
    }

    class Other {
        (new Super).f()
    }
}
