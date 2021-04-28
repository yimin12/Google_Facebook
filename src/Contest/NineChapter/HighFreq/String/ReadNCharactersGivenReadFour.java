package Contest.NineChapter.HighFreq.String;

public abstract class ReadNCharactersGivenReadFour {

    abstract public int read4(char[] buf);

    char[] buffer = new char[4];
    int head = 0, tail = 0;

    public int readII(char[] buf, int n){ // buf is for output
        int i = 0;
        while(i < n){
            if(head == tail){
                head = 0;
                tail = read4(buffer);
                if(tail == 0){
                    break;
                }
            }
            while(i < n && head < tail){
                buf[i ++] = buffer[head ++];
            }
        }
        return i;
    }
}
