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

import java.util.Map;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * AMAX / Rewards Class.
 * 
 * @author Charles Zamora czamora@openovate.com
 */
public class Amax extends Context {
    /* Amax api url */
    private final String AMAX_URL = "https://devapi.globelabs.com.ph/rewards/v1/transactions/send";
    
    /* API app id */
    protected String appId     = null;
    
    /* API app secret */
    protected String appSecret = null;
    
    /* Rewards token */
    protected String rewardsToken = null;
    
    /* Subscriber address */
    protected String address = null;
    
    /* Defined promo */
    protected String promo = null;
    
    /**
     * Create Amax class without paramters.
     */
    public Amax() {
        super();
    }
    
    /**
     * Create Amax class with appId and
     * appSecret parameters.
     * 
     * @param appId app id
     * @param appSecret app secret
     */
    public Amax(String appId, String appSecret) {
        super();
        
        // set app id
        this.appId = appId;
        // set app secret
        this.appSecret = appSecret;
    }
    
    /**
     * Set API app id.
     * 
     * @param  appId app id
     * @return this
     */
    public Amax setAppId(String appId) {
        // set app id
        this.appId = appId;
        
        return this;
    }
    
    /**
     * Set API app secret.
     * 
     * @param appSecret app secret
     * @return this 
     */
    public Amax setAppSecret(String appSecret) {
        // set app secret
        this.appSecret = appSecret;
        
        return this;
    }
    
    /**
     * Set rewards token.
     * 
     * @param  rewardsToken rewards token
     * @return this
     */
    public Amax setRewardsToken(String rewardsToken) {
        // set rewards token
        this.rewardsToken = rewardsToken;
        
        return this;
    }
    
    /**
     * Set subscriber address.
     * 
     * @param  address subscriber address
     * @return this
     */
    public Amax setAddress(String address) {
        // set subscriber address
        this.address = address;
        
        return this;
    }
    
    /**
     * Set defined promo.
     * 
     * @param  promo defined promo
     * @return this
     */
    public Amax setPromo(String promo) {
        // set defined promo
        this.promo = promo;
        
        return this;
    }
    
    /**
     * Send rewards request.
     * 
     * @param  rewardsToken rewards token
     * @param  address subscriber address
     * @param  promo defined promo
     * @return HttpResponse
     * @throws HttpRequestException http request exception
     * @throws HttpResponseException http response exception
     */
    public HttpResponse sendRewardRequest(
        String rewardsToken,
        String address,
        String promo)
        throws HttpRequestException, HttpResponseException {
        
        // set url
        String url = this.AMAX_URL;
        
        // set base data
        Map<String, Object> data = new HashMap<>();
        
        // set outbound reward reqeuest data
        Map<String, Object> orr = new HashMap<>();
        
        // set app id
        orr.put("app_id", this.appId);
        // set app secret
        orr.put("app_secret", this.appSecret);
        // set rewards token
        orr.put("rewards_token", rewardsToken);
        // set address
        orr.put("address", address);
        // set promo
        orr.put("promo", promo);
        
        // set outbound reward request data
        data.put("outboundRewardRequest", orr);
        
        // send request
        CloseableHttpResponse results = this.request
        // set url
        .setUrl(url)
        // set data
        .setData(data)
        // send post request
        .sendPost();
        
        return new HttpResponse(results);
    }
    
    /**
     * Send rewards request.
     * 
     * @return HttpResponse
     * @throws HttpRequestException http request exception
     * @throws HttpResponseException http response exception
     */
    public HttpResponse sendRewardRequest()
        throws HttpRequestException, HttpResponseException {
        
        // call send reward request
        return this.sendRewardRequest(this.rewardsToken, this.address, this.promo);
    }
}
