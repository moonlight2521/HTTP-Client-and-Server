# HTTP-Client-and-Server

Steps:

>HTTPServer:

>Compile: Javac HTTPServer.java
>Run: Java HTTPSerer port


HTTPClient:
>Compile: Javac HTTPClient.java
>Run: Java HTTPClient localhost:port#/path 
>Or
>Run: Java HTTPClient PUT localhost:port#/path 


# Examples
_____________________________________________________________________________
Example1:

HTTPServer:
Zuns-MacBook-Pro:Zun_Lin zunlin$ javac HTTPServer.java 
Zuns-MacBook-Pro:Zun_Lin zunlin$ java HTTPServer 10000
Listening on port 10000...
Client Made Connection
GET /index.html HTTP/1.0
Host: localhost
Time: Wed Apr 10 20:04:07 EDT 2019
User-Agent: VCU-CMSC491
User-name: Zun


HTTPClient:
HTTPClient.java	HTTPServer.java
Zuns-MacBook-Pro:Zun_Lin zunlin$ javac HTTPClient.java 
Zuns-MacBook-Pro:Zun_Lin zunlin$ java HTTPClient localhost:10000/index.html
HTTP/1.0 200 OK
Time: Wed Apr 10 20:04:07 EDT 2019
Last  Modified: Wed Apr 10 20:03:02 EDT 2019


____________________________________________________________________________
Example2(from browser):
HTTPServer:
GET / HTTP/1.1
Host: localhost:10000
Connection: keep-alive
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3
Accept-Encoding: gzip, deflate, br
Accept-Language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7,zh-TW;q=0.6
Cookie: io=-cZEFleqDL83mPMjAAAV

Client:
Google chrome

____________________________________________________________________________
Example3(PUT):

HTTPServer:
PUT /index.html HTTP/1.0
Host: localhost
Time: Wed Apr 10 20:11:14 EDT 2019
User-Agent: VCU-CMSC491
User-name: Zun

HTTPClient:
Zuns-MacBook-Pro:Zun_Lin zunlin$ java HTTPClient PUT localhost:10000/index.html
606 FAILED File NOT Created
Time: Wed Apr 10 20:11:14 EDT 2019


______________________________________________________________________________

Example4(PUT):

HTTPServer:
Client Made Connection
PUT /myApp/vcu.html HTTP/1.0
Host: localhost
Time: Wed Apr 10 20:15:03 EDT 2019
User-Agent: VCU-CMSC491

HTTPClient:
Zuns-MacBook-Pro:Zun_Lin zunlin$ java HTTPClient PUT localhost:10000/myApp/vcu.html
200 OK File Created
Time: Wed Apr 10 20:15:03 EDT 2019
PUT /myApp/vcu.html
Last  Modified: Wed Apr 10 20:15:03 EDT 2019

