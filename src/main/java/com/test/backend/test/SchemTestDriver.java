package com.test.backend.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.backend.utility.HashSHA512;



@Service
@Transactional
public class SchemTestDriver {
	
	@Autowired private HashSHA512 hashSHA512;
	
	public void testApiTestVerifyCard() {
		
        try {
        	
            //String url = "http://localhost/card-scheme/verify/4812528568495704";
            String url = "http://109.203.125.75:8085/card-scheme/verify/4812528568495704";

            String USER_AGENT = "Mozilla/5.0";
            String cache = "no-cache";
            
            String appKey = "test_20191123132233";
            String timeStamp = "1617953042";
            String hashed = hashSHA512.getHashSHA512(appKey + timeStamp);
            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
               
                HttpGet request = new HttpGet(url);

                request.addHeader("User-Agent", USER_AGENT);
                request.addHeader("cache-control", cache);
                
                request.addHeader("appKey", appKey);
                request.addHeader("timeStamp", timeStamp);
                request.addHeader("hashed", hashed);
                
                HttpResponse response = client.execute(request);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                
                StringBuilder result = new StringBuilder();
                String line = "";
                
                while ((line = rd.readLine()) != null) {
                    
                    result.append(line);
                    
                }
                
                ObjectMapper mapper = new ObjectMapper();                
                JsonNode root = mapper.readTree(result.toString());
                
                System.out.println(root);               

                client.close();                   
                                               
            }
                                                                                               
            
        } catch (IOException | UnsupportedOperationException ex) {

            System.out.println("Could not retrieve transaction properties " + ex.getMessage());
            
        }		
		
        
	}
	
	public void testApiTestHitCount() {
		
        try {
        	
            //String url = "http://localhost/card-scheme/stats?start=1&limit=3";
            String url = "http://109.203.125.75:8085/card-scheme/stats?start=1&limit=3";

            String USER_AGENT = "Mozilla/5.0";
            String cache = "no-cache";
            
            String appKey = "test_20191123132233";
            String timeStamp = "1617953042";
            String hashed = hashSHA512.getHashSHA512(appKey + timeStamp);
            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
               
                HttpGet request = new HttpGet(url);

                request.addHeader("User-Agent", USER_AGENT);
                request.addHeader("cache-control", cache);
                
                request.addHeader("appKey", appKey);
                request.addHeader("timeStamp", timeStamp);
                request.addHeader("hashed", hashed);
                
                HttpResponse response = client.execute(request);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                
                StringBuilder result = new StringBuilder();
                String line = "";
                
                while ((line = rd.readLine()) != null) {
                    
                    result.append(line);
                    
                }
                
                ObjectMapper mapper = new ObjectMapper();                
                JsonNode root = mapper.readTree(result.toString());
                
                System.out.println(root);               

                client.close();                   
                                               
            }
                                                                                               
            
        } catch (IOException | UnsupportedOperationException ex) {

            System.out.println("Could not retrieve transaction properties " + ex.getMessage());
            
        }		
        
	}	

}
