#!/usr/bin/python
# Author <Jaideep Khandelwal jdk2588@gmail.com>

class Animal1:

    @staticmethod
    def color():
        return "Red"
class Animal2:

    @staticmethod
    def color():
        return "Green"

class Dog(Animal1, Animal2):
    pass

d = Dog()
print(d.color())
