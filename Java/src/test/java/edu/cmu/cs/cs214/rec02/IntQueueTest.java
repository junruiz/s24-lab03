package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;
    private List<Integer> testListLarge;
    private List<Integer> testListDequeue;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
    //    mQueue = new LinkedIntQueue();
        mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
        testListLarge = new ArrayList<>(List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31));
        testListDequeue = new ArrayList<>(List.of(312, 592, 182, 404, 255));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        mQueue.enqueue(5);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        mQueue.peek();
    }

    @Test
    public void testPeekNoEmptyQueue() {
        mQueue.enqueue(1);
        int head = mQueue.peek();
        assertEquals(1, head);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        for (int i = 0; i < testListDequeue.size(); i++) {
            mQueue.enqueue(testListDequeue.get(i));
        }
        for (int i = 0; i < testListDequeue.size(); i++) {
            assertEquals(testListDequeue.get(i), mQueue.dequeue());
        }
    }

    @Test
    public void testDequeueEmpty() {
        assertEquals(null, mQueue.dequeue());
    }

    @Test
    public void testRepeatedEnqueueDequeue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
        for (int i = 0; i < testList.size(); i++) {
            assertEquals(testList.get(i), mQueue.dequeue());
        }
        for (int i = 0; i < testListLarge.size(); i++) {
            mQueue.enqueue(testListLarge.get(i));
            assertEquals(testListLarge.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
        for (int i = 0; i < testListLarge.size(); i++) {
            assertEquals(testListLarge.get(i), mQueue.dequeue());
        }
    }

    @Test
    public void testClear() {
        mQueue.enqueue(1);
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

}
