# HW3-security
Simple auth server using spring-security and spring -security-oauth2
based on postman and eclipse

How to run:

run on server ->

POST 

http://localhost:8081/SpringSecurityOAuth2/oauth/token?grant_type=password&username=bill&password=abc123 

type=Basic Auth 

username=my-trusted-client +password=secret ->

get access_token ->

GET

http://localhost:8081/SpringSecurityOAuth2/get?filename=ladygaga.txt&N=6&num=50&type=p?access_token=***** ->

results
