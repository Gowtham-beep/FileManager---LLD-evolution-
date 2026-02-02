package com.fileManager.fileIO.factory;

import com.fileManager.fileIO.IFileIOStratergy;
import com.fileManager.fileIO.LocalFileIO;

public class LocalFileIOFactory implements IFileIOFactory{
    public IFileIOStratergy createFileIOStratergy(){
        return new LocalFileIO();
    }
}
