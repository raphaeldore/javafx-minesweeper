package ca.csf.minesweeper.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Subject<T> {
  private List<Observer<T>> observers;

  public Subject() {
    this.observers = new ArrayList<Observer<T>>();
  }

  public void addObserver(Observer<T> observer) {
    if (observer == null) {
      throw new NullPointerException();
    }

    // We do not want to be adding the same observer twice
    if (!observers.contains(observer)) {
      observers.add(observer);
    }
  }
  
  public void clearObservers()
  {
    observers.clear();
  }

  public void removeObserver(Observer<T> observer) {
    if (!observers.contains(observer)) {
      throw new NoSuchElementException();
    }
    observers.remove(observer);
  }

  protected void notifyObservers(T argument) {
    for (Observer<T> observer : observers) {
      observer.update(this, argument);
    }
  }

  public int countObservers() {
    return observers.size();
  }
}