package com.fortis.crm.android.ui.customer.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CustomerContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Customer> ITEMS = new ArrayList<Customer>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Customer> ITEM_MAP = new HashMap<String, Customer>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static List<Character> firstLetterList() {


        return null;
    }

    private static void addItem(Customer item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getCustomerNo(), item);
    }

    private static Customer createDummyItem(int position) {
        return new Customer(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}