package com.fileManager.fileIO.Decorators;

import com.fileManager.fileIO.IFileIOStratergy;

public class LoggingFileIODecorator extends FileIODecorator {
    public LoggingFileIODecorator(IFileIOStratergy stratergy){
        super(stratergy);
        }
    @Override
    public void save(String filePath, byte[] data){
        System.out.println("Saving file: " + filePath);
        super.save(filePath, data);
        System.out.println("File saved successfully: " + filePath);
    }

    @Override
    public byte[] read(String filePath) {
        System.out.println("Reading file: " + filePath);
        byte[] data = super.read(filePath);
        System.out.println("File read successfully: " + filePath);
        return data;
    }

    @Override
    public void delete(String filePath) {
        System.out.println("Deleting file: " + filePath);
        super.delete(filePath);
        System.out.println("File deleted: " + filePath);
    }

    @Override
    public boolean find(String filePath) {
        System.out.println("Checking if file exists: " + filePath);
        boolean exists = super.find(filePath);
        System.out.println("File " + filePath + " exists: " + exists);
        return exists;
    }
}
