package Object_Orient_Design.Hotel_Design;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 18:22
 *   @Description :
 *
 */
public class LRUCache extends LinkedHashMap<SearchRequest, Map<RoomType, List<Room>>> {
    private static final long serialVersionUID = 1L;
    private int capacity;
    public LRUCache(int capacity){
        super(capacity);
        this.capacity = capacity;
    }

    // remove the first element you just put in
    @Override
    protected boolean removeEldestEntry(Map.Entry<SearchRequest, Map<RoomType, List<Room>>> eldest) {
        return size() > this.capacity;
    }

    private String printAvailableRooms(Map<RoomType, List<Room>> rooms){
        String res = "";
        for(Map.Entry<RoomType, List<Room>> entry : rooms.entrySet())
        {
            res += "For room type: " + entry.getKey() + ", available rooms are: ";
            for(Room room : entry.getValue())
            {
                res += room.getId() + "; ";
            }
            res += ". ";
        }
        return res;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String res = "";
        for(Map.Entry<SearchRequest, Map<RoomType, List<Room>>> entry : super.entrySet())
        {
            res += ("Search Request -> " + entry.getKey().toString() + "\n");
            res += ("Value -> " + printAvailableRooms(entry.getValue()) + "\n");
            res += "\n";
        }
        return res;
    }
}
