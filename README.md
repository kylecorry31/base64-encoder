# base64-encoder [![Build Status](https://travis-ci.org/kylecorry31/base64-encoder.svg?branch=master)](https://travis-ci.org/kylecorry31/base64-encoder)
A base64 encoder Java library designed to work on Android devices without support for the Base64 class.

## Getting started

### Examples
Include the jar file from the releases page into your project.

Encoding a string:
```Java
String s = "A test string";
String encoded = Base64Encoder.encode(s); // QSB0ZXN0IHN0cmluZw==
```

Encoding bytes:
```Java
byte[] bytes = "A test string".getBytes();
String encoded = Base64Encoder.encode(bytes); // QSB0ZXN0IHN0cmluZw==
```

All commands presented below work in Mac or Linux, for Windows replace `.\gradlew` with `gradlew.bat`.

### Build
To build the project, run the `./gradlew build` command.

### Jar
To create a jar file which can be imported into other projects, run the `.\gradlew jar` command.

### Test
This library can be tested against the Java 8 Base64 class by executing the `.\gradlew test` command.

## Contributing
Please fork this repo and submit a pull request to contribute. I will review all changes and respond if they are accepted or rejected (as well as reasons, so it will be accepted).

### Issues
If you are submitting a bug, please describe the bug in detail and how to replicate if possible. Logs are also very useful.

If you are submitting a feature idea, please describe it in detail and document the potential use cases of that feature if it isn't clear.

## Credits
I am currently the sole contributor to this project.

## License
You are free to copy, modify, and distribute base64-encoder with attribution under the terms of the MIT license. See the [LICENSE](LICENSE) file for details.
