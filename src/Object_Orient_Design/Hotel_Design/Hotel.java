package Object_Orient_Design.Hotel_Design;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2020/8/13 18:20
 *   @Description :
 *
 */
public class Hotel {
    public static final int DAY = 1*24*60*60*1000; // convert days to ms
    private List<Room> rooms;
    private LRUCache cache; // provide high searching efficiency

    public Hotel(int cacheSize){
        cache = new LRUCache(cacheSize);
        rooms = new ArrayList<>();
    }

    public Reservation makeReservation(ReservationRequest request){
        Reservation reservation = new Reservation(request.getStartDate(), request.getEndDate());
        SearchRequest search = new SearchRequest(request.getStartDate(), request.getEndDate());
        Map<RoomType, List<Room>> roomsAvailable = getAvailable(search);
        Map<RoomType, Integer> roomsNeeded = request.getRoomsNeeded();
        for(Map.Entry<RoomType, Integer> entry:roomsNeeded.entrySet()){
            RoomType type = entry.getKey();
            int roomCount = entry.getValue();
            List<Room> rooms = roomsAvailable.get(type);
            // case 1: no enough room
            if(entry.getValue() > rooms.size()){
                cache.put(search, roomsAvailable); // store the search request and the available situation
                return null;
            }
            // case 2: enough room
            for(int i = 0; i < roomCount; i++){
                Room room = rooms.remove(0);
                reservation.getRoomsNeeded().add(room);
            }
            // update the order status
            roomsAvailable.put(entry.getKey(), rooms);
        }
        cache.put(search, roomsAvailable);
        for(Room room:reservation.getRoomsNeeded()){
            room.makeReservation(reservation.getStartDate(), reservation.getEndDate());
        }
        return reservation;
    }

    public Map<RoomType, List<Room>> handleSearchResult(SearchRequest request){
        if(cache.containsKey(request)){
            return cache.get(request);
        }
        // Once you handle the request, you should return the latest info, so we should update the roomsAvailable
        Map<RoomType, List<Room>> res = getAvailable(request);
        cache.put(request, res);
        return res;
    }

    public void cancelReservation(Reservation reservation){
        for(Room room:reservation.getRoomsNeeded()){
            room.cancelReservation(reservation);
        }
    }

    public String printCache(){
        return "Printing Cache ...\n" + cache.toString() +
                "*****************************\n";
    }

    private Map<RoomType, List<Room>> getAvailable(SearchRequest request) {
        Map<RoomType, List<Room>> res = new HashMap<>();
        res.put(RoomType.SINGLE, new ArrayList<>());
        res.put(RoomType.DOUBLE, new ArrayList<>());
        for(Room room : rooms){
            if(room.isValidRequest(request)){
                List<Room> roomList = res.get(room.getRoomType());
                roomList.add(room);
                res.put(room.getRoomType(), roomList);
            }
        }
        return res;
    }

}
