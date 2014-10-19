package ca.csf.minesweeper.model;


public class GameTile<T> extends Observer<T> {

  public GameTile() {}

  public GameTile(Subject<T> subject) {
    this.subject = subject;
    this.subject.addObserver(this);
  }

  @Override
  public void update() {
    // TODO: Implement this empty method
  }

  @Override
  public void update(Subject<T> sender, T argument) {
    System.out.print(argument.toString());

  }

}
