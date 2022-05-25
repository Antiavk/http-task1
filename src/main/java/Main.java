import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.access.JavaNetUriAccess;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import java.io.IOException;
import java.util.List;



public class main {

    public static final String REMOTE_SERVICE_URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static <CloseableHttpClient> void main(String[] args) throws IOException {
        JavaNetUriAccess HttpClientBuilder = null;
        CloseableHttpClient httpclient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)   // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)     // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet httpGet = new HttpGet(REMOTE_SERVICE_URI);
        httpGet.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response = httpclient.execute(httpGet);
        List<DataRequest> dataRequests = MAPPER.readValue(
                response.getEntity()
                        .getContent(),
                new TypeReference<>() {
                });

        dataRequests.stream()
                .filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0)
                .forEach(System.out::println);
    }
}


