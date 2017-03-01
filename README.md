
## Globe Connect for Java

### Setting Up


        Install via Maven:
        <!-- https://mvnrepository.com/artifact/ph.com.globe.connect/globe-connect-java -->
        <dependency>
            <groupId>ph.com.globe.connect</groupId>
            <artifactId>globe-connect-java</artifactId>
            <version>0.0.5</version>
        </dependency>

        Install via Gradle:
        // https://mvnrepository.com/artifact/ph.com.globe.connect/globe-connect-java
        compile group: 'ph.com.globe.connect', name: 'globe-connect-java', version: '0.0.5'

        Install via Ivy:
        <!-- https://mvnrepository.com/artifact/ph.com.globe.connect/globe-connect-java -->
        <dependency org="ph.com.globe.connect" name="globe-connect-java" rev="0.0.5"/>
        

### Authentication

#### Overview

If you haven't signed up yet, please follow the instructions found in [Getting Started](http://www.globelabs.com.ph/docs/#getting-started-create-an-app) to obtain an `App ID` and `App Secret` these tokens will be used to validate most of your interaction requests with the Globe APIs.

    The authenication process follows the protocols of **OAuth 2.0**. The example code below shows how you can swap your app tokens for an access token.

#### Sample Code

```java
import ph.com.globe.connect.Authentication;
import org.json.JSONObject;

Authentication auth = new Authentication([app_id], [app_secret]);

String dialogUrl = auth.getDialogUrl();

// redirect the user, process the code then ...

JSONObject response = auth
    .getAccessToken("[code]")
    .getJsonResponse();

System.out.println(response);
```

#### Sample Results

```json
{
    "access_token":"1ixLbltjWkzwqLMXT-8UF-UQeKRma0hOOWFA6o91oXw",
    "subscriber_number":"9171234567"
}
```

### SMS

#### Overview

Short Message Service (SMS) enables your application or service to send and receive secure, targeted text messages and alerts to your Globe / TM subscribers.

        Note: All API calls must include the access_token as one of the Universal Resource Identifier (URI) parameters.

#### SMS Sending

Send an SMS message to one or more mobile terminals:

##### Sample Code

```java
import ph.com.globe.connect.Sms;
import org.json.JSONObject;

Sms sms = new Sms("[short_code]", "[access_token]");

JSONObject response = sms
    .setClientCorrelator("[client_correlator]")
    .setReceiverAddress("[receiver_address]")
    .setMessage("[message]")
    .sendMessage()
    .getJsonResponse();

System.out.println(response);
```

##### Sample Results

```json
{
    "outboundSMSMessageRequest": {
        "address": "tel:+639175595283",
        "deliveryInfoList": {
            "deliveryInfo": [],
            "resourceURL": "https://devapi.globelabs.com.ph/smsmessaging/v1/outbound/8011/requests?access_token=3YM8xurK_IPdhvX4OUWXQljcHTIPgQDdTESLXDIes4g"
        },
        "senderAddress": "8011",
        "outboundSMSTextMessage": {
            "message": "Hello World"
        },
        "receiptRequest": {
            "notifyURL": "http://test-sms1.herokuapp.com/callback",
            "callbackData": null,
            "senderName": null,
            "resourceURL": "https://devapi.globelabs.com.ph/smsmessaging/v1/outbound/8011/requests?access_token=3YM8xurK_IPdhvX4OUWXQljcHTIPgQDdTESLXDIes4g"
        }
    }
}
```

#### SMS Binary

Send binary data through SMS:

##### Sample Code

```java
import ph.com.globe.connect.BinarySms;
import org.json.JSONObject;

BinarySms sms = new BinarySms("[short_code]", "[access_token]");

JSONObject response = sms
    .setUserDataHeader("[data_header]")
    .setDataCodingScheme([coding_scheme])
    .setReceiverAddress("[receiver_address]")
    .setBinaryMessage("[message]")
    .sendBinaryMessage()
    .getJsonResponse();

System.out.println(response);
```

##### Sample Results

```json
{
    "outboundBinaryMessageRequest": {
        "address": "9171234567",
        "deliveryInfoList": {
            "deliveryInfo": [],
            "resourceURL": "https://devapi.globelabs.com.ph/binarymessaging/v1/outbound/{senderAddress}/requests?access_token={access_token}",
        "senderAddress": "21581234",
        "userDataHeader": "06050423F423F4",
        "dataCodingScheme": 1,
        "outboundBinaryMessage": {
            "message": "samplebinarymessage"
        },
        "receiptRequest": {
          "notifyURL": "http://example.com/notify",
          "callbackData": null,
          "senderName": null
        },
        "resourceURL": "https://devapi.globelabs.com.ph/binarymessaging/v1/outbound/{senderAddress}/requests?access_token={access_token}"
    }
}
```

#### SMS Mobile Originating (SMS-MO)

Receiving an SMS from globe (Mobile Originating - Subscriber to Application):

##### Sample Code

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONException;

...

/**
 * Handles the HTTP <code>POST</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    StringBuilder raw = new StringBuilder();

    try {
      BufferedReader reader = request.getReader();
      String line = null;

      while ((line = reader.readLine()) != null) {
        raw.append(line);
      }
    } catch (IOException e) {
        throw new IOException(e.getMessage());
    }

    try {
      JSONObject json =  new JSONObject(raw.toString());

      System.out.println(json.toString(5));
    } catch (JSONException e) {
      throw new IOException("An error occured while parsing json string.");
    }

    processRequest(request, response);
}
```

##### Sample Results

```json
{
  "inboundSMSMessageList":{
      "inboundSMSMessage":[
         {
            "dateTime":"Fri Nov 22 2013 12:12:13 GMT+0000 (UTC)",
            "destinationAddress":"tel:21581234",
            "messageId":null,
            "message":"Hello",
            "resourceURL":null,
            "senderAddress":"9171234567"
         }
       ],
       "numberOfMessagesInThisBatch":1,
       "resourceURL":null,
       "totalNumberOfPendingMessages":null
   }
}
```

### Voice

#### Overview

The Globe APIs has a detailed list of voice features you can use with your application.

#### Voice Ask

You can take advantage of Globe's automated Ask protocols to help service your customers without manual intervention for common questions in example.

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Choices;
import ph.com.globe.connect.voice.Say;

import static ph.com.globe.connect.voice.Key.*;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.say("Welcome to my Tropo Web API.");

    Say say = new Say("Please enter your 5 digit zip code.");
    Choices choices = new Choices("[5 DIGITS]");

    voice.ask(
        INSTANCE(choices),
        ATTEMPTS(3),
        BARGEIN(false),
        NAME("foo"),
        REQUIRED(true),
        INSTANCE(say),
        TIMEOUT(10)
    );

    voice.on(
        EVENT("continue"),
        NEXT("http://somefakehost.com:8000/"),
        REQUIRED(true)
    );

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API."
            }
        },
        {
            ask: {
                choices: {
                    value: "[5 DIGITS]"
                },
                attempts: 3,
                bargein: false,
                name: "foo",
                required: true,
                say: {
                    value: "Please enter your 5 digit zip code."
                },
                timeout: 10
            }
        },
        {
            on: {
                event: "continue",
                next: "http://somefakehost.com:8000/",
                required: true
            }
        }
    ]
}
```

#### Voice Answer

You can take advantage of Globe's automated Ask protocols to help service your customers without manual intervention for common questions in example.

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.say("Welcome to my Tropo Web API.");
    voice.hangup();

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API."
            }
        },
        {
            hangup: { }
        }
    ]
}
```

#### Voice Ask Answer

A better sample of the Ask and Answer dialog would look like the following.

##### Sample Code

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Choices;
import ph.com.globe.connect.voice.Say;
import ph.com.globe.connect.voice.Result;

import static ph.com.globe.connect.voice.Key.*;

import org.json.JSONObject;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.say("Welcome to my Tropo Web API.");

    if("/VoiceSample/AskZipTest".equals(request.getRequestURI())) {
        Say say = new Say("Please enter your 5 digit zip code.");
        Choices choices = new Choices("[5 DIGITS]");

        voice.ask(
            INSTANCE(choices),
            ATTEMPTS(3),
            BARGEIN(false),
            NAME("foo"),
            REQUIRED(true),
            INSTANCE(say),
            TIMEOUT(10)
        );

        voice.on(
            EVENT("continue"),
            NEXT("/VoiceSample/AnswerZipTest"),
            REQUIRED(true)
        );
    } else if("/VoiceSample/AnswerZipTest".equals(request.getRequestURI())) {
        StringBuilder builder = new StringBuilder();
        String line = "";

        try {
           BufferedReader reader = request.getReader();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

        } catch (IOException e) { }

        JSONObject json = new JSONObject(builder.toString());

        // parse result as flat data
        JSONObject result = new Result(json).getResult();

        // get interpretation
        String interpretation = (String) result.get("interpretation");

        voice = new Voice();

        voice.say("Your zip code is " + interpretation + ", thank you!");
    }

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
if path is ask?

{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API."
            }
        },
        {
            ask: {
                choices: {
                    value: "[5 DIGITS]"
                },
                attempts: 3,
                bargein: false,
                name: "foo",
                required: true,
                say: {
                    value: "Please enter your 5 digit zip code."
                },
                timeout: 10
            }
        },
        {
            on: {
                event: "continue",
                next: "/askanswer/answer",
                required: true
            }
        }
    ]
}

if path is answer?

{
    tropo: [
        {
            say: {
                value: "Your zip code is 52521, thank you!"
            }
        }
    ]
}
```

#### Voice Call

You can connect your app to also call a customer to initiate the Ask and Answer features.

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Say;
import static ph.com.globe.connect.voice.Key.*;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.call(
        TO("9065263453"),
        FROM("sip:21584130@sip.tropo.net")
    );

    voice.say(ARRAY(new Say("Hello World")));

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
    tropo: [
        {
            call: {
                to: "9065272450",
                from: "sip:21584130@sip.tropo.net"
            }
        },
        [
            {
                value: "Hello World"
            }
        ]
    ]
}
```

#### Voice Conference

You can take advantage of Globe's automated Ask protocols to help service your customers without manual intervention for common questions in example.

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Conference;
import ph.com.globe.connect.voice.JoinPrompt;
import ph.com.globe.connect.voice.LeavePrompt;
import ph.com.globe.connect.voice.Session;

import static ph.com.globe.connect.voice.Key.*;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.say("Welcome to my Tropo Web API Conference Call.");

    voice.conference(
        ID("12345"),
        MUTE(false),
        NAME("foo"),
        PLAY_TONES(true),
        TERMINATOR("#"),
        INSTANCE(new JoinPrompt(
            VALUE("http://openovate.com/hold-music.mp3")
        )),
        INSTANCE(new LeavePrompt(
            VALUE("http://openovate.com/hold-music.mp3")
        ))
    );

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API Conference Call."
        }
        },
        {
            conference: {
                id: "12345",
                mute: false,
                name: "foo",
                playTones: true,
                terminator: "#",
                joinPrompt: {
                    value: "http://openovate.com/hold-music.mp3"
                },
                leavePrompt: {
                    value: "http://openovate.com/hold-music.mp3"
                }
            }
        }
    ]
}
```

#### Voice Event

Call events are triggered depending on the response of the receiving person. Events are used with the Ask and Answer features.

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Choices;
import ph.com.globe.connect.voice.Say;

import static ph.com.globe.connect.voice.Key.*;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.say("Welcome to my Tropo Web API.");

    Say e1 = new Say(
        VALUE("Sorry, I did not hear anything."),
        EVENT("timeout")
    );

    Say e2 = new Say(
        VALUE("Sorry, that was not a valid option."),
        EVENT("nomatch:1")
    );

    Say e3 = new Say(
        VALUE("Nope, still not a valid response"),
        EVENT("nomatch:2")
    );

    Say say = new Say(
        VALUE("Please enter your 5 digit zip code."),
        ARRAY(e1, e2, e3)
    );

    Choices choices = new Choices("[5 DIGITS]");

    voice.ask(
        INSTANCE(choices),
        ATTEMPTS(3),
        BARGEIN(false),
        NAME("foo"),
        REQUIRED(true),
        INSTANCE(say),
        TIMEOUT(5)
    );

    voice.on(
        EVENT("continue"),
        NEXT("http://somefakehost:8000/"),
        REQUIRED(true)
    );

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
tropo: [
    {
        say: {
            value: "Welcome to my Tropo Web API."
        }
    },
    {
        ask: {
                choices: {
                    value: "[5 DIGITS]"
                },
                attempts: 3,
                bargein: false,
                name: "foo",
                required: true,
                say: [
                    {
                        value: "Sorry, I did not hear anything.",
                        event: "timeout"
                    },
                    {
                        value: "Sorry, that was not a valid option.",
                        event: "nomatch:1"
                    },
                    {
                        value: "Nope, still not a valid response",
                        event: "nomatch:2"
                    },
                    {
                        value: "Please enter your 5 digit zip code."
                    }
                ],
                timeout: 5
            }
        },
        {
            on: {
                event: "continue",
                next: "http://somefakehost:8000/",
                required: true
            }
        }
    ]
}
```

#### Voice Hangup

Between your automated dialogs (Ask and Answer) you can automatically close the voice call using this feature. 

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.say("Welcome to my Tropo Web API, thank you!");
    voice.hangup();

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API, thank you!"
            }
        },
        {
            hangup: { }
        }
    ]
}
```

#### Voice Record

It is helpful to sometime record conversations, for example to help improve on the automated dialog (Ask and Answer). The following sample shows how you can use connect your application with voice record features.

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Choices;
import ph.com.globe.connect.voice.Say;
import ph.com.globe.connect.voice.Transcription;
import ph.com.globe.connect.voice.enums.*;

import static ph.com.globe.connect.voice.Key.*;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.say("Welcome to my Tropo Web API.");

    Say timeout = new Say(
        VALUE("Sorry, I did not hear anything. Please call back."),
        EVENT("timeout")
    );

    Say say = new Say(VALUE("Please leave a message"), ARRAY(timeout));

    Choices choices = new Choices(TERMINATOR("#"));

    Transcription transcription = new Transcription(
        ID("1234"),
        URL("mailto:charles.andacc@gmail.com")
    );

    voice.record(
        ATTEMPTS(3),
        BARGEIN(false),
        METHOD("POST"),
        REQUIRED(true),
        INSTANCE(say),
        NAME("foo"),
        URL("http://openovate.com/globe.php"),
        FORMAT(Format.WAV),
        INSTANCE(choices),
        INSTANCE(transcription)
    );

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API."
            }
        },
        {
            record: {
                attempts: 3,
                bargein: false,
                method: "POST",
                required: true,
                say: [
                    {
                        value: "Sorry, I did not hear anything. Please call back.",
                        event: "timeout"
                    },
                    {
                        value: "Please leave a message"
                    }
                ],
                name: "foo",
                url: "http://openovate.com/globe.php",
                format: "audio/wav",
                choices: {
                    terminator: "#"
                },
                transcription: {
                    id: "1234",
                    url: "mailto:charles.andacc@gmail.com"
                }
            }
        }
    ]
}
```

#### Voice Reject

To filter incoming calls automatically, you can use the following example below. 

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.reject();

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
    tropo: [
        {
            reject: { }
        }
    ]
}
```

#### Voice Routing

To help integrate Globe Voice with web applications, this API using routing which can be easily routed within your framework.

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import static ph.com.globe.connect.voice.Key.*;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    String path = request.getRequestURI();

    Voice voice = new Voice();

    if("/VoiceSample/RoutingTest".equals(path)) {
        voice.say("Welcome to my Tropo Web API.");
        voice.on(
            EVENT("continue"),
            NEXT("/VoiceSample/RoutingTest1")
        );
    } else if("/VoiceSample/RoutingTest1".equals(path)) {
        voice.say("Hello from resource one!");
        voice.on(
            EVENT("continue"),
            NEXT("/VoiceSample/RoutingTest2")
        );
    } else if("/VoiceSample/RoutingTest2".equals(path)) {
        voice.say("Hello from resource two! thank you.");
    }

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
if path is routing?

{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API."
            }
        },
        {
            on: {
                next: "/VoiceSample/RoutingTest1",
                event: "continue"
            }
        }
    ]
}

if path is routing1?

{
    tropo: [
        {
            say: {
                value: "Hello from resource one!"
            }
        },
        {
            on: {
                next: "/VoiceSample/RoutingTest2",
                event: "continue"
            }
        }
    ]
}

if path is routing2?

{
    tropo: [
        {
            say: {
                value: "Hello from resource two! thank you."
            }
        }
    ]
}
```

#### Voice Say

The message you pass to `say` will be transformed to an automated voice.

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import static ph.com.globe.connect.voice.Key.*;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.say("Welcome to my Tropo Web API.");
    voice.say("I will play an audio file for you, please wait.");
    voice.say(
        VALUE("http://openovate.com/tropo-rocks.mp3")
    );

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API."
            }
        },
        {
            say: {
                value: "I will play an audio file for you, please wait."
            }
        },
        {
            say: {
                value: "http://openovate.com/tropo-rocks.mp3"
            }
        }
    ]
}
```

#### Voice Transfer

The following sample explains the dialog needed to transfer the receiver to another phone number.

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Ask;
import ph.com.globe.connect.voice.Choices;
import ph.com.globe.connect.voice.Say;
import ph.com.globe.connect.voice.Transfer;
import ph.com.globe.connect.voice.On;
import ph.com.globe.connect.voice.enums.*;
import static ph.com.globe.connect.voice.Key.*;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.say("Welcome to my Tropo Web API, you are now being transferred.");

    Say e1 = new Say(
        VALUE("Sorry, I did not hear anything."),
        EVENT("timeout")
    );

    Say e2 = new Say(
        VALUE("Sorry, that was not a valid option."),
        EVENT("nomatch:1")
    );

    Say e3 = new Say(
        VALUE("Nope, still not a valid response"),
        EVENT("nomatch:2")
    );

    Say say = new Say(
        VALUE("Please enter your 5 digit zip code."),
        ARRAY(e1, e2, e3)
    );

    Choices choices = new Choices("[5 DIGITS]");

    Ask ask = new Ask(
        INSTANCE(choices),
        ATTEMPTS(3),
        BARGEIN(false),
        NAME("foo"),
        REQUIRED(true),
        INSTANCE(say),
        TIMEOUT(5)
    );

    On ring = new On(
        EVENT("ring"),
        INSTANCE(new Say("http://openovate.com/hold-music.mp3"))
    );

    On connect = new On(
       EVENT("connect"),
       INSTANCE(ask)
    );

    On on = new On(ARRAY(ring, connect));

    voice.transfer(
        TO("9053801178"),
        RING_REPEAT(2),
        INSTANCE(on)
    );

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API, you are now being transferred."
            }
        },
        {
            transfer: {
                to: "9053801178",
                ringRepeat: 2,
                on: [
                    {
                        event: "ring",
                        say: {
                            value: "http://openovate.com/hold-music.mp3"
                        }
                    },
                    {
                        event: "connect",
                        ask: {
                            choices: {
                                value: "[5 DIGITS]"
                            },
                            attempts: 3,
                            bargein: false,
                            name: "foo",
                            required: true,
                            say: [
                                {
                                    value: "Sorry, I did not hear anything.",
                                    event: "timeout"
                                },
                                {
                                    value: "Sorry, that was not a valid option.",
                                    event: "nomatch:1"
                                },
                                {
                                    value: "Nope, still not a valid response",
                                    event: "nomatch:2"
                                },
                                {
                                    value: "Please enter your 5 digit zip code."
                                }
                            ],
                            timeout: 5
                        }
                    }
                ]
            }
        }
    ]
}
```

#### Voice Transfer Whisper

TODO

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import ph.com.globe.connect.voice.Ask;
import ph.com.globe.connect.voice.Choices;
import ph.com.globe.connect.voice.Say;
import ph.com.globe.connect.voice.Transfer;
import ph.com.globe.connect.voice.On;
import ph.com.globe.connect.voice.enums.*;
import static ph.com.globe.connect.voice.Key.*;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    if("/VoiceSample/TransferWhisperTest".equals(request.getRequestURI())) {
        voice.say("Welcome to my Tropo Web API, please hold while you are being transferred.");

        Say say = new Say("Press 1 to accept this call or any other number to reject");

        Choices choices = new Choices(
            VALUE("1"),
            MODE(Mode.DTMF)
        );

        Ask ask = new Ask(
            INSTANCE(choices),
            NAME("color"),
            INSTANCE(say),
            TIMEOUT(60)
        );

        On connect1 = new On(
            EVENT("connect"),
            INSTANCE(ask)
        );

        On connect2 = new On(
            EVENT("connect"),
            INSTANCE(new Say("You are now being connected."))
        );

        On ring = new On(
            EVENT("ring"),
            INSTANCE(new Say("http://openovate.com/hold-music.mp3"))
        );

        On connect = new On(ARRAY(ring, connect1, connect2));

        voice.transfer(
            TO("9054799241"),
            NAME("foo"),
            INSTANCE(connect),
            REQUIRED(true),
            TERMINATOR("*")
        );

        voice.on(
            EVENT("incomplete"),
            NEXT("/VoiceSample/TransferWhisperHangupTest"),
            INSTANCE(new Say("You are now being disconnected."))
        );
    } else if("/VoiceSample/TransferWhisperHangupTest".equals(request.getRequestURI())) {
        voice.hangup();
    }

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
if transfer whisper?

{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API, please hold while you are being transferred."
            }
        },
        {
            transfer: {
                to: "9054799241",
                name: "foo",
                on: [
                    {
                        event: "ring",
                        say: {
                            value: "http://openovate.com/hold-music.mp3"
                        }
                    },
                    {
                        event: "connect",
                        ask: {
                            choices: {
                                value: "1",
                                mode: "dtmf"
                            },
                            name: "color",
                            say: {
                                value: "Press 1 to accept this call or any other number to reject"
                            },
                            timeout: 60
                        }
                    },
                    {
                        event: "connect",
                        say: {
                            value: "You are now being connected."
                        }
                    }
                ],
                required: true,
                terminator: "*"
            }
        },
        {
            on: {
                event: "incomplete",
                next: "/transferwhisper/hangup",
                say: {
                    value: "You are now being disconnected."
                }
            }
        }
    ]
}

if hangup?

{
    tropo: [
        {
            hangup: { }
        }
    ]
}
```

#### Voice Wait

To put a receiver on hold, you can use the following sample.

##### Sample Code

```java
import java.io.IOException;
import java.io.PrintWriter;

import ph.com.globe.connect.Voice;
import static ph.com.globe.connect.voice.Key.*;

...

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("application/json;charset=UTF-8");

    Voice voice = new Voice();

    voice.say("Welcome to my Tropo Web API, please wait for a while.");
    voice.wait(MILLISECONDS(5000), ALLOW_SIGNALS(true));
    voice.say("Thank you for waiting!");

    try (PrintWriter out = response.getWriter()) {
        out.println(voice.render());
    }
}
```

##### Sample Results

```json
{
    tropo: [
        {
            say: {
                value: "Welcome to my Tropo Web API, please wait for a while."
            }
        },
        {
            wait: {
                milliseconds: 5000,
                allowSignals: true
            }
        },
        {
            say: {
                value: "Thank you for waiting!"
            }
        }
    ]
}
```

### USSD

#### Overview

USSD are basic features built on most smart phones which allows the phone owner to interact with menu item choices.

#### USSD Sending

The following example shows how to send a USSD request.

##### Sample Code

```java
import ph.com.globe.connect.Ussd;
import org.json.JSONObject;

Ussd ussd = new Ussd("[access_token]");

JSONObject response = ussd
    .setSenderAddress("[short_code]")
    .setUssdMessage("[message]")
    .setAddress("[subscriber_number]")
    .setFlash([flash])
    .sendUssdRequest()
    .getJsonResponse();

System.out.println(response);
```

##### Sample Results

```json
{
    "outboundUSSDMessageRequest": {
        "address": "639954895489",
        "deliveryInfoList": {
            "deliveryInfo": [],
            "resourceURL": "https://devapi.globelabs.com.ph/ussd/v1/outbound/21589996/reply/requests?access_token=access_token"
        },
        "senderAddress": "21580001",
        "outboundUSSDMessage": {
            "message": "Simple USSD Message\nOption - 1\nOption - 2"
        },
        "receiptRequest": {
            "ussdNotifyURL": "http://example.com/notify",
            "sessionID": "012345678912"
        },
        "resourceURL": "https://devapi.globelabs.com.ph/ussd/v1/outbound/21589996/reply/requests?access_token=access_token"
    }
}
```

#### USSD Replying

The following example shows how to send a USSD reply.

##### Sample Code

```java
import ph.com.globe.connect.Ussd;
import org.json.JSONObject;

Ussd ussd = new Ussd("[access_token]");

JSONObject response = ussd
    .setSessionId([session_id])
    .setAddress("[subscriber_number]")
    .setSenderAddress("[short_code]")
    .setUssdMessage("[message]")
    .setFlash([flash])
    .replyUssdRequest()
    .getJsonResponse();

System.out.println(response);
```

##### Sample Results

```json
{
    "outboundUSSDMessageRequest": {
        "address": "639954895489",
        "deliveryInfoList": {
            "deliveryInfo": [],
            "resourceURL": "https://devapi.globelabs.com.ph/ussd/v1/outbound/21589996/reply/requests?access_token=access_token"
        },
        "senderAddress": "21580001",
        "outboundUSSDMessage": {
            "message": "Simple USSD Message\nOption - 1\nOption - 2"
        },
        "receiptRequest": {
            "ussdNotifyURL": "http://example.com/notify",
            "sessionID": "012345678912",
            "referenceID": "f7b61b82054e4b5e"
        },
        "resourceURL": "https://devapi.globelabs.com.ph/ussd/v1/outbound/21589996/reply/requests?access_token=access_token"
    }
}
```

### Payment

#### Overview

Your application can monetize services from customer's phone load by sending a payment request to the customer, in which they can opt to accept.

#### Payment Requests

The following example shows how you can request for a payment from a customer.

##### Sample Code

```java
import ph.com.globe.connect.Payment;
import org.json.JSONObject;

Payment payment = new Payment("[access_token]");

JSONObject response = payment
    .setAmount([amount])
    .setDescription("[description]")
    .setEndUserId("[subscriber_number]")
    .setReferenceCode("[reference]")
    .setTransactionOperationStatus("[status]")
    .sendPaymentRequest()
    .getJsonResponse();

System.out.println(response);
```

##### Sample Results

```json
{
    "amountTransaction":
    {
        "endUserId": "9171234567",
        "paymentAmount":
        {
            "chargingInformation":
            {
                "amount": "0.00",
                "currency": "PHP",
                "description": "my application"
            },
            "totalAmountCharged": "0.00"
        },
        "referenceCode": "12341000023",
        "serverReferenceCode": "528f5369b390e16a62000006",
        "resourceURL": null
    }
}
```

#### Payment Last Reference

The following example shows how you can get the last reference of payment.

##### Sample Code

```java
import ph.com.globe.connect.Payment;
import org.json.JSONObject;

Payment payment = new Payment("[access_token]");

JSONObject response = payment
    .setAppId("[app_id]")
    .setAppSecret("[app_secret]")
    .getLastReferenceCode()
    .getJsonResponse();

System.out.println(response);
```

##### Sample Results

```json
{
    "referenceCode": "12341000005",
    "status": "SUCCESS",
    "shortcode": "21581234"
}
```

### Amax

#### Overview

Amax is an automated promo builder you can use with your app to award customers with certain globe perks.

#### Sample Code

```java
import ph.com.globe.connect.Amax;
import org.json.JSONObject;

Amax amax = new Amax([app_id], [app_secret]);

JSONObject response = amax
    .setRewardsToken("[rewards_token]")
    .setAddress("[subscriber_number]")
    .setPromo("[promo]")
    .sendRewardRequest()
    .getJsonResponse();

System.out.println(response);
```

#### Sample Results

```json
{
    "outboundRewardRequest": {
        "transaction_id": 566,
        "status": "Please check your AMAX URL for status",
        "address": "9065272450",
        "promo": "FREE10MB"
    }
}
```

### Location

#### Overview

To determine a general area (lat,lng) of your customers you can utilize this feature.

#### Sample Code

```java
import ph.com.globe.connect.Location;
import org.json.JSONObject;

Location location = new Location("[access_token]");

JSONObject response = location
    .setAddress("[subscriber_number]")
    .setRequestedAccuracy([accuracy])
    .getLocation()
    .getJsonResponse();

System.out.println(response);
```

#### Sample Results

```json
{
    "terminalLocationList": {
        "terminalLocation": {
            "address": "tel:9171234567",
            "currentLocation": {
                "accuracy": 100,
                "latitude": "14.5609722",
                "longitude": "121.0193394",
                "map_url": "http://maps.google.com/maps?z=17&t=m&q=loc:14.5609722+121.0193394",
                "timestamp": "Fri Jun 06 2014 09:25:15 GMT+0000 (UTC)"
            },
            "locationRetrievalStatus": "Retrieved"
        }
    }
}
```

### Subscriber

#### Overview

Subscriber Data Query API interface allows a Web application to query the customer profile of an end user who is the customer of a mobile network operator.

#### Subscriber Balance

The following example shows how you can get the subscriber balance.

##### Sample Code

```java
import ph.com.globe.connect.Subscriber;
import org.json.JSONObject;

Subscriber subscriber = new Subscriber("[access_token]");

JSONObject response = subscriber
    .setAddress("[subscriber_number]")
    .getSubscriberBalance()
    .getJsonResponse();

System.out.println(response);
```

##### Sample Results

```json
{
    "terminalLocationList":
    {
        "terminalLocation":
        [
            {
                address: "639171234567",
                subBalance: "60200"
            }
        ]
    }
}
```

#### Subscriber Reload

The following example shows how you can get the subscriber reload amount.

##### Sample Code

```java
import ph.com.globe.connect.Subscriber;
import org.json.JSONObject;

Subscriber subscriber = new Subscriber("[access_token]");

JSONObject response = subscriber
    .setAddress("[subscriber_number]")
    .getSubscriberReloadAmount()
    .getJsonResponse();

System.out.println(response);
```

##### Sample Results

```json
{
    "terminalLocationList":
    {
        "terminalLocation":
        [
            {
                address: "639171234567",
                reloadAmount: "30000"
            }
        ]
    }
}
```
