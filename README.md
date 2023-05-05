# Ulla Appliaction

## Description
Ulla Application is a mobile application that lets its clients control their robotic lawn mower, while it communicates with a backend server throught WebSockets.
With Ulla Application clients can view a map one how Ulla has moved around on the lawn and clients can also view the obsticles that Ulla had collisons with during her mowing.

## Technology

### IDE
Ulla Application is a Android mobile application that was developed with the help of [Android Studio](https://developer.android.com/studio) which is a IDE that was developed by `Google and JetBrains` for Android app development.

### Programming language
Language that was used to develop Ulla Application was [Kotlin](https://kotlinlang.org/). Kotlin is a open-source statically typed programming language.

### WebSocket 
Websockets for Ulla Application to live communicate with the backend for sendning data to the mower and getting data from the mower.

## Install and Run Ulla Application

When you have downloaded `Android Studio`, you can create a new folder and clone the git repository into the created folder. The following code is with the help of a terminal.

```
mkdir <your_folder_name>
cd <your_folder_name>
git clone https://github.com/IMSGroup4/Application.git
cd Application
```

Start up Android Studio on your desktop and click `Open` and select the `Application` folder and then `OK`

After the project has loaded, check so that you have a selected `Device Manager`. If you don't then you have to download one. 

```
Tools -> Device Manager -> Create device
Select your Phone -> Next
```

System Image
```
Recommended
 Release Name  API Level  ABI
   Nougat         24      x86
```
```
x86 Images
 Release Name  API Level  ABI
   Nougat         24      x86
```

After selecting the System Image press Next and then Finish. The Image will be donwloaded to you system. After it's completed you can press the `Play button` the mobile emulator will run and you will be able to see the emulator run the code.


## Websocket JSON to mower

```
# when user uses joystick
 {
     "action" : "joystick",
     "x": 0.5,
     "y": 0.8,
     "timestamp: 1651234567890
 }
```

```
# when user chooses autonomous
 {
     "action" : "autonomous",
     "x": null,
     "y": null,
     "timestamp: 1651234567890
 }
```
