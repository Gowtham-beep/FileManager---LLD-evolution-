# FileManager

A simple FileManager application built in Java to practice and learn Low-Level Design (LLD) design patterns and principles.

## Purpose

This project serves as a learning tool for understanding and implementing common design patterns in object-oriented programming. It demonstrates the practical application of design patterns in a real-world scenario - managing file operations with extensibility and maintainability in mind.

## Design Patterns Implemented

### 1. Strategy Pattern
- **Location**: `FileManager` class and `fileIO` package
- **Purpose**: Allows the FileManager to switch between different file I/O strategies without changing its code
- **Implementation**:
  - `IFileIOStratergy` interface defines the contract for file operations (save, read, delete, find)
  - `LocalFileIO` implements the interface for local file system operations
  - `FileManager` uses composition to delegate file operations to the strategy

### 2. Singleton Pattern
- **Location**: `EventNotifier` class
- **Purpose**: Ensures only one instance of the event notifier exists throughout the application
- **Implementation**:
  - Static `instance` variable holds the single instance
  - `getInstance()` method provides global access to the instance
  - Used for centralized event management

### 3. Observer Pattern
- **Location**: `Notification` package
- **Purpose**: Implements a publish-subscribe mechanism for file operation notifications
- **Implementation**:
  - `IFileObserver` interface defines the observer contract
  - `UpdateFileObserver` implements the interface to handle notifications
  - `EventNotifier` (singleton) manages observers and notifies them of events

### 4. Decorator Pattern
- **Location**: `fileIO/Decorators` package
- **Purpose**: Allows dynamic addition of responsibilities to file I/O operations (logging, compression, encryption, etc.) without modifying existing code
- **Implementation**:
  - `FileIODecorator` abstract class implements `IFileIOStratergy` and holds a reference to another `IFileIOStratergy`
  - `LoggingFileIODecorator` adds logging functionality to file operations
  - `CompressionFileIODecorator` adds compression/decompression functionality
  - Decorators can be stacked: `new LoggingFileIODecorator(new CompressionFileIODecorator(new LocalFileIO()))`

### 5. Factory Method Pattern
- **Location**: `fileIO/factory` package
- **Purpose**: Provides an interface for creating file I/O strategy objects, allowing subclasses to decide which class to instantiate
- **Implementation**:
  - `IFileIOFactory` interface defines the factory method `createFileIOStratergy()`
  - `LocalFileIOFactory` creates `LocalFileIO` instances
  - `LoggingFileIOFactory` creates `LoggingFileIODecorator` instances with proper decorator chaining
  - `CompressionFileIODecoratorFactory` creates `CompressionFileIODecorator` instances
  - `DecoratedFileIOFactory` creates fully decorated file I/O instances (compression + logging)

## Project Structure

```
src/
├── main/java/com/fileManager/
│   ├── App.java                    # Main application entry point
│   ├── FileManager/
│   │   └── FileManager.java        # Main FileManager class (Strategy pattern)
│   ├── fileIO/
│   │   ├── IFileIOStratergy.java   # Strategy interface
│   │   ├── LocalFileIO.java        # Concrete strategy implementation
│   │   ├── FileIODecorator.java    # Abstract decorator base class
│   │   └── Decorators/
│   │       ├── LoggingFileIODecorator.java     # Logging decorator
│   │       └── CompressionFileIODecorator.java # Compression decorator
│   └── factory/
│       ├── IFileIOFactory.java     # Factory Method interface
│       ├── LocalFileIOFactory.java # Factory for LocalFileIO
│       ├── LoggingFileIOFactory.java # Factory for LoggingFileIODecorator
│       ├── CompressionFileIODecoratorFactory.java # Factory for CompressionFileIODecorator
│       └── DecoratedFileIOFactory.java # Factory for decorated file I/O
│   └── Notification/
│       ├── EventNotifier.java      # Singleton + Subject (Observer pattern)
│       └── Observer/
│           ├── IFileObserver.java      # Observer interface
│           └── UpdateFileObserver.java # Concrete observer
└── test/java/com/fileManager/
    └── AppTest.java                # Unit tests
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Building and Running

1. **Clone the repository** (if applicable)
   ```bash
   git clone <repository-url>
   cd fileManager
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run tests**
   ```bash
   mvn test
   ```

4. **Package the application**
   ```bash
   mvn package
   ```

5. **Run the application**
   ```bash
   java -cp target/classes com.fileManager.App
   ```

## Usage Example

```java
// Create a FileManager using Factory Method pattern
IFileIOFactory factory = new LocalFileIOFactory();
IFileIOStratergy fileIO = factory.createFileIOStratergy();
FileManager fileManager = new FileManager(fileIO);

// Register an observer for notifications
EventNotifier.getInstance().addObserver(new UpdateFileObserver());

// Perform file operations
fileManager.upload("example.txt", "Hello World".getBytes());
byte[] data = fileManager.download("example.txt");

// Using Decorator Pattern with Factory Method
IFileIOFactory decoratedFactory = new DecoratedFileIOFactory();
IFileIOStratergy decoratedIO = decoratedFactory.createFileIOStratergy();
FileManager decoratedFileManager = new FileManager(decoratedIO);

// Now file operations will be logged AND compressed!
decoratedFileManager.upload("compressed_file.txt", "This data will be compressed".getBytes());
byte[] decompressedData = decoratedFileManager.download("compressed_file.txt");

// Alternative: Using individual factories
IFileIOFactory loggingFactory = new LoggingFileIOFactory();
IFileIOStratergy loggingIO = loggingFactory.createFileIOStratergy();
FileManager loggingFileManager = new FileManager(loggingIO);
```

## Design Principles Demonstrated

- **Single Responsibility Principle**: Each class has a single, well-defined responsibility
- **Open/Closed Principle**: The system is open for extension (new strategies, observers) but closed for modification
- **Dependency Inversion**: High-level modules (FileManager) don't depend on low-level modules (LocalFileIO), but both depend on abstractions (IFileIOStratergy)
- **Composition over Inheritance**: Uses composition to achieve flexibility (Strategy pattern)
- **Factory Method Pattern**: Encapsulates object creation, making the system more maintainable and extensible

## Future Enhancements

- Add more file I/O strategies (CloudFileIO, DatabaseFileIO)
- Implement additional observers for different notification types
- Add error handling and logging
- Include more comprehensive unit tests

## Contributing

This is a learning project, but suggestions for improvements or additional patterns are welcome!

## License

This project is for educational purposes only.