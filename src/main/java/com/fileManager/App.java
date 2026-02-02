package com.fileManager;

import com.fileManager.FileManager.FileManager;
import com.fileManager.Notification.EventNotifier;
import com.fileManager.Notification.Observer.UpdateFileObserver;
import com.fileManager.fileIO.LocalFileIO;
import com.fileManager.fileIO.IFileIOStratergy;
import com.fileManager.fileIO.Decorators.LoggingFileIODecorator;
import com.fileManager.fileIO.factory.DecoratedFileIOFactory;
import com.fileManager.fileIO.factory.IFileIOFactory;
import com.fileManager.fileIO.Decorators.CompressionFileIODecorator;


public class App {
    public static void main(String[] args) {
        System.out.println("FILE MANAGER!");
        
        // For decorated operations
        IFileIOFactory decoratedFactory = new DecoratedFileIOFactory();
        IFileIOStratergy decoratedIO = decoratedFactory.createFileIOStratergy();
        FileManager fileManager = new FileManager(decoratedIO);

        EventNotifier.getInstance().addObserver(new UpdateFileObserver());

        String data = "TESTING THE FILE MANAGER";
        fileManager.upload("/home/wbtserver/IdeaProjects/fileManager/test.txt",data.getBytes());

        // Demonstrate Decorator Pattern
        // System.out.println("\n=== Demonstrating Decorator Pattern ===");
        // IFileIOStratergy decoratedIO = new LoggingFileIODecorator(
        //     new CompressionFileIODecorator(
        //         new LocalFileIO()
        //     )
        // );

        FileManager decoratedFileManager = new FileManager(decoratedIO);
        EventNotifier.getInstance().addObserver(new UpdateFileObserver());

        String testData = "This is test data that will be compressed and logged by the decorators.";
        String testFilePath = "decorated_test.txt";

        // Save with decorators (compression + logging)
        decoratedFileManager.upload(testFilePath, testData.getBytes());

        // Read with decorators (decompression + logging)
        byte[] readData = decoratedFileManager.download(testFilePath);
        System.out.println("Read data: " + new String(readData));

        System.out.println("=== Decorator Pattern Demo Complete ===");
    }
}
