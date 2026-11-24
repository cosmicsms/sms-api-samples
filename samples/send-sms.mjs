const xml = `<?xml version="1.0" encoding="UTF-8"?>
<sendsms>
  <sender>CosmicSMS</sender>
  <message>Hello from Cosmic SMS!</message>
  <mobiles>
    <mobile>447700900701</mobile>
  </mobiles>
</sendsms>`;

const auth = Buffer.from("USERNAME:API_PASSWORD").toString("base64");

const res = await fetch("https://api.cosmicsms.com/prod/rest/sms/send", {
  method: "POST",
  headers: {
    "Content-Type": "application/xml",
    Authorization: `Basic ${auth}`,
  },
  body: xml,
});

console.log(res.status, await res.text());
