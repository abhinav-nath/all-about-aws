package com.codecafe.aws.opensearch.mainprograms;

import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.base.RestClientTransport;
import org.opensearch.client.base.Transport;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._global.IndexRequest;
import org.opensearch.client.opensearch._global.SearchResponse;
import org.opensearch.client.opensearch.indices.*;
import org.opensearch.client.opensearch.indices.put_settings.IndexSettingsBody;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;

public class OpenSearchClientExample {

    static class IndexData {
        private String firstName;
        private String lastName;

        public IndexData(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return String.format("IndexData{first name='%s', last name='%s'}", firstName, lastName);
        }
    }

    private static TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
    };


    public static void main(String[] args) {
        try {

            // Only for demo purposes. Don't specify your credentials in code.
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials("admin", "admin"));

            // Initialize the client with SSL and TLS enabled
            RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "https")).
                    setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                        @SneakyThrows
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                            SSLContext sslContext = SSLContext.getInstance("TLS");
                            sslContext.init(null, trustAllCerts, new SecureRandom());
                            httpClientBuilder.setSSLContext(sslContext);
                            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        }
                    }).build();
            Transport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
            OpenSearchClient client = new OpenSearchClient(transport);

            // Create the index
            String index = "sample-index";
            CreateRequest createIndexRequest = new CreateRequest.Builder().index(index).build();
            client.indices().create(createIndexRequest);

            // Add some settings to the index
            IndexSettings indexSettings = new IndexSettings.Builder().autoExpandReplicas("0-all").build();
            IndexSettingsBody settingsBody = new IndexSettingsBody.Builder().settings(indexSettings).build();
            PutSettingsRequest putSettingsRequest = new PutSettingsRequest.Builder().index(index).value(settingsBody).build();
            client.indices().putSettings(putSettingsRequest);

            // Index some data
            IndexData indexData = new IndexData("first_name", "Bruce");
            IndexRequest<IndexData> indexRequest = new IndexRequest.Builder<IndexData>().index(index).id("1").value(indexData).build();
            client.index(indexRequest);

            // Search for the document
            SearchResponse<IndexData> searchResponse = client.search(s -> s.index(index), IndexData.class);
            for (int i = 0; i < searchResponse.hits().hits().size(); i++) {
                System.out.println(searchResponse.hits().hits().get(i).source());
            }

            // Delete the document
            client.delete(b -> b.index(index).id("1"));

            // Delete the index
            DeleteRequest deleteRequest = new DeleteRequest.Builder().index(index).build();
            DeleteResponse deleteResponse = client.indices().delete(deleteRequest);

            restClient.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}