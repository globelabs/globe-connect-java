/* 
 * The MIT License
 *
 * Copyright 2016 charleszamora.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ph.com.globe.connect;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStoreException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;

import org.json.JSONObject;

/**
 * HttpRequest helper.
 * 
 * @author Charles Zamora czamora@openovate.com
 */
public class HttpRequest {
    /* Default user agent */
    private final String USER_AGENT = "Mozilla/5.0";
    
    /* Default content type */
    private final String CONTENT_TYPE = "application/json";
    
    /* Host url */
    protected String url = null;
    
    /* Request data */
    protected Map<String, Object> data = null;

    /**
     * Create HttpRequest without any parameter.
     */
    public HttpRequest() {}
    
    /**
     * Create HttpRequest with url parameter.
     * 
     * @param url target url
     */
    public HttpRequest(String url) {
        this.url = url;
    }
    
    /**
     * Returns the current data.
     * 
     * @return Map
     */
    public Map<String, Object> getData() {
        return this.data;
    }
    
    /**
     * Returns the current data
     * as JSONObject.
     * 
     * @return JSONObject
     */
    public JSONObject getDataAsJson() {
        return new JSONObject(this.data);
    }
    
    /**
     * Set request url.
     * 
     * @param  url target url
     * @return this
     */
    public HttpRequest setUrl(String url) {
        // set url
        this.url = url;
        
        return this;
    }
    
    /**
     * Set request data.
     * 
     * @param  data post data
     * @return this
     */
    public HttpRequest setData(Map<String, Object> data) {
        // set request data
        this.data = data;
        
        return this;
    }
    
    /**
     * Sends get request to the specified url.
     * 
     * @return CloseableHttpResponse
     * @throws HttpRequestException http request exception
     */
    public CloseableHttpResponse sendGet() 
        throws HttpRequestException {
        
        // try building up
        try {
            // initialize ssl context builder
            SSLContextBuilder builder = new SSLContextBuilder();

            // set trust self signed strategy
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

            // initialize ssl socket connection factory
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(builder.build());

            // default http client
            CloseableHttpClient client = HttpClients
                    .custom()
                    .setSSLSocketFactory(sslSocketFactory)
                    .build();

            // create request method
            HttpGet request = new HttpGet(this.url);

            // set default header
            request.setHeader("User-Agent", this.USER_AGENT);

            // try request
            try {
                // execute request and get response
                CloseableHttpResponse response = client.execute(request);

                return response;
            } catch (IOException e) {
                throw new HttpRequestException(e.getMessage());
            }
        } catch(NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new HttpRequestException(e.getMessage());
        }
    }
    
    /**
     * Send post request to the specified url.
     * 
     * @return CloseableHttpResponse
     * @throws HttpRequestException http request exception
     */
    public CloseableHttpResponse sendPost() throws HttpRequestException {
        // try building up
        try {
            // initialize ssl context builder
            SSLContextBuilder builder = new SSLContextBuilder();

            // set trust self signed strategy
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

            // initialize ssl socket connection factory
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(builder.build());

            // default http client
            CloseableHttpClient client = HttpClients
                .custom()
                .setSSLSocketFactory(sslSocketFactory)
                .build();

            // create request method
            HttpPost post = new HttpPost(this.url);

            // set default user agent
            post.setHeader("User-Agent", this.USER_AGENT);
            // set default content type
            post.setHeader("Content-Type", this.CONTENT_TYPE);

            // convert data to json string
            JSONObject data = new JSONObject(this.data);

            try {
                // set the string entity
                StringEntity entity = new StringEntity(data.toString());

                // set post data
                post.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                // throw exception
                throw new HttpRequestException(e.getMessage());
            }

            // try request
            try {
                // execute request and get the response
                CloseableHttpResponse response = client.execute(post);

                return response;
            } catch (IOException e) {
                // throw an exception
                throw new HttpRequestException(e.getMessage());
            }
        } catch(NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            // throw an exception
            throw new HttpRequestException(e.getMessage());
        }
    }
}
