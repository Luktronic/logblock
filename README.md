# LogBlock

> [!NOTE]
> LogBlock has just had its first release on Maven Central with version `1.0.0-rc.1`!
> 
> If you encounter any bugs, or simply want to request a new feature/improvement, 
> please feel free to [open an issue](https://github.com/Luktronic/logblock/issues/new).
> 
> If you simply want to ask questions or talk about the project, feel free to head into the [Discussions](https://github.com/Luktronic/logblock/discussions).

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

### Multiline messages

LogBlock will parse every `\n` inside of your log message, 
and treat the new line as a separate log statement.

Example for a multiline log message:
```java
String hello = "Hello";
String world = "World";
logBlock.info("To everyone who reads this,\n" +
        "I wish to you a very dear:\n" +
        "{} {}!", hello, world);
```

> [!TIP]
> When using LogBlock in a project with JDK 15 or higher, you can use the text block syntax to
> easily print out multi-line log statements!
> 
> ```java
> logBlock.info("""
>        To everyone who reads this,
>        I wish to you a very dear:
>        {} {}!
>        """, hello, world); 
> ```

The log message will look like this:

```text
[INFO] MyClass - |================================================================================
[INFO] MyClass - |
[INFO] MyClass - |  To everyone who reads this,
[INFO] MyClass - |  I wish to you a very dear:
[INFO] MyClass - |  Hello World!
[INFO] MyClass - |
[INFO] MyClass - |================================================================================
```

## Formatting

LogBlock comes with a nice default format for your blocks.
However, you can customize the formatting on a global level or just temporarily for a single log statement.

<details>
<summary>Default formatting values</summary>

- Border delimiter: `=`
- Border length: `80`
- Border thickness: `1`
- Line prefix: `|`
- Padding left: `2`
- Padding top: `1`
- Padding bottom: `1`

</details>

### Custom formatting

#### Single-Statement Formatting

You can set the formatting of a single log statement through the use of 
the fluent API methods provided by your `LogBlock` instance.

This strategy of formatting is only applied for the **next** `LogBlock` log statement.
After that, the formatting will be reset to the default formatting.

Example:
```java
logBlock
    .withBorderDelimiter("+")
    .withBorderThickness(2)
    .withPaddingTop(2)
    .withPaddingBottom(2)
    .info("This is my custom formatted log statement!");

logBlock.info("This will have default formatting again!");
```
#### Custom default format

LogBlock allows you to overwrite the default format by setting 
different Java System properties.

> [!TIP]
> A fully documented list of all the System properties can be 
> found in the public `LogBlockSystemProperties` class.   
> It will tell you the name of the system property, what type is expected, 
> what the default value is, and which validation rules apply.
> <details>
> <summary>List of System properties</summary>
>
> - `eu.luktronic.logblock.border.delimiter`
> - `eu.luktronic.logblock.border.length`
> - `eu.luktronic.logblock.border.thickness`
> - `eu.luktronic.logblock.line-prefix`
> - `eu.luktronic.logblock.padding.left`
> - `eu.luktronic.logblock.padding.top`
> - `eu.luktronic.logblock.padding.bottom`
> </details>

```text
# Default format
[INFO] MyClass - |================================================================================
[INFO] MyClass - |
[INFO] MyClass - |  Hello World from LogBlock!
[INFO] MyClass - |
[INFO] MyClass - |================================================================================

# Default format with `eu.luktronic.logblock.border.delimiter=-`
[INFO] MyClass - |--------------------------------------------------------------------------------
[INFO] MyClass - |
[INFO] MyClass - |  Hello World from LogBlock!
[INFO] MyClass - |
[INFO] MyClass - |--------------------------------------------------------------------------------
```

## Building

While the library itself can be used with Java 8+, LogBlock uses [Gradle 9.0.0](https://gradle.org/releases/#9.0.0) 
as its build tool (using Gradle wrapper) and requires Java 24 for building.

## Planned features

- Block borders ✅
- Block line prefix ✅
- Top padding ✅
- Bottom padding ✅
- Left padding ✅

### Currently in consideration

- Headers
- Footers
- Colored block border
- Colored block message
