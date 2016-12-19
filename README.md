# Globe Connect for Java

## Introduction
Globe Connect for Java provides an implementation of Globe APIs e.g Authentication, Amax,
Sms etc. that is easy to use and can be integrated in your existing Java application. Below shows
some samples on how to use the API depending on the functionality that you need to integrate in your
application.

## Basic Usage

###### Figure 1. Authentication

```java
import ph.com.globe.connect.Authentication;
import org.json.JSONObject;

String appId = "[app_id]";
String appSecret = "[app_secret]";

Authentication auth = new Authentication(appId, appSecret);

System.out.println("Get dialog url:");

String dialogUrl = auth.getDialogUrl();

System.out.println(dialogUrl);

System.out.println("Sending Access Token request:");
System.out.println(auth.getAccessUrl());

JSONObject response = auth
    .getAccessToken("[code]")
    .getJsonResponse();

System.out.println("Response -->");
System.out.println("Access Token: " + response.getString("access_token"));
System.out.println("Subscriber Number: " + response.getString("subscriber_number"));
```

###### Figure 2. Amax

```java
import ph.com.globe.connect.Amax;
import org.json.JSONObject;

String appId = "[app_id]";
String appSecret = "[app_secret]";

Amax amax = new Amax(appId, appSecret);

System.out.println("Sending rewards request: ");

JSONObject response = amax
    .setRewardsToken("[rewards_token]")
    .setAddress("[+63 subscriber_number]")
    .setPromo("[promo]")
    .sendRewardRequest()
    .getJsonResponse();

System.out.println("Response: -->");
System.out.println(response);
```

###### Figure 3. Binary SMS

```java
import ph.com.globe.connect.BinarySms;
import org.json.JSONObject;

BinarySms sms = new BinarySms("[short_code]", "[access_token]");

System.out.println("Sending Binary SMS: ");

JSONObject response = sms
    .setUserDataHeader("[data_header]")
    .setDataCodingScheme([scheme])
    .setReceiverAddress("[+63 subscriber_number]")
    .setBinaryMessage("[message]")
    .sendBinaryMessage()
    .getJsonResponse();

System.out.println("Response: -->");
System.out.println(response.toString());
```

###### Figure 4. Location

```java
import ph.com.globe.connect.Location;
import org.json.JSONObject;

Location location = new Location("[access_token]");

System.out.println("Getting Subscriber Location: ");

JSONObject response = location
    .setAddress("0[+63 subscriber_number]")
    .setRequestedAccuracy([accuracy])
    .getLocation()
    .getJsonResponse();

System.out.println("Response: -->");
System.out.println(response.toString());
```

###### Figure 5. Payment (Send Payment Request)

```java
import ph.com.globe.connect.Payment;
import org.json.JSONObject;

Payment payment = new Payment("[access_token]");

System.out.println("Sending Payment Request: ");

JSONObject response = payment
    .setAmount([amount])
    .setDescription("[description]")
    .setEndUserId("[+63 subscriber_number]")
    .setReferenceCode("[reference_code]")
    .setTransactionOperationStatus("[status]")
    .sendPaymentRequest()
    .getJsonResponse();

System.out.println("Response: -->");
System.out.println(response.toString());
```

###### Figure 6. Payment (Get Last Reference ID)

```java
import ph.com.globe.connect.Payment;
import org.json.JSONObject;

Payment payment = new Payment("[access_token]");

System.out.println("Get last reference request: ");

JSONObject reference = payment
    .setAppId("[app_id]")
    .setAppSecret("[app_secret]")
    .getLastReferenceCode()
    .getJsonResponse();

System.out.println("Response: -->");
System.out.println(reference.toString());
```

###### Figure 7. Sms

```java
import ph.com.globe.connect.Sms;
import org.json.JSONObject;

Sms sms = new Sms("[short_code]", "[access_token]");

System.out.println("Sending SMS: ");

JSONObject response = sms
    .setClientCorrelator("[client_correlator]")
    .setReceiverAddress("[+63 subscriber_number]")
    .setMessage("[message]")
    .sendMessage()
    .getJsonResponse();

System.out.println("Response: -->");
System.out.println(response.toString());
```

###### Figure 8. Subscriber (Get Balance)

```java
import ph.com.globe.connect.Subscriber;
import org.json.JSONObject;

Subscriber subscriber = new Subscriber("[access_token]");

System.out.println("Get subscriber balance request: ");

JSONObject response = subscriber
    .setAddress("[+63 subscriber_number]")
    .getSubscriberBalance()
    .getJsonResponse();

System.out.println("Response: -->");
System.out.println(response.toString());
```

###### Figure 9. Subscriber (Get Reload Amount)

```java
import ph.com.globe.connect.Subscriber;
import org.json.JSONObject;

Subscriber subscriber = new Subscriber("[access_token]");

System.out.println("Get subscriber reload amount request: ");

JSONObject reload = subscriber
    .setAddress("[+63 subscriber_number]")
    .getSubscriberReloadAmount()
    .getJsonResponse();

System.out.println("Response: -->");
System.out.println(reload.toString());
```

###### Figure 10. USSD (Send)

```java
import ph.com.globe.connect.Ussd;
import org.json.JSONObject;

Ussd ussd = new Ussd("[access_token]");

System.out.println("USSD Send request: ");

JSONObject response = ussd
    .setSenderAddress("[short_code]")
    .setUssdMessage("[message]")
    .setAddress("[+63 subscriber_number]")
    .setFlash([flash])
    .sendUssdRequest()
    .getJsonResponse();

System.out.println("Response: -->");
System.out.println(response.toString());
```

###### Figure 11. USSD (Reply)

```java
import ph.com.globe.connect.Ussd;
import org.json.JSONObject;

Ussd ussd = new Ussd("[access_token]");

System.out.println("USSD Reply request: ");

JSONObject reply = ussd
    .setSessionId(sessionId)
    .setAddress("[+63 subscriber_number]")
    .setSenderAddress("[short_code]")
    .setUssdMessage("[message]")
    .setFlash([flash])
    .replyUssdRequest()
    .getJsonResponse();

System.out.println(reply.toString());
```
