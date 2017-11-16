 [ ![Download](https://api.bintray.com/packages/airgoss/airGOss/device/images/download.svg) ](https://bintray.com/airgoss/airGOss/device/_latestVersion)

# Android Device
The airG android device library is a group of utility methods for getting runtime information about the device such as API Level, Camera availability, Connectivity, Soft Keyboard utilities, and generic hardware information (CPU core count, hardware id, etc.). All of the functionality here is available through various services and classes in the Android SDK, but this library just gives you a few shortcuts to them.

`compile 'com.airg.android:device:+@aar'`

## API Levels
The `ApiLevel` contains simple API Level query methods such as `atLeast (int minApiLevel)`, `atMost (int maxApilevel)`, `is (int targetApiLevel)`, `get ()`, and more. You'd ideally do an `import static` on the desired methods and use them where needed.

## Cameras
The `Cameras` class provides simple Camera related query methods that allow you to determine whether the device has any cameras, has selfie camera, supports raw capture, etc. These methods just use the Android [`Package Manager`](https://developer.android.com/reference/android/content/pm/PackageManager.html) to gather the required information.

## Connectivity
The `Connectivity` class allows you to query the connectivity state of the device. You can tell whether the device is online or not and whether the connection is _WiFi_ or _Mobile_. You can also just get a handle to the [`ConnectivityManager`](https://developer.android.com/reference/android/net/ConnectivityManager.html) service and perform other queries.

## Device
The `Device` class gives you access to the device's hardware id, available CPU cores, System services, etc.

## Keyboard
The `Keyboard` class allows you to open, close, and toggle the soft keyboard.

# Contributions
Contributions are appreciated and welcome. In order to contribute to this repo please follow these steps:

1. Fork the repo
1. Add this repo as the `upstream` repo in your fork (`git remote add upstream git@github.com:airG/android-device.git`)
1. Contribute (Be sure to format your code according to th included code style settings)
1. IMPORTANT: Rebase with upstream (`git pull --rebase upstream`)
1. Submit a pull request
