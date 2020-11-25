package Algorithm.Iterator;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/9/3 21:06
 *   @Description :
 *      Given a file and assume that you can only read the file using a given method read4, implement a method to read_n_characters.
        Method read4:
        The API read4reads 4 consecutive characters from the file, then writes those characters into the buffer arraybuf.
        The return value is the number of actual characters read.
        Note that read4()has its own file pointer, much likeFILE *fpin C.
        Definition of read4:
            Parameter:  char[] buf
            Returns:    int

        Note: buf[] is destination not source, the results from read4 will be copied to buf[]
        Below is a high level example of howread4works:
        File file("abcdefghijk"); // File is "abcdefghijk", initially file pointer (fp) points to 'a'
        char[] buf = new char[4]; // Create buffer with enough space to store characters
        read4(buf); // read4 returns 4. Now buf = "abcd", fp points to 'e'
        read4(buf); // read4 returns 4. Now buf = "efgh", fp points to 'i'
        read4(buf); // read4 returns 3. Now buf = "ijk", fp points to end of file
        Method read:
        By using theread4method, implement the method readthat readsncharacters from the file and store it in the buffer array buf. Consider that you cannot manipulate the file directly.
        The return value is the number of actual characters read.
        Definition of read:
            Parameters:    char[] buf, int n
            Returns:    int

        Note: buf[] is destination not source, you will need to write the results to buf[]
        Example 1:
        Input:
        file = "abc", n = 4

        Output:
        3

        Explanation:
         After calling your read method, buf should contain "abc". We read a total of 3 characters from the file, so return 3. Note that "abc" is the file's content, not buf. buf is the destination buffer that you will have to write the results to.
        Example 2:
        Input:
        file = "abcde", n = 5

        Output:
        5

        Explanation:
        After calling your read method, buf should contain "abcde". We read a total of 5 characters from the file, so return 5.
        Example 3:
        Input:
        file = "abcdABCD1234", n = 12

        Output:
        12

        Explanation:
        After calling your read method, buf should contain "abcdABCD1234". We read a total of 12 characters from the file, so return 12.
        Example 4:
        Input:
        file = "leetcode", n = 5

        Output:
        5

        Explanation:
        After calling your read method, buf should contain "leetc". We read a total of 5 characters from the file, so return 5.
        Note:
        Consider that you cannot manipulate the file directly, the file is only accesible for read4 but not for read.
        The read function will only be called once for each test case.
        You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n  characters.
 */
public class ReadNCharactersI {

    // Provided Read4 API
    abstract class Read4{
       abstract int read(char[] buffer, int n);
       public int read4(char[] temp){
           return 111;
       }
    }

    class MyReaderI extends Read4{
        private int SIZE = 4;

        @Override
        int read(char[] buffer, int n) {
            char[] temp = new char[SIZE];
            int cur = 0;
            int count = 0;
            boolean EndOfFile = false;
            while(!EndOfFile && cur < n){
                count = read4(temp);
                // In case the file is less than 4
                EndOfFile = (count <SIZE);
                int i = 0;
                while(i < count && cur < n){
                    buffer[cur++] = temp[i++];
                }
            }
            return cur;
        }
    }

    /*
        Given a file and assume that you can only read the file using a given method read4, implement a methodreadto read_n_characters.Your method readmay be called multiple times.
        Method read4:
        The API read4reads 4 consecutive characters from the file, then writes those characters into the buffer arraybuf.
        The return value is the number of actual characters read.
        Note that read4()has its own file pointer, much likeFILE *fpin C.
     */

    class MyReaderIi extends Read4{

        private int SIZE = 4;
        private int bufCount = 0;
        private int bufEnd = 0;
        private int bufPtr = 0;
        private char[] ibuf = new char[SIZE];

        @Override
        int read(char[] buffer, int n) {
            int curPtr = 0;
            boolean EOF = false;
            while(curPtr < n && !EOF){
                if(bufCount == 0){
                    bufCount = read4(ibuf);
                    bufEnd = bufCount;
                    bufPtr = 0;
                    if(bufCount < 4){
                        EOF = true;
                    }
                }
                while (bufPtr < bufEnd && curPtr < n){
                    buffer[curPtr++] = ibuf[bufPtr++];
                    bufCount--;
                }
            }
            return curPtr;
        }
    }
}
