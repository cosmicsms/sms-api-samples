# Cosmic SMS - REST API code samples

Working examples for sending SMS through the [Cosmic SMS](https://www.cosmicsms.com) REST API - UK business SMS with direct carrier routes, a delivery receipt on every message, and pay-as-you-go credit that never expires.

**AI-assistant shortcut:** a compact, machine-readable spec lives at
[cosmicsms.com/cosmic-sms-api.md](https://www.cosmicsms.com/cosmic-sms-api.md).
Paste it into your AI assistant and ask it to generate integration code for your stack - that file plus the samples here are usually all it needs.

## Quickstart

1. [Sign up free](https://service.cosmicsms.com/register) - new accounts get free test credits.
2. Your **API password** is managed in the web app under **Settings** (it is separate from your login password).
3. Pick a sample from [`samples/`](samples/), drop in your username, API password and a recipient number, and run it.

## The API in one paragraph

Base URL `https://api.cosmicsms.com`.  Authentication is HTTP Basic (`username:API_PASSWORD`, Base64-encoded in the `Authorization` header).  Requests and responses are XML with `Content-Type: application/xml`.  A send returns a `<batch>` id - keep it to poll delivery status.

| Method | Path | Purpose |
|--------|------|---------|
| POST | `/prod/rest/sms/send` | Send to 1-500 recipients |
| GET | `/prod/rest/sms/status/{batch_id}` | Delivery status for a batch |
| GET | `/prod/rest/sms/receive[/{from}[/{to}]]` | Inbound messages (dates `yyyyMMddHHmm`) |
| GET | `/prod/rest/sms/cancel/{batch_id}` | Cancel a scheduled batch |
| GET | `/prod/rest/account/balance` | Current credit balance |

### Send request

```xml
<?xml version="1.0" encoding="UTF-8"?>
<sendsms>
  <sender>CosmicSMS</sender>              <!-- alphanumeric max 11, or numeric max 15 -->
  <message>Hello from Cosmic SMS!</message>
  <sendtime>2026-07-01T13:15Z</sendtime>  <!-- optional ISO-8601; omit = send now -->
  <multilingual>2</multilingual>          <!-- 0=GSM-7, 1=Unicode, 2=auto -->
  <mobiles>
    <mobile>447700900701</mobile>         <!-- international format, no + or leading 0; max 500 -->
  </mobiles>
</sendsms>
```

### Send response

```xml
<?xml version="1.0" encoding="UTF-8"?>
<sendsms>
  <batch>20154010</batch>
  <mobilestatus>
    <mobile>447700900701</mobile>
    <statusid>SUCCESS</statusid>
    <statustext>Success: Message submitted successfully</statustext>
  </mobilestatus>
</sendsms>
```

## Samples

Each file is the same minimal send, in your language of choice - identical to the examples on the [SMS API page](https://www.cosmicsms.com/sms-api).

| Language | File | Run |
|----------|------|-----|
| cURL | [`samples/send-sms.sh`](samples/send-sms.sh) | `sh send-sms.sh` |
| Python 3 | [`samples/send_sms.py`](samples/send_sms.py) | `pip install requests` then `python send_sms.py` |
| Node 18+ | [`samples/send-sms.mjs`](samples/send-sms.mjs) | `node send-sms.mjs` |
| PHP | [`samples/send-sms.php`](samples/send-sms.php) | `php send-sms.php` |
| C# (.NET 6+) | [`samples/SendSms.cs`](samples/SendSms.cs) | `dotnet run` |
| Java 17+ | [`samples/SmsApi.java`](samples/SmsApi.java) | `java SmsApi.java USERNAME API_PASSWORD 447700900701` |

## Postman

Import [`postman/Cosmic-SMS-API.postman_collection.json`](postman/Cosmic-SMS-API.postman_collection.json), set the collection variables `USERNAME` and `API_PASSWORD`, and all five endpoints are ready to call.

## Good to know

- **Message length:** GSM-7 gives 160 characters in one part, then 153 per part (maximum 918 = 6 parts).  Unicode gives 70, then 67 per part.  Each part costs one credit.
- **Escaping:** `<`, `&`, `>` inside `<message>` must be `&lt;` `&amp;` `&gt;`.
- **Submission statuses:** SUCCESS, INVALID_REQUEST, INVALID_VALUES, INVALID_USER, INSUFFICIENT_CREDIT, SYSTEM_ERROR and friends.  **Delivery statuses:** DELIVERED, FAILED, REJECTED, UNKNOWN, PENDING.
- The example numbers here use Ofcom's reserved drama range (`447700900xxx`) - swap in a real handset to see a message arrive.

## Links

- [SMS API overview](https://www.cosmicsms.com/sms-api) · [Full API guide](https://www.cosmicsms.com/guide-api) · [PDF specification](https://www.cosmicsms.com/files/Cosmic-SMS-REST-API.pdf)
- [Pricing](https://www.cosmicsms.com/prices) - UK texts from 3.6p, credit never expires
- [Contact the team](https://www.cosmicsms.com/contact)

---
Cosmic SMS is a trading name of Cosmic Apps Ltd · support@cosmicsms.com · +44 (0) 20 7183 9065
