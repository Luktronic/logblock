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

## Building

While the library itself can be used with Java 8+, LogBlock uses [Gradle 9.0.0](https://gradle.org/releases/#9.0.0) 
as its build tool (using Gradle wrapper) and therefore requires Java 17+ for building.

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
