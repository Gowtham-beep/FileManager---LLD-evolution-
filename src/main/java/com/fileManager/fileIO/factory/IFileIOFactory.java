package com.fileManager.fileIO.factory;

import com.fileManager.fileIO.IFileIOStratergy;

public interface IFileIOFactory {
    public IFileIOStratergy createFileIOStratergy();
}
