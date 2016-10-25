#Fancy Music Spectrum Animation

I am impressed with the animation from dribble and decided to write an implementation of the following animation.

Also, I have found the implementation of their product [Wave In App - CleverRoad Github](https://github.com/Cleveroad/WaveInApp). However, the code is written in OpenGL and the animation is repeating in  consistent pattern compare to the fancy animation that shown in their showcase.

Thus, I decided to write an implementation with Android Canvas that work exactly with the animation.

First world things, break the animation into parts.
1. Sine Graph 
2. Bubble Effect
##Animation Preview
[gif]()
[mygif]()

<--Original Animation --- My Implementation-->

##Method 1
1. Use WaveForm Data that can be acquired from DataCapture Listener
2. Use FFT Data that can be acquired from DataCapture Listener

P/S: Waveform Data will return a byte array of 1024 length with data range of -127 to 128. I have been using this data to simulate the graph.

##DrawBack of Method 1
1. Data Capture Listener is affected by Speaker Volume, which mean if the device have low volume, the spectrum gonna be ugly.

##Preview of Method 1
[gif1]()

##Method 2
1. Random a sine graph with random variable with the following equation:
~~~
y = A * sin( a * x ) + B * sin( b * x ) + C * sin( c * x )
~~~

##Drawback of Method 2
1. The graph draw in similar pattern over the song plays.

##Preview of Method 2
[gif2]()

##Method 3
