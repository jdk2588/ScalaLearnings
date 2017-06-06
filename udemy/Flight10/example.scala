package outside {
    package inside1 {
        class Animal
    }

    package inside2 {
        class Dog {
          val animal = new inside1.Animal()
        }
    }
}
