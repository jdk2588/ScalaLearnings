/*

- Traits and Classes are not much different, you can put anything in between
  curly braces of a class and simillary in between a trait.
- One can put abstract methods,concrete methods, abstract fields, concrete fields

- Difference between them is , Class can have constructors, Traits can't have Constructors.
- Super is resolved as same as for classes in other languages.
- Classes resolvs calls to super at point class is defined.
- Traits resolve calls to super at point trait is used.
  It delays untill trait is used. This is not doing at run time. It is static and resolves at compile time.

 - Traits helps in having multiple interfaces and that allows to make rich interfaces,
   Here in the example if you see with trait Rectangular only topLeft and bottomRight
   are abstract, so it makes job easier for implemtor whereas Rectangle gets all of
   implemented methods like left, right and width in Rectangle

 class Point(val x: Int, val y: Int)
 trait Rectangular {
     def topLeft: Point
     def bottomRight: Point
     def left = topLeft.x
     def right = bottomRight.x
     def width = right - left
 }

class Rectangle(
    val topLeft: Point
    val bottomRight: Point
) extends Rectanglur {

}

 Difference in abstract class and traits
    - Abstract class can have constructor paramters and type parameters. Traits
      can only have type parameters.
    - Abstract classes are compatible with Java. Traits are compatible only if
      they do not have implementation code.
    - A class can inherit from multiple traits but only one abstract.
 */

class Food {
    override def toString = "mmm!"
}

trait Bread extends Food {
    override def toString = "breadlike, " + super.toString
}

//Traits does not know which super to call
trait Cheese extends Food {
    override def toString = "cheesey, " + super.toString
}

//Classes know which super to call and based on last inherited class or object order
//Behaviour of Cheese will be taken first because it is last, then Bread and Food
// A question here ? Why did not you get I am cheesey, mmm!breadlike, mmm!
//The reason is because traits the method is called with linearization of the classes
//and traits that are mixed into a class
class BreadwithCheeseSandwich extends Bread with Cheese {
    override def toString = "I am " + super.toString
}

class CheesewithBreadSandwich extends Cheese with Bread {
    override def toString = "I am " + super.toString
}

/* how does Linearization is done by compiler
CheesewithBreadSandwich->Bread->Cheese->Food->AnyRef->Any
When the trait is used to define a class, the linearization is decided and all of the traits are told who their superclass is.*/
