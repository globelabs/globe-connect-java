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
package ph.com.globe.connect.voice;

import org.json.JSONObject;

/**
 * Say voice command class.
 * 
 * @author Charles Zamora czamora@openovate.com
 */
public class Say extends Base {
    public Say(String value) {
        super(Key.VALUE(value));
    }
    
    public Say(Key key) {
        if("array".equals(key.getName())) {
            super.put("fromArray", "array");
            
            super.put(key.getName(), key.getValue());
        }
    }
    
    public Say(Key... keys) {
        super(keys);
        
        for(Key key : keys) {
            if("array".equals(key.getName())) {
                super.put("fromArray", "array");
                
                String original = null;
                
                for(Key k : keys) {
                    if("value".equals(k.getName())) {
                        original = (String) k.getValue();
                        
                         super
                        .getJSONArray("array")
                        .put(new JSONObject()
                            .put("value", original)
                        );
                         
                        break;
                    }
                }
            }
        }
    }
}
