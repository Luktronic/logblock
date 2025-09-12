# LogBlock

> [!NOTE]
> This project is currently under active development and is therefore not usable yet.

LogBlock is an [SLF4J](https://github.com/qos-ch/slf4j)-based utility library that provides an easy way to log 
beautiful information blocks like this one:

```text
[ERROR] MyClass - |=======================================================================================
[ERROR] MyClass - |
[ERROR] MyClass - |	ERROR SETTING UP INSTALLATION DIRECTORY:
[ERROR] MyClass - |	Configured installation path must be absolute - was: ../..
[ERROR] MyClass - |
[ERROR] MyClass - |	Tip: The path must contain the root element
[ERROR] MyClass - |	On Unix systems (Linux/MacOS), this is '/'
[ERROR] MyClass - |	        Example path: '/home/john/nyarie'
[ERROR] MyClass - |
[ERROR] MyClass - |	On Windows, this is your target partition (most commonly 'C:')
[ERROR] MyClass - |	Windows system also require backslashes ('\') instead of forward slashes ('/')
[ERROR] MyClass - |	        Example path: 'C:\Users\john\nyarie'
[ERROR] MyClass - |
[ERROR] MyClass - |=======================================================================================
```

While this is probably most useful for error logs, you can use this functionality for *any* logging level you want!

LogBlock also allows you to customize the logging blocks to your liking, providing configuration options for things like the border String
(in the above example: `=`), the prefix (in the example: `|`), or how much padding the block has (in example: top/bottom = 1 line, left: 1 space).

## Getting started

The core component you will be mainly interacting with is the `LogBlock` class.
It has a `withLogger` static factory method that takes 
an SLF4J `Logger` as its parameter and returns a new instance of the `LogBlock` class. 

You can then simply use the logging level methods that you already know from SLF4j
(`.debug()`, `.info()`, etc.) and start writing your log messages, which will then be 
logged in a fancy block!

```java
public class MyClass {
    static final Logger log = LoggerFactory.getLogger(MyClass.class);
    static final LogBlock logBlock = LogBlock.withLogger(log);
    
    public static void main(String[] args) {
        logBlock.info("Hello World from LogBlock!");
    }
}
```

Running this code will result in the following log message:

```text
[INFO] MyClass - |================================================================================
[INFO] MyClass - |
[INFO] MyClass - |  Hello World from LogBlock!
[INFO] MyClass - |
[INFO] MyClass - |================================================================================
```

## Formatting

LogBlock supports custom formatting

## Building

While the library itself can be used with Java 8+, LogBlock uses [Gradle 9.0.0](https://gradle.org/releases/#9.0.0) 
as its build tool (using Gradle wrapper) and requires Java 24 for building.

## Planned features

- Block borders
- Block line prefix
- Top padding
- Bottom padding
- Left padding

### Currently in consideration

- Headers
- Footers
- Colored block border
- Colored block message
