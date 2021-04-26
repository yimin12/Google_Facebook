package Contest.NineChapter.HighFreq.Array;

public class ImplementDequeByCircularArray {

    class CircularDeque {
        int[] circularArray;
        int front, rear, size;
        public CircularDeque(int n) {
            // initialize your data structure here
            this.circularArray = new int[n];
            front = getCircularIndex(1);
            rear = getCircularIndex(0);
            size = 0;
        }

        private int getCircularIndex(int n){
            int len = circularArray.length;
            return (n % len + len) % len;
        }
        /**
         * @return:  return true if the array is full
         */
        public boolean isFull() {
            return size == circularArray.length;
        }

        /**
         * @return: return true if there is no element in the array
         */
        public boolean isEmpty() {
            // write your code here
            return size == 0;
        }

        /**
         * @param element: the element given to be added
         * @return: nothing
         */
        public void pushFront(int element) {
            int index = getCircularIndex(front - 1);
            circularArray[index] = element;
            front = index;
            size ++;
        }

        /**
         * @return: pop an element from the front of deque
         */
        public int popFront() {
            int index = front;
            int ele = circularArray[index];
            front = getCircularIndex(index + 1);
            size --;
            return ele;
        }

        /**
         * @param element: element: the element given to be added
         * @return: nothing
         */
        public void pushBack(int element) {
            int index = getCircularIndex(rear + 1);
            circularArray[index] = element;
            rear = index;
            size ++;
        }

        /**
         * @return: pop an element from the tail of deque
         */
        public int popBack() {
            int index = rear;
            int ele = circularArray[index];
            rear = getCircularIndex(index - 1);
            size --;
            return ele;
        }
    }
}
