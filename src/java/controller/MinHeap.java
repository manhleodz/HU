import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    private List<int[]> heapArray;

    public MinHeap() {
        heapArray = new ArrayList<>();
    }

    public int size() {
        return heapArray.size();
    }

    public boolean empty() {
        return size() == 0;
    }

    public void push(int[] value) {
        heapArray.add(value);
        upHeapify();
    }

    private void upHeapify() {
        int currentIndex = size() - 1;
        while (currentIndex > 0) {
            int[] currentElement = heapArray.get(currentIndex);
            int parentIndex = (currentIndex - 1) / 2;
            int[] parentElement = heapArray.get(parentIndex);

            if (parentElement[0] < currentElement[0]) {
                break;
            } else {
                heapArray.set(parentIndex, currentElement);
                heapArray.set(currentIndex, parentElement);
                currentIndex = parentIndex;
            }
        }
    }

    public int[] top() {
        return heapArray.get(0);
    }

    public void pop() {
        if (!empty()) {
            int lastIndex = size() - 1;
            heapArray.set(0, heapArray.get(lastIndex));
            heapArray.remove(lastIndex);
            downHeapify();
        }
    }

    private void downHeapify() {
        int currentIndex = 0;
        int[] currentElement = heapArray.get(0);
        while (currentIndex < size()) {
            int childIndex1 = currentIndex * 2 + 1;
            int childIndex2 = currentIndex * 2 + 2;
            if (childIndex1 >= size() && childIndex2 >= size()) {
                break;
            } else if (childIndex2 >= size()) {
                int[] childElement1 = heapArray.get(childIndex1);
                if (currentElement[0] < childElement1[0]) {
                    break;
                } else {
                    heapArray.set(childIndex1, currentElement);
                    heapArray.set(currentIndex, childElement1);
                    currentIndex = childIndex1;
                }
            } else {
                int[] childElement1 = heapArray.get(childIndex1);
                int[] childElement2 = heapArray.get(childIndex2);
                if (currentElement[0] < childElement1[0] && currentElement[0] < childElement2[0]) {
                    break;
                } else {
                    if (childElement1[0] < childElement2[0]) {
                        heapArray.set(childIndex1, currentElement);
                        heapArray.set(currentIndex, childElement1);
                        currentIndex = childIndex1;
                    } else {
                        heapArray.set(childIndex2, currentElement);
                        heapArray.set(currentIndex, childElement2);
                        currentIndex = childIndex2;
                    }
                }
            }
        }
    }
}
