curl https://api.cosmicsms.com/prod/rest/sms/send \
  -u "USERNAME:API_PASSWORD" \
  -H "Content-Type: application/xml" \
  --data-binary '<?xml version="1.0" encoding="UTF-8"?>
<sendsms>
  <sender>CosmicSMS</sender>
  <message>Hello from Cosmic SMS!</message>
  <mobiles>
    <mobile>447700900701</mobile>
  </mobiles>
</sendsms>'
