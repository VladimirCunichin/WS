# WS
Web servisai  
Kad paleisti programa:  
cd shop  
docker-compose up  
http://localhost:5000/swagger-ui.html#/


SOAP uzklausos:  
http://localhost:5000/ws  
add:  
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gs="http://spring.io/guides/gs-producing-web-service">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:addArticleRequest>
         <gs:id>2</gs:id>
         <gs:name>xd</gs:name>
         <gs:description>desccc</gs:description>
         <gs:partId>4</gs:partId>
         <gs:pcpartid>4</gs:pcpartid>
         <gs:manufacturer>xd</gs:manufacturer>
         <gs:pcpartname>myname</gs:pcpartname>
         <gs:price>1337</gs:price>
         <gs:type>testtype</gs:type>
      </gs:addArticleRequest>
   </soapenv:Body>
</soapenv:Envelope>

delete:  
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gs="http://spring.io/guides/gs-producing-web-service">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:deleteArticleRequest>
         <gs:id>2</gs:id>
      </gs:deleteArticleRequest>
   </soapenv:Body>
</soapenv:Envelope>
  
update:  
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gs="http://spring.io/guides/gs-producing-web-service">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:updateArticleRequest>
         <gs:id>2</gs:id>
         <gs:newid>7</gs:newid>
         <gs:name>asd</gs:name>
         <gs:description>fsda</gs:description>
         <gs:partId>3</gs:partId>
         <gs:pcpartid>3</gs:pcpartid>
         <gs:manufacturer>man</gs:manufacturer>
         <gs:pcpartname>newname</gs:pcpartname>
         <gs:price>247</gs:price>
         <gs:type>yes</gs:type>
      </gs:updateArticleRequest>
   </soapenv:Body>
</soapenv:Envelope>
  
get:  
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gs="http://spring.io/guides/gs-producing-web-service">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:getArticleRequest>
         <gs:id>2</gs:id>
      </gs:getArticleRequest>
   </soapenv:Body>
</soapenv:Envelope>
