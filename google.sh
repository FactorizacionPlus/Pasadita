#!/bin/zsh

client_id=470401541816-35t7onpn3ger6dbe5b6q8dph6o78t4rf.apps.googleusercontent.com
response_type="code"

redirect_uri="http://localhost:8080/auth/login/google"
# redirect_uri="https://pasadita-production.up.railway.app/auth/login/google"
redirect_uri=$(printf $redirect_uri | jq -sRr @uri)

scope="email profile openid"
scope=$(printf $scope | jq -sRr @uri)

promp="select_account"

endpoint="https://accounts.google.com/o/oauth2/v2/auth"

echo "$endpoint?client_id=$client_id&redirect_uri=$redirect_uri&response_type=$response_type&scope=$scope&prompt=$promp"
