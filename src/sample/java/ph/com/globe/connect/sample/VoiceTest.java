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

import ph.com.globe.connect.voice.enums.Format;
import ph.com.globe.connect.voice.enums.Network;
import ph.com.globe.connect.voice.enums.Recognizer;
import ph.com.globe.connect.voice.enums.Mode;
import ph.com.globe.connect.voice.enums.Channel;
import ph.com.globe.connect.voice.enums.As;
import ph.com.globe.connect.voice.Headers;
import ph.com.globe.connect.voice.StartRecording;
import ph.com.globe.connect.voice.LeavePrompt;
import ph.com.globe.connect.voice.Redirect;
import ph.com.globe.connect.voice.Hangup;
import ph.com.globe.connect.voice.Say;
import ph.com.globe.connect.voice.Choices;
import ph.com.globe.connect.voice.Transcription;
import ph.com.globe.connect.voice.Wait;
import ph.com.globe.connect.voice.MachineDetection;
import ph.com.globe.connect.voice.Reject;
import ph.com.globe.connect.voice.Ask;
import ph.com.globe.connect.voice.JoinPrompt;
import ph.com.globe.connect.voice.Call;
import ph.com.globe.connect.voice.Transfer;
import ph.com.globe.connect.voice.Record;
import ph.com.globe.connect.voice.Message;
import ph.com.globe.connect.voice.Conference;
import ph.com.globe.connect.voice.On;

import static ph.com.globe.connect.voice.Key.*;

/**
 *
 * @author charleszamora
 */
public class VoiceTest {
    public static void main(String[] args) {
        Headers headers = new Headers(
                new String[] {"x-sbc-from", "\"username\"<sip:00001234567@192.168.0.101>;tag=2a648c6e"},
                new String[] {"x-sbc-allow", "BYE"},
                new String[] {"x-sbc-user-agent", "sipgw-1.0"},
                new String[] {"x-sbc-contact", "<sip:00001234567@192.168.0.101:16000>"},
                new String[] {"Content-Length", "247"},
                new String[] {"To", "<sip:9991234567@10.6.60.100:5060>"},
                new String[] {"Contact", "<sip:username@10.6.60.100:5060>"},
                new String[] {"x-sbc-request-uri", "sip:990009369991234567@66.190.50.10:5060"},
                new String[] {"x-sbc-call-id", "OWE0OGFkMTE1ZGY4NTI1MmUzMjc1M2Y3Y2ExMzc2YhG."},
                new String[] {"x-sid", "39f4688b8896f024f3a3aebd0cfb40b2"},
                new String[] {"x-sbc-cseq", "1 INVITE"},
                new String[] {"x-sbc-max-forwards", "70"},
                new String[] {"CSeq", "2 INVITE"},
                new String[] {"Via", "SIP/2.0/UDP 66.190.50.10:5060;received=10.6.60.100"},
                new String[] {"x-sbc-record-route", "<sip:190.40.250.230:5061;r2=on;lr;ftag=2a648c6e>"},
                new String[] {"Call-ID", "0-13c4-4b7d8ff7-1c3c1b82-7935-1d10b081"},
                new String[] {"Content-Type", "application/sdp"},
                new String[] {"x-sbc-to", "<sip:990009369991427645@60.190.50.10:5060>"},
                new String[] {"From", "<sip:username@10.6.60.100:5060>;tag=0-13c4-4b7d8ff7-1c3c1b82-5c7b"}
        );
        
        print(headers.getAction().toString());
        
        Ask ask = new Ask(
            INSTANCE(
                new Choices(
                    VALUE("1"),
                    MODE(Mode.DTMF),
                    TERMINATOR(".")
                )
            ),
            ATTEMPTS(10),
            BARGEIN(false),
            MIN_CONFIDENCE(10),
            NAME("foo"),
            RECOGNIZER(Recognizer.US_ENGLISH),
            REQUIRED(false),
            INSTANCE(
                new Say("Hello World")
            ),
            TIMEOUT(10),
            VOICE(ph.com.globe.connect.voice.enums.Voice.VICTOR),
            INTER_DIGIT_TIMEOUT(10),
            SENSITIVITY(10),
            SPEECH_COMPLETE_TIMEOUT(10),
            SPEECH_INCOMPLETE_TIMEOUT(10)
        );
        
        print(ask.getAction().toString());
        
        Call call = new Call(
            TO("+639065272450"),
            ANSWER_ON_MEDIA(false),
            CHANNEL(Channel.VOICE),
            FROM("234252535"),
            INSTANCE(headers),
            NAME("call"),
            NETWORK(Network.AIM),
            INSTANCE(
                new Record(
                    INSTANCE(new Say(ARRAY(new Say("Hello World!")))),
                    NAME("recording"),
                    URL("http://example.com/recording.js"),
                    INSTANCE(new Choices(TERMINATOR("#")))
                )
            ),
            REQUIRED(true),
            TIMEOUT(10),
            ALLOW_SIGNALS(true),
            INSTANCE(
                new MachineDetection(
                    INTRODUCTION("Hello World!"),
                    VOICE("Victor")
                )
            )
        );
        
        print(call.getAction().toString());
        
        Choices choices = new Choices(
            VALUE("[5 DIGITS]"),
            MODE(Mode.DTMF),
            TERMINATOR("#")
        );
        
        print(choices.getAction().toString());
        
        Conference conference = new Conference(
            ID("12345"),
            MUTE(false),
            NAME("conference"),
            PLAY_TONES(false),
            REQUIRED(false),
            TERMINATOR("#"),
            ALLOW_SIGNALS(false),
            INTER_DIGIT_TIMEOUT(10),
            INSTANCE(new JoinPrompt(VALUE("Please welcome the monster to the party!"))),
            INSTANCE(new LeavePrompt(VALUE("The monster has decide to depart!")))
        );
        
        print(conference.getAction().toString());
        
        Hangup hangup = new Hangup();
        
        print(hangup.getAction().toString());
        
        MachineDetection md = new MachineDetection(
            INTRODUCTION("Hello World"),
            VOICE(ph.com.globe.connect.voice.enums.Voice.ALLISON)
        );
        
        print(md.getAction().toString());
        
        Message message = new Message(
            INSTANCE(new Say("Hello World")),
            TO("+63525544787"),
            ANSWER_ON_MEDIA(false),
            CHANNEL(Channel.TEXT),
            FROM("2542562"),
            NAME("message"),
            NETWORK(Network.JABBER),
            REQUIRED(false),
            TIMEOUT(60),
            VOICE(ph.com.globe.connect.voice.enums.Voice.CARMEN)
        );
        
        print(message.getAction().toString());
        
        On on = new On(
            EVENT("continue"),
            NAME("event"),
            NEXT("http://openovate.com/hello.php"),
            REQUIRED(false),
            INSTANCE(new Say("Hello World")),
            INSTANCE(ask),
            INSTANCE(message),
            INSTANCE(new Wait(MILLISECONDS(3000), ALLOW_SIGNALS(true)))
        );
        
        print(on.getAction().toString());
        
        Record record = new Record(
            ATTEMPTS(10),
            BARGEIN(false),
            BEEP(false),
            INSTANCE(choices),
            FORMAT(Format.MP3),
            MAX_SILENCE(10),
            MAX_TIME(60),
            METHOD("POST"),
            MIN_CONFIDENCE(10),
            NAME("recording"),
            REQUIRED(false),
            INSTANCE(new Say("Hello World")),
            TIMEOUT(10),
            INSTANCE(
                new Transcription(
                    ID("12345"), 
                    URL("https//gmail.com"), 
                    EMAIL_FORMAT("format")
                )
            ),
            URL("http://openovate.com/recording.js"),
            USERNAME("admin"),
            PASSWORD("admin"),
            VOICE(ph.com.globe.connect.voice.enums.Voice.CARLOS),
            ALLOW_SIGNALS(false),
            INTER_DIGIT_TIMEOUT(10)
        );
        
        print(record.getAction().toString());
        
        Redirect redirect = new Redirect(TO("+12345"), NAME("charles"), REQUIRED(false));
        
        print(redirect.getAction().toString());
        
        Reject reject = new Reject();
        
        print(reject.getAction().toString());
        
        Say say = new Say(
            VALUE("Hello World"),
            AS(As.DIGITS),
            NAME("say"),
            REQUIRED(false),
            VOICE(ph.com.globe.connect.voice.enums.Voice.BERNARD),
            ALLOW_SIGNALS(false)
        );
        
        print(say.getAction().toString());
        
        StartRecording sr = new StartRecording(
            FORMAT(Format.MP3),
            METHOD("POST"),
            URL("http://openovate.com/recording.js"),
            USERNAME("admin"),
            PASSWORD("admin"),
            TRANSCRIPTION_ID("12345"),
            TRANSCRIPTION_EMAIL_FORMAT("format"),
            TRANSCRIPTION_OUT_URI("http://openovate.com/recording.js")
        );
        
        print(sr.getAction().toString());
        
        Transfer transfer = new Transfer(
            TO("+123456"),
            ANSWER_ON_MEDIA(false),
            INSTANCE(choices),
            FROM("12345"),
            INSTANCE(headers),
            NAME("transfer"),
            INSTANCE(
                new On(ARRAY(
                    new On(
                        EVENT("ring"),
                        INSTANCE(new Say("http://www.phono.com/audio/holdmusic.mp3"))
                    ),
                    new On(
                        EVENT("connect"),
                        INSTANCE(say)
                    ))
                )
            ),
            REQUIRED(false),
            TERMINATOR("*"),
            TIMEOUT(10),
            ALLOW_SIGNALS(false),
            INTER_DIGIT_TIMEOUT(10),
            INSTANCE(md)
        );
        
        print(transfer.getAction().toString());
        
        Wait wait = new Wait(
            MILLISECONDS(500),
            ALLOW_SIGNALS(true)
        );
        
        print(wait.getAction().toString());
    }
    
    public static void print(String data) {
        System.out.println(new org.json.JSONObject(data).toString(5));
    }
}
