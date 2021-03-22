package app;

import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpService {
    public OkHttpClient httpClient;

    public HttpService() {
        httpClient = new OkHttpClient();
    }

    public String readURL(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        System.out.println(request);
        Response response = httpClient.newCall(request).execute();
        System.out.println(response);
        return response.body().string();
    }
}
