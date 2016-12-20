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

import ph.com.globe.connect.voice.Redirect;
import ph.com.globe.connect.voice.Say;
import ph.com.globe.connect.voice.On;
import ph.com.globe.connect.voice.Reject;
import ph.com.globe.connect.voice.Key;
import ph.com.globe.connect.voice.StartRecording;
import ph.com.globe.connect.voice.Conference;
import ph.com.globe.connect.voice.Wait;
import ph.com.globe.connect.voice.MachineDetection;
import ph.com.globe.connect.voice.LeavePrompt;
import ph.com.globe.connect.voice.Ask;
import ph.com.globe.connect.voice.Hangup;
import ph.com.globe.connect.voice.Transfer;
import ph.com.globe.connect.voice.JoinPrompt;
import ph.com.globe.connect.voice.StopRecording;
import ph.com.globe.connect.voice.Record;
import ph.com.globe.connect.voice.Call;
import ph.com.globe.connect.voice.Message;
import ph.com.globe.connect.voice.Base;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Root class for the Voice API.
 *
 * @author Charles Zamora czamora@openovate.com
 */
public class Voice {
    /* Root json object */
    protected JSONObject rootObject = new JSONObject();

    /* Root json array */
    protected JSONArray rootArray = new JSONArray();

    /**
     * Add an action base on the given
     * action object.
     *
     * @param  base  base class
     * @return this
     */
    public Voice addAction(Base base) {
        // add action
        this.addAction(base, null);

        return this;
    }

    /**
     * Add an action base on the given
     * action object.
     *
     * @param  base  base class
     * @param  root  root key
     * @return this
     */
    public Voice addAction(Base base, String root) {
        // push to our commands
        if(root != null) {
            this.rootArray.put(base.getAction(root));

            return this;
        }

        this.rootArray.put(base.getAction());

        return this;
    }

    /**
     * Initialize an "ask" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice ask(Key... keys) {
        // initialize ask
        Ask ask = new Ask(keys);

        // push to our commands
        this.rootArray.put(ask.getAction());

        return this;
    }

    /**
     * Initialize a "call" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice call(Key... keys) {
        // initialize call
        Call call = new Call(keys);

        // push to our commands
        this.rootArray.put(call.getAction());

        return this;
    }

    /**
     * Initialize a "conference" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice conference(Key... keys) {
        // initialize conference
        Conference conference = new Conference(keys);

        // push to our commands
        this.rootArray.put(conference.getAction());

        return this;
    }

    /**
     * Initialize a "hangup" command.
     *
     * @return this
     */
    public Voice hangup() {
        // initialize hangup
        Hangup hangup = new Hangup();

        // push to our commands
        this.rootArray.put(hangup.getAction());

        return this;
    }

    /**
     * Initialize a "joinPrompt" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice joinPrompt(Key... keys) {
        // initialize join prompt
        JoinPrompt joinPrompt = new JoinPrompt(keys);

        // push to our commands
        this.rootArray.put(joinPrompt.getAction());

        return this;
    }

    /**
     * Initialize a "leavePrompt" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice leavePrompt(Key... keys) {
        // initialize leave prompt
        LeavePrompt leavePrompt = new LeavePrompt(keys);

        // push to our commands
        this.rootArray.put(leavePrompt.getAction());

        return this;
    }

    /**
     * Initialize a "machineDetection" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice machineDetection(Key... keys) {
        // initialize machine detection
        MachineDetection machineDetection = new MachineDetection(keys);

        // push to our commands
        this.rootArray.put(machineDetection.getAction());

        return this;
    }

    /**
     * Initialize a "message" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice message(Key... keys) {
        // initialize message
        Message message = new Message(keys);

        // push to our commands
        this.rootArray.put(message.getAction());

        return this;
    }

    /**
     * Initialize an "on" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice on(Key... keys) {
        // initialize on
        On on = new On(keys);

        // push to our commands
        this.rootArray.put(on.getAction());

        return this;
    }

    /**
     * Initialize a "record" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice record(Key... keys) {
        // initialize record
        Record record = new Record(keys);

        // push to our commands
        this.rootArray.put(record.getAction());

        return this;
    }

    /**
     * Initialize a "redirect" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice redirect(Key... keys) {
        // initialize redirect
        Redirect redirect = new Redirect (keys);

        // push to our commands
        this.rootArray.put(redirect.getAction());

        return this;
    }

    /**
     * Initialize a "reject" command.
     *
     * @return this
     */
    public Voice reject() {
        // initialize reject
        Reject reject = new Reject();

        // push to our commands
        this.rootArray.put(reject.getAction());

        return this;
    }

    /**
     * Initialize a "say" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice say(Key... keys) {
        // initialize say
        Say say = new Say(keys);

        if(say.getAction().getClass().getSimpleName().equals("JSONArray")) {
            JSONObject json = new JSONObject();

            json.put("say", say.getAction());

            // push to our commands
            this.rootArray.put(json);

            return this;
        }

        // push to our commands
        this.rootArray.put(say.getAction());

        return this;
    }

    /**
     * Initialize a "say" command.
     *
     * @param  value say value
     * @return this
     */
    public Voice say(String value) {
        // initialize say
        Say say = new Say(value);

        // push to our commands
        this.rootArray.put(say.getAction());

        return this;
    }

    /**
     * Initialize a "startRecording" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice startRecording(Key... keys) {
        // initialize start recording
        StartRecording startRecording = new StartRecording(keys);

        // push to our commands
        this.rootArray.put(startRecording.getAction());

        return this;
    }

    /**
     * Initialize a "stopRecording" command.
     *
     * @return this
     */
    public Voice stopRecording() {
        // initialize stop recording
        StopRecording stopRecording = new StopRecording();

        // push to our commands
        this.rootArray.put(stopRecording.getAction());

        return this;
    }

    /**
     * Initialize a "transfer" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice transfer(Key... keys) {
        // initialize transfer
        Transfer transfer = new Transfer(keys);

        // push to our commands
        this.rootArray.put(transfer.getAction());

        return this;
    }

    /**
     * Initialize a "wait" command.
     *
     * @param  keys a list of key instances
     * @return this
     */
    public Voice wait(Key... keys) {
        // initialize wait
        Wait wait = new Wait(keys);

        // push to our commands
        this.rootArray.put(wait.getAction());

        return this;
    }

    /**
     * Renders the json object including
     * all the commands in the root object.
     *
     * @return  JSONObject
     */
    public JSONObject render() {
        // set the root tropo
        return this.rootObject.put("tropo", this.rootArray);
    }
}
