<?php
$xml = '<?xml version="1.0" encoding="UTF-8"?>
<sendsms>
  <sender>CosmicSMS</sender>
  <message>Hello from Cosmic SMS!</message>
  <mobiles>
    <mobile>447700900701</mobile>
  </mobiles>
</sendsms>';

$ch = curl_init("https://api.cosmicsms.com/prod/rest/sms/send");
curl_setopt_array($ch, [
    CURLOPT_POST           => true,
    CURLOPT_POSTFIELDS     => $xml,
    CURLOPT_HTTPHEADER     => ["Content-Type: application/xml"],
    CURLOPT_USERPWD        => "USERNAME:API_PASSWORD",
    CURLOPT_RETURNTRANSFER => true,
]);

$response = curl_exec($ch);
echo curl_getinfo($ch, CURLINFO_HTTP_CODE) . PHP_EOL . $response;
