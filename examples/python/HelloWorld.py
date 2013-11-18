#!/usr/local/bin/python2.7
# encoding: utf-8
'''
@note: docstring
'''
import sys

class HelloWorld(object):
    def greet(self, name=None):
        print 'Hello, ' + `name` # Comment

@apply
def main():
    HelloWorld().greet(sys.argv[1])
