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
 * Base class for all the voice objects.
 *
 * @author Charles Zamora czamora@openovate.com
 */
public class Base extends JSONObject {
    /**
     * Sets the key value pair.
     *
     * @param keys list of key instance
     */
    public Base(Key... keys) {
        // iterate on each keys
        for(Key key : keys) {
            // set key and value pair
            this.put(key.getName(), key.getValue());
        }
    }

    /**
     * Returns the action key value
     * pair without the root key.
     *
     * @return Object
     */
    public Object getAction() {
        return this.getAction(null);
    }

    /**
     * Returns the action key value
     * pair with the given root key.
     *
     * @param  root root key
     * @return Object
     */
    public Object getAction(String root) {
        // if root key is not set
        if(root == null) {
            // get instance name
            String instance = this.getClass().getSimpleName();

            // replace first char
            instance = instance.substring(0, 1).toLowerCase() + instance.substring(1);

            // get the class name
            root = instance;
        }

        // return the instance as array?
        if(this.has("fromArray")) {
            // get the target array
            return this.get(this.getString("fromArray"));
        }

        return new JSONObject().put(root, this);
    }

    /**
     * Removes unnecessary fields.
     *
     * @return String
     */
    @Override
    public String toString() {
        // remove unnecessary field
        this.remove("fromArray");

        return super.toString();
    }
}
