package ca.csf.minesweeper.model;

/*
 * Generic implementation of the Java Observer class.
 * 
 * @param Observer<T> The observer of type <T> that will update the observers via the update method
 * 
 * @see ca.csf.minesweeper.model.Observable
 */
public abstract class Observer<T> {
  protected Subject<T> subject;
  public abstract void update();
  public abstract void update(Subject<T> sender, T argument);
}
