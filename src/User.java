import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
    private long id;
    private String userName;
    private String ip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        User bob = new User();
        bob.setId(123456789);
        bob.setIp("10.10.10.10");
        bob.setUserName("Bob Ross");

        try {
            File file = new File(System.getProperty("user.home") + "/.chatData/user1.json");
            file.getParentFile().mkdirs();
            mapper.writeValue(file, bob);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            File file = new File(System.getProperty("user.home") + "/.chatData/user1.json");
            file.getParentFile().mkdirs();
            User testbob = mapper.readValue(file, User.class);
            System.out.println(testbob.getId());
            System.out.println(testbob.getIp());
            System.out.println(testbob.getUserName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
