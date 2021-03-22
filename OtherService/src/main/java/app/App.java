package app;

import java.util.Date;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.secure;

public class App {

    public static void main(String... args){
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        //secure("keys/ecikeystore.p12", "123456", "keys/trustStoreLogin", "123456");
        port(getPort());
        get("/service2", (req, res) -> date());
    }

    public static String date(){
        System.out.println("listo pal service");
        return new Date().toString();
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001;
    }

}