### ThoughfulRoboticArm ###

This software package provides a method for sorting packages based on mass and size. It returns the name of a stack to place a given package in. The software includes unit tests and a main method for running it directly.

### Setup

This software requires JDK 21 and maven to be setup.

To build and test the software, run `mvn clean package`. This will package the software into ThoughtfulRoboticArm-0.0.1.jar, located in the project's target directory.

To run the software, once built, run `java -jar ThoughtfulRoboticArm-0.0.1.jar <width> <length> <height> <mass>`, where each argument is a non-zero, positive, floating point number describing a package measurement.

Invalid arguments will throw an IllegalArgumentException.
