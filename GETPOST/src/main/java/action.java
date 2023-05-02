import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class action implements Action {
    String name;
    String email;

    String dataJson;

    String Motadataally;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    String result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotadataally() {
        return Motadataally;
    }

    public void setMotadataally(String motadataally) {
        Motadataally = motadataally;
    }





    @Override
    public String execute() throws Exception {

        System.out.println("inset called");

        database.insert(this);

        return SUCCESS;
    }

    public String select(){

        System.out.println("select called");

        result = database.select();

        return SUCCESS;
    }


}