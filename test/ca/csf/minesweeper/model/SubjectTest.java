/*package ca.csf.minesweeper.model;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

*//**
 * @author Raphaël Doré
 *
 *//*

public class SubjectTest {

  // TODO: TEMP. This is a placeholder. Source: http://stackoverflow.com/a/1119559
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); // TODO
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream(); // TODO

  private Subject<String> subject;

  @Before
  public void setUp() throws Exception {
    subject = new Subject<String>();
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @Test
  public void whenObservableInstanceCreated_thenObserverCountIsZero() {
    assertEquals(0, subject.countObservers());
  }

  @Test(expected = NullPointerException.class)
  public void whenAddingNullObserver_thenThrowNullPointerException() {
    GameTile<String> observer = null;
    subject.addObserver(observer);
    assertEquals(0, subject.countObservers()); // Just to be certain, we verify that the Observer
                                               // amount did not increase.
  }

  @Test
  public void whenAddingObserver_thenObserversCountIsOne() {
    new GameTile<String>(subject);
    assertEquals(1, subject.countObservers());
  }

  @Test
  public void whenAddingAnAlreadyExistingObserver_thenItIsNotAdded_ThusObserverCountDoesNotChange() {
    GameTile<String> observer = new GameTile<String>(subject);
    subject.addObserver(observer);
    assertEquals(1, subject.countObservers());
  }

  @Test(expected = NoSuchElementException.class)
  public void whenAddingAnObserverAndRemovingItAndRemovingItAgain_thenThrowNoSuchElementException() {
    GameTile<String> observer = new GameTile<String>(subject);
    subject.removeObserver(observer);
    subject.removeObserver(observer);
  }

  @Test
  public void whenObserverIsNotified_thenUpdateMethodIsCalledAndWorks() {
    new GameTile<String>(subject);
    subject.notifyObservers("Hello".toString());
    String output = outContent.toString();
    assertEquals("Hello".toString(), output);
  }

  // TODO: TEMP
  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

}
*/