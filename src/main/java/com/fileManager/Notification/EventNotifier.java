package com.fileManager.Notification;

import java.util.ArrayList;
import java.util.List;

import com.fileManager.Notification.Observer.IFileObserver;

public class EventNotifier {
    public static EventNotifier instance = null;

    public static EventNotifier getInstance(){
        if(instance == null){
            instance = new EventNotifier();
        }
        return instance;
    }
    private List<IFileObserver> observers = new ArrayList<>();
    public void addObserver(IFileObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IFileObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(String event){
        for(IFileObserver observer : observers){
            observer.update(event);
        }
    }

}
