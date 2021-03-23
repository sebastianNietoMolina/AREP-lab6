package app;

import model.User;
import org.apache.commons.codec.binary.Hex;
import spark.Request;
import spark.Response;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static spark.Spark.*;

public class App {

    public static User user = new User("user", "8e8725edeaa090c62110e9c7b23a243873d61b36");
    public static HttpService httpService;

    public static void main(String... args){
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        //secure("keys/ecikeystore.p12", "123456", "keys/trustStoreOtherService", "123456");
        secure("keys/ecikeystore.p12", "123456", "keys/trustStoreOtherService", "123456");
        port(getPort());
        httpService = new HttpService();
        get("/login", (req, res) -> log(req,res));
    }

    public static String log(Request req, Response res) throws NoSuchAlgorithmException, IOException {
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        if(username.equals(user.getUserName()) && getHashSHA1Password(password).equals(user.getPassword())){
            httpService = new HttpService();
            String url = "https://localhost:5001/service2";
            return httpService.readURL(url);
        }else{
            return "The user or password are incorrect";
        }
    }

    public static String getHashSHA1Password(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes());
        byte[] mb = md.digest();
        char[] temp = Hex.encodeHex(mb);
        String pass = "";
        for(char i: temp){
            pass+=i;
        }
        return pass;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }



}