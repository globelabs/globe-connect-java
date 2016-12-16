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

import ph.com.globe.connect.Authentication;
import ph.com.globe.connect.ApiException;
import ph.com.globe.connect.HttpRequestException;
import ph.com.globe.connect.HttpResponseException;

import org.json.JSONObject;

/**
 * Authentication Test.
 * 
 * @author Charles Zamora czamora@openovate.com
 */
public class AuthenticationTest {
    public static void main(String[] args) 
        throws ApiException, HttpRequestException, HttpResponseException {
        
        String appId = "5ozgSgeRyeHzacXo55TR65HnqoAESbAz";
        String appSecret = "3dbcd598f268268e13550c87134f8de0ec4ac1100cf0a68a2936d07fc9e2459e";
        
        Authentication auth = new Authentication(appId, appSecret);
        
        System.out.println("Get dialog url:");
        
        String dialogUrl = auth.getDialogUrl();
    
        System.out.println(dialogUrl);
        
        System.out.println("Sending Access Token request:");
        System.out.println(auth.getAccessUrl());
        
        JSONObject response = auth
            .getAccessToken("G4HBMexKfaM9E7SG4LpkHRBoLGf9Go6qSnBno8HRKXnes7doqEukgq4bCq59nKfR7KX6Uorknysa8EXyHoxEaRhzGo57tLn4gduLkaE7S9ke9RtpBjgauaeRKpu4RcoX6y4cRaxuGzjkKuyzedXtkra8qSbe47LueyonxtgoEorhpkEoaHLkkResXyKR4U4K996f4EqB7CRLoKGuBjXorsAxnrpH9poqrSAEo6ef7XLGXHyK9R9SLregxfaM6XxH")
            .getJsonResponse();
            
        System.out.println("Response -->");
        System.out.println("Access Token: " + response.getString("access_token"));
        System.out.println("Subscriber Number: " + response.getString("subscriber_number"));
    }
}
