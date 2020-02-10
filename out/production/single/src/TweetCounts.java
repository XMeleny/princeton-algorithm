import java.util.*;

public class TweetCounts {
    public static HashMap<String, ArrayList<Integer>> map = new HashMap<>();

    public TweetCounts() {
    }

    public void recordTweet(String tweetName, int time) {
        ArrayList<Integer> list;
        if (map.containsKey(tweetName)) {
            list = map.get(tweetName);
            list.add(time);
        } else {
            list = new ArrayList<>();
            list.add(time);
            map.put(tweetName, list);
        }
//        System.out.print("after adding " + tweetName + ": ");
//        for (int i : map.get(tweetName)) {
//            System.out.print(i + "  ");
//        }
//        System.out.println();
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        if (map.containsKey(tweetName)) {
            ArrayList<Integer> list;
            list = map.get(tweetName);
            Collections.sort(list);

            ArrayList<Integer> inTime = new ArrayList<>();
            for (int i : list) {
                if (startTime <= i && i <= endTime)
                    inTime.add(i);
            }

            ArrayList<Integer> result = new ArrayList<>();

            int i, delta;
            int tempStart, tempEnd;
            int count;
            if (freq.equals("minute")) {
                //统计频率是每分钟（60 秒）
                delta = 60;
                i = 1;
                count = 0;
                while (true) {
                    tempStart = startTime + (i - 1) * delta;
                    tempEnd = startTime + i * delta;

                    int item;
                    while (!inTime.isEmpty()) {
                        item = inTime.get(0);
                        if (tempStart <= item && item < tempEnd) {
                            count++;
                            inTime.remove(0);
                        } else break;
                    }

                    if (count > 0) result.add(count);


                    count = 0;
                    i++;
                    if (tempEnd > endTime) break;
                }

            } else if (freq.equals("hour")) {
                //统计频率是每分钟（3600 秒）
                delta = 3600;
                i = 1;
                count = 0;
                while (true) {
                    tempStart = startTime + (i - 1) * delta;
                    tempEnd = startTime + i * delta;

                    int item;
                    while (!inTime.isEmpty()) {
                        item = inTime.get(0);
                        if (tempStart <= item && item < tempEnd) {
                            count++;
                            inTime.remove(0);
                        } else break;
                    }

                    if (count > 0) result.add(count);

                    count = 0;
                    i++;
                    if (tempEnd > endTime) break;
                }

            } else if (freq.equals("day")) {
                //统计频率是每分钟（86400 秒）
                delta = 86400;
                i = 1;
                count = 0;
                while (true) {
                    tempStart = startTime + (i - 1) * delta;
                    tempEnd = startTime + i * delta;

                    int item;
                    while (!inTime.isEmpty()) {
                        item = inTime.get(0);
                        if (tempStart <= item && item < tempEnd) {
                            count++;
                            inTime.remove(0);
                        } else break;
                    }

                    if (count > 0) result.add(count);


                    count = 0;
                    i++;
                    if (tempEnd > endTime) break;
                }

            } else return null;

            return result;
        }

        return null;
    }

//    public static void main(String[] args) {
//        TweetCounts tweetCounts = new TweetCounts();
//        tweetCounts.recordTweet("tweet3", 0);
//        tweetCounts.recordTweet("tweet3", 60);
//        tweetCounts.recordTweet("tweet3", 10);
//        tweetCounts.recordTweet("tweet1", 10);
//
//        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60));
//    }
}
