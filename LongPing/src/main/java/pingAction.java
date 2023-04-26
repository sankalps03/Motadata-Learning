import com.opensymphony.xwork2.Action;

public class pingAction implements Action {

    public static String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    private static String ipAddress;
    @Override
    public String execute() throws Exception {

        pingRuntime ping = new pingRuntime(ipAddress);

        Thread pinging = new Thread(ping);

        pinging.start();

        return SUCCESS;
    }
}
