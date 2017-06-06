#!/usr/bin/python
# Author <Jaideep Khandelwal jdk2588@gmail.com>
import abc


class PluginBase(metaclass=abc.ABCMeta):
	@abc.abstractmethod
	def load(self, input):
		print(input)

	@abc.abstractmethod
	def save(self, output, data):
		"""Hello"""

class LocalBaseClass:
    pass

@PluginBase.register
class RegisteredImplementation(LocalBaseClass, PluginBase):

	def load(self, input):
		print("H")
		super(RegisteredImplementation, self).load(input)
	def save(self, output, data):
		output.write(data)
class SubclassImplementation(PluginBase):

    def load(self, input):
        return input.read()

    def save(self, output, data):
        return output.write(data)


if __name__ == '__main__':
	obj = RegisteredImplementation()
	obj.load("Hello")
