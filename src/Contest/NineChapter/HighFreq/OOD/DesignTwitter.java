package Contest.NineChapter.HighFreq.OOD;

import java.util.*;

public class DesignTwitter {

    public static class Tweet {
        public static int id, user_id;
        public static String text;
        private Tweet(int user_id, String tweet_text){
            this.user_id = user_id;
            this.text = tweet_text;
        }
        public static Tweet create(int user_id, String tweet_text){
            return new Tweet(user_id, tweet_text);
        }
    }

    public class MiniTwitter{

        Map<Integer, Set<Integer>> followers;
        Map<Integer, List<Tweet>> tweets;

        public MiniTwitter(){
            tweets = new HashMap<>();
            followers = new HashMap<>();
        }

        /*
         * @param user_id: An integer
         * @param tweet_text: a string
         * @return: a tweet
         */
        public Tweet postTweet(int user_id, String tweet_text) {
            Tweet tweet = Tweet.create(user_id, tweet_text);
            if(!followers.containsKey(user_id)) followers.put(user_id, new HashSet<>());
            Set<Integer> follower = followers.get(user_id);
            if(!follower.contains(user_id)) follower.add(user_id);
            if(!tweets.containsKey(user_id)) tweets.put(user_id, new LinkedList<>());
            List<Tweet> myTweets = tweets.get(user_id);
            if(myTweets.size() >= 10) myTweets.remove(myTweets.size() - 1);
            myTweets.add(0, tweet);
            return tweet;
        }

        /*
         * @param user_id: An integer
         * @return: a list of 10 new feeds recently and sort by timeline
         */
        public List<Tweet> getNewsFeed(int user_id) {
            List<Tweet> res = new LinkedList();
            List<List<Tweet>> newsFeedList = new ArrayList();
            Set<Integer> myFollows = new HashSet();
            if (!followers.containsKey(user_id)) return res;
            for (Integer userId : followers.get(user_id)) {
                if (!tweets.containsKey(userId)) continue;
                if (res.size() == 0) res = tweets.get(userId);
                else res = mergeTwoSortedLists(res, tweets.get(userId));
            }
            return res;
        }

        /*
         * @param user_id: An integer
         * @return: a list of 10 new posts recently and sort by timeline
         */
        public List<Tweet> getTimeline(int user_id) {
            if(!tweets.containsKey(user_id)) return new LinkedList<>();
            return tweets.get(user_id);
        }

        /*
         * @param from_user_id: An integer
         * @param to_user_id: An integer
         * @return: nothing
         */
        public void follow(int from_user_id, int to_user_id) {
            if(!followers.containsKey(from_user_id)) {
                followers.put(from_user_id, new HashSet());
                followers.get(from_user_id).add(from_user_id);
            }

            Set<Integer> follows = followers.get(from_user_id);
            follows.add(to_user_id);
        }

        /*
         * @param from_user_id: An integer
         * @param to_user_id: An integer
         * @return: nothing
         */
        public void unfollow(int from_user_id, int to_user_id) {
            if(!followers.containsKey(from_user_id)) return;
            Set<Integer> follows = followers.get(from_user_id);
            follows.remove(to_user_id);
        }

        List<Tweet> mergeTwoSortedLists(List<Tweet> l1, List<Tweet> l2){
            List<Tweet> res = new LinkedList();
            int i = 0, j = 0;
            while ((i < l1.size() || j < l2.size()) && res.size() < 10) {
                if (i > l1.size() - 1 && j > l2.size() - 1) break;
                else if (i > l1.size() - 1) res.add(l2.get(j++));
                else if (j > l2.size() - 1) res.add(l1.get(i++));
                else if (l1.get(i).id > l2.get(j).id) res.add(l1.get(i++));
                else res.add(l2.get(j++));
            }
            return res;
        }
    }


}
