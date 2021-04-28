package Contest.NineChapter.HighFreq.OOD;

import java.util.*;

public class MiniYelp {

    class Node{
        public double distance;
        public Restaurant restaurant;
        public Node(double d, Restaurant r){
            distance = d;
            restaurant = r;
        }
    }

    public NavigableMap<String, Restaurant> restaurants;
    public Map<Integer, String> ids;
    public MiniYelp(){
        ids = new HashMap<>();
        restaurants = new TreeMap<>();
    }

    public int addRestaurant(String name, Location location){
        Restaurant restaurant = Restaurant.create(name, location);
        String hashCode = GeoHash.encode(location) + "#" + restaurant.id;
        ids.put(restaurant.id, hashCode);
        restaurants.put(hashCode, restaurant);
        return restaurant.id;
    }

    public void removeRestaurant(int restaurant_id){
        if(ids.containsKey(restaurant_id)){
            String hashCode = ids.get(restaurant_id);
            ids.remove(restaurant_id);
            if(restaurants.containsKey(hashCode)){
                restaurants.remove(hashCode);
            }
        }
    }

    public List<String> neighbors(Location location, double k){
        int len = get_length(k);
        String hashCode = GeoHash.encode(location);
        hashCode = hashCode.substring(0, len);
        List<Node> results = new ArrayList<>();
        for(Map.Entry<String, Restaurant> entry : restaurants.subMap(hashCode, true, hashCode + "{", true).entrySet()){
            String key = entry.getKey();
            Restaurant value = entry.getValue();
            double distance = Helper.get_distance(location, value.location);
            if(distance <= k){
                results.add(new Node(distance, value));
            }
        }
        Collections.sort(results, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.distance < o2.distance){
                    return -1;
                } else if(o1.distance > o2.distance){
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        List<String> rt = new ArrayList<>();
        int n = results.size();
        for(int i = 0; i < n; i ++){
            rt.add(results.get(i).restaurant.name);
        }
        return rt;
    }

    // precision of geo hash
    private int get_length(double k){
        double[] ERROR = {2500, 630, 78, 20, 2.4, 0.61, 0.076, 0.01911, 0.00478, 0.0005971, 0.0001492, 0.0000186};
        for (int i = 0; i < 12; ++i)
            if (k  > ERROR[i])
                return i;
        return 12;
    }

}

 class Location {
     public double latitude, longitude;
     private Location(double latitude, double longitude){
         this.latitude = latitude;
         this.longitude = longitude;
     }
     public static Location create(double lati, double longi) {
         return new Location(lati, longi);
     }
 };
 //Definition of Restauran*
class Restaurant {
     public int id;
     public String name;
     public Location location;
     public Restaurant(String name, Location location) {
         this.name = name;
         this.location = location;
     }
     public static Restaurant create(String name, Location location) {
         return new Restaurant(name, location);
     }
 };
// Definition of Helpe*
class Helper {
     public static double get_distance(Location location1, Location location2) {
         // return distance between location1 and location2.
         return 0.0;
     }
 };
 class GeoHash {
     public static String encode(Location location) {
         // return convert location to a GeoHash string
         return "";
     }
     public static Location decode(String hashcode) {
          return Location.create(0.0, 0.0);
     }
 };
