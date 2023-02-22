package Feb22;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IterateOverArrayList {

    public static void main(String[] args) {

        List<String> tvShows = new ArrayList<>();

        tvShows.add("Breaking Bad");

        tvShows.add("Game Of Thrones");

        tvShows.add("Friends");

        tvShows.add("Prison break");

        System.out.println("Iterate using forEach and lambda ");

        tvShows.forEach(tvShow -> {
            System.out.println(tvShow);
        });

        System.out.println("Iterate using an iterator()");

        Iterator<String> tvShowIterator = tvShows.iterator();

        while (tvShowIterator.hasNext()) {

            String tvShow = tvShowIterator.next();

            System.out.println(tvShow);
        }

        System.out.println("\n=== Iterate using an iterator() forEachRemaining() method");

        tvShowIterator = tvShows.iterator();

        tvShowIterator.forEachRemaining(tvShow -> {
            System.out.println(tvShow);
        });

        System.out.println("Iterate using a listIterator() to traverse in both directions");

        // Here, we start from the end of the list and traverse backwards.
        ListIterator<String> tvShowListIterator = tvShows.listIterator(tvShows.size());

        while (tvShowListIterator.hasPrevious()) {

            String tvShow = tvShowListIterator.previous();

            System.out.println(tvShow);
        }
    }
}
