package ca.csf.minesweeper.model;

/*
 * Generic implementation of the Java Observer class.
 * 
 * @param Observer<T> The observer of type <T> that will update the observers via the update method
 * 
 * @see ca.csf.minesweeper.model.Subject
 */
public interface Observer<T> {

  public void update(Subject<T> sender, T argument);
}
