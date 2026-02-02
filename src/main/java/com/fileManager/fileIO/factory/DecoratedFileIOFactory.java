package com.fileManager.fileIO.factory;

import com.fileManager.fileIO.IFileIOStratergy;

import com.fileManager.fileIO.Decorators.CompressionFileIODecorator;
import com.fileManager.fileIO.Decorators.LoggingFileIODecorator;

public class DecoratedFileIOFactory implements IFileIOFactory {
    public IFileIOStratergy createFileIOStratergy(){
        IFileIOStratergy baseIO = new LocalFileIOFactory().createFileIOStratergy();
        IFileIOStratergy compressedIO = new CompressionFileIODecorator(baseIO);
        return new LoggingFileIODecorator(compressedIO);
    }
}
