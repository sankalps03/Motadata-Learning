import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class pingRuntime {


    public static void execute(String ip){



        System.out.println(ip);

        if (ip == null){

            System.out.println("Ip address is null");

            return;
        }
        StringBuilder pingSummary = new StringBuilder();

        String ping = "ping -c 50 " + ip;

        BufferedReader input = null;

        try {
            Runtime runtime = Runtime.getRuntime();

            Process process = runtime.exec(ping);

            input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String input_line_by_line ;

            while ((input_line_by_line = input.readLine()) != null) {

                System.out.println(input_line_by_line);

                pingSummary = pingSummary.append("\n").append(input_line_by_line);

            }
            webSocketEndPoint.getPingData(pingSummary.toString());
        }
        catch (IOException e) {

            System.out.println(e);
        }
        finally {

            if(input != null) {

                try {

                    input.close();

                } catch (IOException e) {

                    throw new RuntimeException(e);
                }
            }
        }
    }
}
