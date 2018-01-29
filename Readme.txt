
This program calculates the water accumulated inside the pits. This program cannot simulate the amount of blocks of water would flow and accumulate across the pits within Landscape.

Note: clean code principles are applied at certain level, comments are used where ever necessary to explain function of complex method.
 
-> com.lanLandscape.model.Landscape is the composite object for this Flow contains data + main functions/responsibilities of it.
-> com.lanLandscape.model.helper.LandscapeHelper contains certain method required for executing functions of Landscape
-> com.lanLandscape.model.validation.ValidateLandscape is responsible for validation of data inserted and maintain integrity of object at any point of time.
-> com.lanLandscape.model.type.LandForms is the Enum describing types of LandForms, with the idea that Landscape is made up of various types of LandForms such as Hills and Pits, for details https://en.wikipedia.org/wiki/Landscape
-> com.lanLandscape.App is main method classs.

Note:
- We can run the project via App class or by running tests to understand the behavior. Instead of going for JMockito created TestSuit utility class with required things for applying DeEncapsulation in order to cover the tests in depth.
- For tests, currently the log level is set to Info, kindly switch logback.xml entry to Debug if required for detail understanding of flow of program.
- Version of JDK used is 1.8. Maven 3.x is required to run the project, we can use the eclipse built in Embedded Maven also.
