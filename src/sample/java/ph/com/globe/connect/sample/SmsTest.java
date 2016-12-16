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

import ph.com.globe.connect.ApiException;
import ph.com.globe.connect.HttpRequestException;
import ph.com.globe.connect.HttpResponseException;
import ph.com.globe.connect.Sms;

import org.json.JSONObject;

/**
 * SMS Test.
 * 
 * @author Charles Zamora czamora@openovate.com
 */
public class SmsTest {
    public static void main(String[] args)
        throws ApiException, HttpRequestException, HttpResponseException {
        
        Sms sms = new Sms("21584130", "JO3SpcC-AFiC461wgOxUPDmsOTc5YiMayoK1GnQcduc");
        
        System.out.println("Sending SMS: ");
        
        JSONObject response = sms
            .setClientCorrelator("12345")
            .setReceiverAddress("+639065272450")
            .setMessage("Hello World")
            .sendMessage()
            .getJsonResponse();
    
        System.out.println("Response: -->");
        System.out.println(response.toString());
    }
}
