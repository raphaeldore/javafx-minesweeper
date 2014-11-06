package ca.csf.minesweeper.model;

/*
 * Generic implementation of the Java Observer class.
 * 
 * @param Observer<T> The observer of type <T> that will update the via via the update method when called
 * by a Subject
 * 
 * @see ca.csf.minesweeper.model.Subject
 */
public interface Observer<T> {

  public void update(Subject<T> sender, T argument);
}
