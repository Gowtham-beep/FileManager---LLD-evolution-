package com.fileManager;

import com.fileManager.FileManager.FileManager;
import com.fileManager.Notification.EventNotifier;
import com.fileManager.Notification.Observer.UpdateFileObserver;
import com.fileManager.fileIO.LocalFileIO;


public class App {
    public static void main(String[] args) {
        System.out.println("FILE MANAGER!");
        FileManager fileManager = new FileManager(new LocalFileIO());
        EventNotifier.getInstance().addObserver(new UpdateFileObserver());

        String data = "TESTING THE FILE MANAGER";
        fileManager.upload("/home/wbtserver/IdeaProjects/fileManager/test.txt",data.getBytes());
    }
}
