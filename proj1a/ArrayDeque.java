import org.hamcrest.core.Is;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.security.cert.PolicyNode;
import java.util.Arrays;

/**
 * @author Diyang Li
 * @create 2022-01-26 10:40 PM
 */
public class ArrayDeque<T> {
    private T[] arr;
    private int nextFirst;
    private int nextLast;
    private static int size;
    private static int len = 8;

    public ArrayDeque() {
        arr = (T[]) new Object[len];
        size = 0;
        nextFirst = len - 1;
        nextLast = 0;

    }

    private void extendArray() {

        int curLen = len * 2;
        T[] curArr = (T[]) new Object[curLen];
//        System.arraycopy(arr, 0, curArr, 0, nextLast);
//        int start = len - 1;
//        int curStart = curLen - 1;
//        while (start >= nextLast) {
//            if (arr[start] == null) break;
//            curArr[curStart] = arr[start];
//            curStart--;
//            start--;
//        }
//        arr = curArr;
//        len = curLen;
//        nextFirst = curStart;

        int last = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] != null){
                curArr[last] = arr[i];
                last++;
            }
        }
        len = curLen;
        nextFirst = curLen-1;
        nextLast = last;
        arr = curArr;
    }

    private void shinkArray(int threashold) {
        int curLen = (int) (threashold / 0.5);
        T[] curArr = (T[]) new Object[curLen];
        int last = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] != null){
                curArr[last] = arr[i];
                last++;
            }
        }
        len = curLen;
        nextFirst = curLen-1;
        nextLast = last;
        arr = curArr;

    }

    private void ifExtend() {
        if (size >= len) {
            extendArray();
        }
        ;
    }

    public void addFirst(T item) {
        ifExtend();
        arr[nextFirst] = item;
        nextFirst--;
        size++;
    }

    public void addLast(T item) {
        ifExtend();
        arr[nextLast] = item;
        nextLast++;
        size++;
    }

    private int getThreshold() {
        return (len > 8) ? (int) (len * 0.25) : 8;
    }

    private void ifShink(int threshold) {
        if (size <= threshold && len > 8) {
            shinkArray(threshold);
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item;
        int threshold = getThreshold();
        if (nextFirst == len - 1) {
            item = arr[0];
            arr[0] = null;
            nextFirst = 0;
        } else {
            nextFirst++;
            item = arr[nextFirst];
            arr[nextFirst] = null;
        }
        size--;
        ifShink(threshold);
        return item;
    }

    public T removeLast() {
        if (size == 0) return null;
        T item = null;
        int threshold = getThreshold();
        if (nextLast == 0) {
            item = arr[len - 1];
            arr[len - 1] = null;
            nextLast = len - 1;
        } else {
            nextLast--;
            item = arr[nextLast];
            arr[nextLast] = null;
        }
        size--;
        ifShink(threshold);
        return item;
    }

    public T get(int index){
        return arr[index];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return (size==0)?true:false;
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> arr = new ArrayDeque<>();
//        arr.addLast(1);
//        arr.addLast(2);
//        arr.addLast(3);
//        arr.addLast(4);
//        arr.addLast(5);
//        arr.addFirst(6);
//        arr.addFirst(7);
//        arr.addFirst(8);
//        arr.addFirst(9);
//        arr.addFirst(10);
//
//        arr.addLast(11);
//        arr.addLast(12);
//        arr.addLast(13);
//        arr.addLast(14);
//        arr.addLast(15);
        System.out.println(arr.isEmpty());
        System.out.println(arr.size());

    }

}

