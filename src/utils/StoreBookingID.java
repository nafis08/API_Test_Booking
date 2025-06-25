package utils;
import java.util.ArrayList;
import java.util.List;

public class StoreBookingID {
    private static List<String> bookingIds = new ArrayList<>();

    public static void addBookingId(String id) {
        bookingIds.add(id);
    }

    public static List<String> getBookingIds() {
        return bookingIds;
    }

    public static String getBookingId(int index) {
        return bookingIds.get(index);
    }

    /*
    public static void clear() {
        bookingIds.clear();
    }
    */
}
