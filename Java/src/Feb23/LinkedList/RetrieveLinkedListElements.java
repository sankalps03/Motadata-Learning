package Feb23.LinkedList;

import java.util.LinkedList;

public class RetrieveLinkedListElements {

    public static void main(String[] args) {
        try {

            LinkedList<Double> stockPrices = new LinkedList<>();

            stockPrices.add(45.00);

            stockPrices.add(51.00);

            stockPrices.add(62.50);

            stockPrices.add(42.75);

            stockPrices.add(36.80);

            stockPrices.add(68.40);

            Double firstElement = stockPrices.getFirst();

            System.out.println("Initial Stock Price : " + firstElement);

            Double lastElement = stockPrices.getLast();

            System.out.println("Current Stock Price : " + lastElement);

            Double stockPriceOn3rdDay = stockPrices.get(2);

            System.out.println("Stock Price on 3rd Day : " + stockPriceOn3rdDay);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
