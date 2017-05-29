//Traits and Classes are not much different, you can put anything in between
//curly braces of a class and simillary in between a trait. 
//One can put abstract methods,concrete methods, abstract fields, concrete fields

//Difference between them is , Class can have constructors, Traits can't have 
//Constructors.
//Super is resolved as same as for classes in other languages.
//Classes resolvs calls to super at point class is defined.
//Traits resolve calls to super at point trait is used. It delays untill trait is used

trait Food {
    override def toString = "mmm!"
}

trait Meat {
    override def toString = "meat!"
}

trait Bread extends Food {
    override def toString = "breadlike, " + super.toString
}

//Traits does not know which super to call 
trait Cheese extends Food {
    override def toString = "cheesey, " + super.toString
}

//Classes know which super to call and based on last inherited class or object order
class BreadwithCheeseSandwich extends Bread with Cheese {
    override def toString = "I am " + super.toString
}

class CheesewithBreadSandwich extends Cheese with Bread {
    override def toString = "I am " + super.toString
}
