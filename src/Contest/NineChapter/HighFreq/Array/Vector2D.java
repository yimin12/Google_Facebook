package Contest.NineChapter.HighFreq.Array;

import java.util.List;

public class Vector2D {

    private int row, col;
    private List<List<Integer>> list;

    public Vector2D(List<List<Integer>> vec2d) {
        list = vec2d;
        row = 0;
        col = 0;
    }

    public Integer next() {
        if (!hasNext()) { return null; }
        return list.get(row).get(col++);
    }

    public boolean hasNext() {
        while (row < list.size() && col == list.get(row).size()) {
            row++;
            col = 0;
        }
        return row < list.size();
    }

    public void remove() {
        if (col - 1 < 0) {
            return;
        }
        if (row < list.size() && col <= list.get(row).size()) {
            list.get(row).remove(--col);
        }
    }
}
