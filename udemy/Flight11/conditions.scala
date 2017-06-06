/*
Design by contract where invoking a method, it is a transaction server and client

    - Pre-conditions - Should be true when method is called, responsibility of caller
    - Post-conditions - Write before method returns from server
    - Checking invariant consitions - For a class things should be true in between method class

    Use to be done in debugging mode 

Pre-conditions have assume/require/assure
    val a = -1
    assume(a >= 0)
    assert(a >= 0)
    require(a >= 0, s"a is not greater than or equal to 0, a is $a")
Post-consitions have ensuring
    7 ensuring ((i: Int) => i > 0)
 */


