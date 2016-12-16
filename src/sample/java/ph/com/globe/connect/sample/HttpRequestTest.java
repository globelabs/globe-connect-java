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
package ph.com.globe.connect.sample;

import ph.com.globe.connect.HttpRequest;
import ph.com.globe.connect.HttpResponse;

import java.util.Map;
import java.util.HashMap;

import org.json.JSONObject;

/**
 * HttpRequest Test.
 * 
 * @author Charles Zamora czamora@openovate.com
 */
public class HttpRequestTest {
    public static void main(String[] args) throws Exception {
        HttpRequest request = new HttpRequest();
        
        System.out.println("Sending GET request to: http://www.mocky.io/v2/5185415ba171ea3a00704eed");
        
        org.apache.http.client.methods.CloseableHttpResponse getRequest = request
            .setUrl("http://www.mocky.io/v2/5185415ba171ea3a00704eed")
            .sendGet();
        
        JSONObject getResponse = new HttpResponse(getRequest).getJsonResponse();
        
        System.out.println("Response: -->");
        System.out.println(getResponse.toString());
        
        Map<String, Object> data = new HashMap<>();
        
        data.put("hello", "world");
        
        System.out.println("Sending POST Request to: http://www.mocky.io/v2/5185415ba171ea3a00704eed");
        
        HttpRequest postRequest = request
            .setUrl("http://www.mocky.io/v2/5185415ba171ea3a00704eed")
            .setData(data);
        
        System.out.println("Post Data: " + postRequest.getDataAsJson().toString());
        
        JSONObject postResponse = new HttpResponse(postRequest.sendPost()).getJsonResponse();
        
        System.out.println("Response: -->");
        System.out.println(postResponse.toString());
    }
}
