using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;

var xml = @"<?xml version=""1.0"" encoding=""UTF-8""?>
<sendsms>
  <sender>CosmicSMS</sender>
  <message>Hello from Cosmic SMS!</message>
  <mobiles>
    <mobile>447700900701</mobile>
  </mobiles>
</sendsms>";

using var client = new HttpClient();
var auth = Convert.ToBase64String(Encoding.UTF8.GetBytes("USERNAME:API_PASSWORD"));
client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", auth);

var content = new StringContent(xml, Encoding.UTF8, "application/xml");
var res = await client.PostAsync("https://api.cosmicsms.com/prod/rest/sms/send", content);

Console.WriteLine((int)res.StatusCode);
Console.WriteLine(await res.Content.ReadAsStringAsync());
