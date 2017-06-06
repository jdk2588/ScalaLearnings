abstract class IntQueue {
    def get(): Int
    def put(x: Int)
}

import scala.collection.mutable.ArrayBuffer
abstract class BasicIntQueue extends IntQueue {
    private val buf  = new ArrayBuffer[Int]
    def get() = buf.remove(0)
    def put(x: Int): Unit = buf += x
}

trait Doubling extends IntQueue {
    abstract override def put(x: Int): Unit = super.put(2*x)
}

    //Java does not allow to override abstract method and Scala does no
	/*This form acknowledges to the compiler that both the method is a known override, and that it is still abstract (requiring the real implementation to be provided by an implementing class*/
trait Flitering extends IntQueue {
    //Java does not allow to override abstract method
    abstract override def put(x: Int): Unit = if (x >= 0) super.put(x)
}

trait Incrementing extends IntQueue {
    //Java does not allow to override abstract method
    abstract override def put(x: Int): Unit = super.put(x+1)
}
class MyQueue extends BasicIntQueue with Doubling with Incrementing


/*
 The trait Doubling  a trait that doubles integers as they are put in the queue.
 The Doubling trait has two funny things going on.

    - The first is that it declares a superclass, IntQueue.
      This declaration means that the trait can only be mixed into a class that also extends IntQueue.
      Thus, you can mix Doubling into BasicIntQueue.

    - The second funny thing is that the trait has a super call on a method declared abstract.
      Such calls are illegal for normal classes, because they will certainly fail at run time.
      For a trait, however, such a call can actually succeed.
      Since super calls in a trait are dynamically bound,
      the super call in trait Doubling will work so long as the trait is mixed -
      in after another trait or class that gives a concrete definition to the method.

This arrangement is frequently needed with traits that implement stackable modifications.
To tell the compiler you are doing this on purpose, you must mark such methods as abstract override.
This combination of modifiers is only allowed for members of traits, not classes,
and it means that the trait must be mixed into some class that has a concrete definition of the method in question.

Doubling has Stackable modifications implementation and this is possible due to the
fact as super in traits is not decided when it is written, but when it is used.
 */

/*
If the behavior will not be reused, then make it a concrete class. It is not reusable behavior after all.

If it might be reused in multiple, unrelated classes, make it a trait.
Only traits can be mixed into different parts of the class hierarchy.

If you want to inherit from it in Java code, use an abstract class.
Since traits with code do not have a close Java analog,
it tends to be awkward to inherit from a trait in a Java class.
Inheriting from a Scala class, meanwhile, is exactly like inheriting from a Java class.
As one exception, a Scala trait with only abstract members translates directly to a Java interface, so you should feel free to define such traits even if you expect Java code to inherit from it.

Cons-
If you plan to distribute it in compiled form, and you expect outside groups to write classes inheriting from it,
you might lean towards using an abstract class.
The issue is that when a trait gains or loses a member,
any classes that inherit from it must be recompiled, even if they have not changed.
If outside clients will only call into the behavior, instead of inheriting from it, then using a trait is fine.

If efficiency is very important, lean towards using a class.
Most Java runtimes make a virtual method invocation of a class member a faster operation
than an interface method invocation.
Traits get compiled to interfaces and therefore may pay a slight performance overhead.
However, you should make this choice only if you know that the trait in question constitutes a performance bottleneck and have evidence that using a class instead actually solves the problem.

If you still do not know, after considering the above, then start by making it as a trait. You can always change it later, and in general using a trait keeps more options open.
*/
