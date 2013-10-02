Project3 - Obstacle Race
========================

**Team Members**: Trevor Davenport, Phuoc Nguyen, Khoa Tran, Corey Short  

**Brief Description**: Two beacon lights will be set up about 20 feet apart, in middle of the hall outside
1174 Etcheverry. The robot starts within 2 feet of one light facing the other and makes 2 round trips. In future 
milestones, we will add objects detector and avoider.

## Milestone 1 Report    

### Control Scheme to Keep the Robot Moving toward the Light     

In order to keep our robot efficiently and effectively moving towards the light, 
we implemented a toLight() method in our Racer.java s.t. our scanner object compares
its current light sensor reading with two different constant light THRESHOLD's that we
deemed optimal based on the experimental work via ScanRecorder.java. (graph provided below)
The scanTo() method in Scanner.java rotates the light sensor to the input degree, (in our
case, its between -60 and 60 degrees) calculates the tacho count of the respective motor,
and while it is doing this, the LightSensor's getLightValue() method is being called to
determine the the maximum light value at the robots current position. This value is defined
in Scanner.java as xLight. 

When we first tested the light intensity, we got different values for both light as one is 
brighter than the other. On our demonstration day, proffesor changes the light bulbs that makes
the light intensity of both lights are the same. At first, we used two different THRESHOLD for
2 light bulbs but now we use only one because the light intensity is the same.

Another problem that we ran into when doing this Milestone is that the light bulb is too high for 
the light sensor that makes it unable to detect the right value of the light intensity. We all 
agreed to raise up the Light Sensor a little bit so it can detect the light value accurately.

For some cases, the light sensor detect the reflection of the light on the wall. We fixed it by
raising up the THRESHOLD value, because the light intensity from the light bulb is bigger than
the one reflected on the wall.

Racer.java's toAngle() method is called based on the the Scanner's
tacho count in scanTo() which calls the differential pilot to steer the robot the direction
of xLight. The trick we used to make this effiecnt was to add the case if our xLight is
greater than our pre-set THRESHOLD we stop the robot and put it to sleep for 500 ms. This
allows us to effectively call the scanTo() method (which was originally created by professor
Glassey in ScanRecorder.java) again and repeat this process in a loop or break out of the loop and turnaround to 
start the next lap.

### Relationship between Classes and Methods for the Tasks and Sub-tasks    

Milestone1.java has the main method for testing and executing our code. It constructs the
differential pilot and scanner instances to their respective motor and sensor ports on the
NXT. Then the racer calls the toLight() method as it is described above.

The Racer itself encapsulates a DifferentialPilot object and a Scanner object, so that it 
can both trace the distance, while search for the lights at the same time

Finally, we package our code to divide up the functionality. The experimental code used to 
collect the data is separated into one package, while all the robot's essentials are in 
another. The test code for each Milestone is in their own corresponding package.

### Graph Output of ScanRecorder.java, and How We Use this Data   
Based on the graph we are able to determine the light intensity coresponding to each angle
from the TachoCount() from Scanner, then the pilot's job is just to steer to that value of
TachoCount(). Also from the data and graph, we can learn that whether the light sensor scanner
is accurate or not. Because at first, there was a gap between 2 sides of the light sensor
that make the value is up/down unexpectedly. Secondly, we did have a flat peak at first because
the eye's angle we scanned was to large that make a lot of same peak values. The way we fixed
it was to use another piece of lego (a pipe) so limit the eye angle that makes the eye just
looks straigth.     

## Milestone 2 Report   
### Responsibilities of the classes designed for this milestone   
* Detector:
* Racer:
* Scanner:    

### Task analysis and Brief Description for Object Detection     
