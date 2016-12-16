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

import ph.com.globe.connect.voice.enums.Network;
import ph.com.globe.connect.voice.enums.Format;
import ph.com.globe.connect.voice.enums.Mode;
import ph.com.globe.connect.voice.enums.Channel;
import ph.com.globe.connect.voice.enums.Recognizer;
import ph.com.globe.connect.voice.enums.Voice;
import ph.com.globe.connect.voice.enums.As;

import java.util.ArrayList;
import org.json.JSONArray;

/**
 * Dynamic key helper, most of the parts
 * of this class are based off of the original
 * java sample from Tropo API.
 * 
 * @author Charles Zamora czamora@openovate.com
 */
public class Key {
    /* Key name */
    private String name = null;
    
    /* Key value */
    private Object value = null;
    
    /**
     * Create key with the given name.
     * 
     * @param name 
     */
    private Key(String name) {
        // set key name
        this.name = name;
    }
    
    /**
     * Creates an AS key.
     * 
     * @param  as
     * @return this
     */
    public static Key AS(As as) {
        return createKey("as", as);
    }
    
    /**
     * Creates an ALLOW_SIGNAL key.
     * 
     * @param  allowSignals
     * @return this
     */
    public static Key ALLOW_SIGNALS(boolean allowSignals) {
        return createKey("allowSignals", allowSignals);
    }
    
    /**
     * Creates an ATTEMPTS key.
     * 
     * @param  attempts
     * @return this
     */
    public static Key ATTEMPTS(int attempts) {
        return createKey("attempts", attempts);
    }
    
    /**
     * Creates an ARRAY key.
     * 
     * @param  objects
     * @return this
     */
    public static Key ARRAY(Object... objects) {
        // create new array
        ArrayList<Object> lists = new ArrayList<>();
    
        // iterate on each object
        for(Object object : objects) {
            lists.add(object);
        }
        
        // create key
        return createKey("array", new JSONArray(lists));
    }
    
    /**
     * Creates an ANSWER_ON_MEDIA key.
     * 
     * @param  answerOnMedia
     * @return this
     */
    public static Key ANSWER_ON_MEDIA(boolean answerOnMedia) {
        return createKey("answerOnMedia", answerOnMedia);
    } 
    
    /**
     * Creates an ASYNC_UPLOAD key.
     * 
     * @param  asyncUpload
     * @return this
     */
    public static Key ASYNC_UPLOAD(boolean asyncUpload) {
        return createKey("asyncUpload", asyncUpload);
    }
    
    /**
     * Creates a BARGEIN key.
     * 
     * @param  bargein
     * @return this
     */
    public static Key BARGEIN(boolean bargein) {
        return createKey("bargein", bargein);
    }
    
    /**
     * Creates a BEEP key.
     * 
     * @param  beep
     * @return this
     */
    public static Key BEEP(boolean beep) {
        return createKey("beep", beep);
    }
    
    /**
     * Creates a CHANNEL key.
     * 
     * @param  channel
     * @return this
     */
    public static Key CHANNEL(Channel channel) {
        return createKey("channel", channel);
    }
    
    /**
     * Creates a CALLER_ID key.
     * 
     * @param  callerId
     * @return this
     */
    public static Key CALLER_ID(String callerId) {
        return createKey("callerID", callerId);
    }
    
    /**
     * Creates an EMAIL_FORMAT key.
     * 
     * @param  emailFormat
     * @return this
     */
    public static Key EMAIL_FORMAT(String emailFormat) {
        return createKey("emailFormat", emailFormat);
    }
    
    /**
     * Creates an EVENT key.
     * 
     * @param  event
     * @return this
     */
    public static Key EVENT(String event) {
        return createKey("event", event);
    }
    
    /**
     * Creates a FROM key.
     * 
     * @param  from
     * @return this
     */
    public static Key FROM(String from) {
        return createKey("from", from);
    }
    
    /**
     * Creates a FORMAT key.
     * 
     * @param  format
     * @return this
     */
    public static Key FORMAT(Format format) {
        return createKey("format", format);
    }
    
    /**
     * Creates an ID key.
     * 
     * @param  value
     * @return this
     */
    public static Key ID(String value) {
        return createKey("id", value);
    }
    
    /**
     * Creates an INSTANCE key.
     * 
     * @param  base
     * @return this
     */
    public static Key INSTANCE(Base base) {
        // get instance name
        String instance = base.getClass().getSimpleName();
        
        // replace first char
        instance = instance.substring(0, 1).toLowerCase() + instance.substring(1);
        
        // return from instance from a key
        // with a value of array?
        if(base.has("fromArray")) {
            // create key from array
            return createKey(instance, base.get(base.getString("fromArray")));
        }
        
        return createKey(instance, base);
    }
    
    /**
     * Creates a INTER_DIGIT_TIMEOUT key.
     * 
     * @param  timeout
     * @return this
     */
    public static Key INTER_DIGIT_TIMEOUT(int timeout) {
        return createKey("interdigitTimeout", timeout);
    }
    
    /**
     * Creates a INTRODUCTION key.
     * 
     * @param  introduction
     * @return this
     */
    public static Key INTRODUCTION(String introduction) {
        return createKey("introduction", introduction);
    }
    
    /**
     * Creates a custom key.
     * 
     * @param  key
     * @param  value
     * @return this
     */
    public static Key KEY(String key, Object value) {
        return createKey(key, value);
    }
    
    /**
     * Creates a MAX_SILENCE key.
     * 
     * @param  maxSilence
     * @return this
     */
    public static Key MAX_SILENCE(float maxSilence) {
        return createKey("maxSilence", maxSilence);
    }
    
    /**
     * Creates a MAX_TIME key.
     * 
     * @param  maxTime
     * @return this
     */
    public static Key MAX_TIME(float maxTime) {
        return createKey("maxTime", maxTime);
    }
    
    /**
     * Creates a METHOD key.
     * 
     * @param  method
     * @return this
     */
    public static Key METHOD(String method) {
        return createKey("method", method);
    }
    
    /**
     * Creates a MILLISECONDS key.
     * 
     * @param  milliseconds
     * @return this
     */
    public static Key MILLISECONDS(float milliseconds) {
        return createKey("milliseconds", milliseconds);
    }
    
    /**
     * Creates a MIN_CONFIDENCE key.
     * 
     * @param   confidence
     * @return  this
     */
    public static Key MIN_CONFIDENCE(int confidence) {
        return createKey("minConfidence", confidence);
    }
    
    /**
     * Creates a MODE key.
     * 
     * @param  mode
     * @return this
     */
    public static Key MODE(Mode mode) {
        return createKey("mode", mode);
    }
    
    /**
     * Creates a MUTE key.
     * 
     * @param  mute
     * @return this
     */
    public static Key MUTE(boolean mute) {
        return createKey("mute", mute);
    }
    
    /**
     * Creates a NEXT key.
     * 
     * @param  next
     * @return this
     */
    public static Key NEXT(String next) {
        return createKey("next", next);
    }
    
    /**
     * Creates a NETWORK key.
     * 
     * @param  network
     * @return this
     */
    public static Key NETWORK(Network network) {
        return createKey("network", network);
    }
    
    /**
     * Creates a NAME key.
     * 
     * @param  name
     * @return this
     */
    public static Key NAME(String name) {
        return createKey("name", name);
    }
    
    /**
     * Creates a PASSWORD key.
     * 
     * @param  password
     * @return this
     */
    public static Key PASSWORD(String password) {
        return createKey("password", password);
    }
    
    /**
     * Creates a PLAY_TONES key.
     * 
     * @param  playTones
     * @return this
     */
    public static Key PLAY_TONES(boolean playTones) {
        return createKey("playTones", playTones);
    }
    
    /**
     * Creates a RECOGNIZER key.
     * 
     * @param  recognizer
     * @return this
     */
    public static Key RECOGNIZER(String recognizer) {
        return createKey("recognizer", recognizer);
    }
    
    /**
     * Creates a RECOGNIZER key.
     * 
     * @param  recognizer
     * @return this
     */
    public static Key RECOGNIZER(Recognizer recognizer) {
        return createKey("recognizer", recognizer);
    }
    
    /**
     * Creates a REQUIRED key.
     * 
     * @param  required
     * @return this
     */
    public static Key REQUIRED(boolean required) {
        return createKey("required", required);
    }
    
    /**
     * Creates a RING_REPEAT key.
     * 
     * @param  ringRepeat
     * @return this
     */
    public static Key RING_REPEAT(int ringRepeat) {
        return createKey("ringRepeat", ringRepeat);
    }
    
    /**
     * Creates a SENSITIVITY key.
     * 
     * @param  sensitivity
     * @return this
     */
    public static Key SENSITIVITY(float sensitivity) {
        return createKey("sensitivity", sensitivity);
    }
    
    /**
     * Creates a SPEECH_COMPLETE_TIMEOUT key.
     * 
     * @param  timeout
     * @return this
     */
    public static Key SPEECH_COMPLETE_TIMEOUT(int timeout) {
        return createKey("speechCompleteTimeout", timeout);
    }
    
    /**
     * Creates a SPEECH_INCOMPLETE_TIMEOUT key.
     * 
     * @param  timeout
     * @return this
     */
    public static Key SPEECH_INCOMPLETE_TIMEOUT(int timeout) {
        return createKey("speechIncompleteTimeout", timeout);
    }
    
    /**
     * Creates a TIMEOUT key.
     *
     * @param  timeout
     * @return this
     */
    public static Key TIMEOUT(float timeout) {
        return createKey("timeout", timeout);
    }
    
    /**
     * Creates a TERMINATOR key.
     * 
     * @param  terminator
     * @return this
     */
    public static Key TERMINATOR(String terminator) {
        return createKey("terminator", terminator);
    }
    
    /**
     * Creates a TO key.
     * 
     * @param  to
     * @return this
     */
    public static Key TO(String to) {
        return createKey("to", to);
    }
    
    /**
     * Creates a TO key.
     * 
     * @param  to
     * @return this
     */
    public static Key TO(String... numbers) {
        JSONArray to = new JSONArray();
        
        for(String num : numbers) {
            to.put(num);
        }
        
        return createKey("to", to);
    }
    
    /**
     * Creates TRANSCRIPTION_ID key.
     * 
     * @param  transcriptionId
     * @return this
     */
    public static Key TRANSCRIPTION_ID(String transcriptionId) {
        return createKey("transcriptionID", transcriptionId);
    }
    
    /**
     * Creates TRANSCRIPTION_EMAIL_FORMAT key.
     * 
     * @param  transcriptionEmailFormat
     * @return this
     */
    public static Key TRANSCRIPTION_EMAIL_FORMAT(String transcriptionEmailFormat) {
        return createKey("transacriptionEmailFormat", transcriptionEmailFormat);
    }
    
    /**
     * Creates TRANSCRIPTION_OUT_URI key.
     * 
     * @param  transcriptionOutUri
     * @return this
     */
    public static Key TRANSCRIPTION_OUT_URI(String transcriptionOutUri) {
        return createKey("transacriptionOutURI", transcriptionOutUri);
    }
    
    /**
     * Creates a URL key.
     * 
     * @param  url
     * @return this
     */
    public static Key URL(String url) {
        return createKey("url", url);
    }
    
    /**
     * Creates a USERNAME key.
     * 
     * @param  username
     * @return this
     */
    public static Key USERNAME(String username) {
        return createKey("username", username);
    }
    
    /**
     * Creates a VALUE key.
     * 
     * @param  value
     * @return this
     */
    public static Key VALUE(String value) {
        return createKey("value", value);
    }
    
    /**
     * Creates a VOICE key.
     * 
     * @param  voice
     * @return this
     */
    public static Key VOICE(Voice voice) {
        return createKey("voice", voice);
    }
    
    /**
     * Creates a VOICE key.
     * 
     * @param  voice
     * @return this
     */
    public static Key VOICE(String voice) {
        return createKey("voice", voice);
    }
    
    /**
     * Create a key using the 
     * given name and value.
     * 
     * @param  name
     * @param  value
     * @return this
     */
    private static Key createKey(String name, Object value) {
        // is it enum?
        if(value instanceof Enum) {
            value = value.toString();
        }
        
        // create new key
        Key key = new Key(name);
        
        // set value
        key.value = value;
        
        return key;
    }
    
    /**
     * Returns the key name.
     * 
     * @return String 
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the key value.
     * 
     * @return Object 
     */
    public Object getValue() {
        return this.value;
    }
}
