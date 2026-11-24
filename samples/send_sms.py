import requests

xml = """<?xml version="1.0" encoding="UTF-8"?>
<sendsms>
  <sender>CosmicSMS</sender>
  <message>Hello from Cosmic SMS!</message>
  <mobiles>
    <mobile>447700900701</mobile>
  </mobiles>
</sendsms>"""

resp = requests.post(
    "https://api.cosmicsms.com/prod/rest/sms/send",
    data=xml.encode("utf-8"),
    headers={"Content-Type": "application/xml"},
    auth=("USERNAME", "API_PASSWORD"),
)
print(resp.status_code, resp.text)
