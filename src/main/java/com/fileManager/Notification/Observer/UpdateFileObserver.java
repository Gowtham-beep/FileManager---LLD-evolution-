package com.fileManager.Notification.Observer;

public class UpdateFileObserver implements IFileObserver {
@Override
public void update(String event){
    System.out.println("Event:"+ event);
}    

}
