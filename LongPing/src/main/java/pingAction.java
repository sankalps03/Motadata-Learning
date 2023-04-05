import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

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

        pingRuntime.execute(ipAddress);

        return SUCCESS;
    }
}
