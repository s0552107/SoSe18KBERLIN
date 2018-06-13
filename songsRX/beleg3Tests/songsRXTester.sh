#! /bin/sh
# Usage:
# ./songsRXTester.sh auth_token
# Example:
# ./songsRXTester.sh 1234567890poiuytrewq
# 

if [ "$#" -ne 1 ]; then
    echo "Illegal number of parameters"
    echo "Usage: ./songsRXTester.sh auth_token"
    echo "Example: ./songsRXTester.sh 1234567890poiuytrewq"
    exit 1
fi

echo "--- REQUESTING ALL SONGS WTH BAD TOKEN: should return 401 --------"
curl -X GET \
     -H "Authorization: 1234567890poiuytrewq" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING ALL SONGS WTHOUT TOKEN: should return 401 or 404 --------"
curl -X GET \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING ALL XML SONGS--------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING SONG 7 IN XML--------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs/7"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING NON-EXISTING SONG 22:--------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/json" \
     -v "http://localhost:8080/songsRX/rest/songs/22"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING NON-EXISTING SONG 'OLD':--------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/json" \
     -v "http://localhost:8080/songsRX/rest/songs/OLD"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- POSTING A JSON SONG ------------------"
curl -X POST \
     -H "Authorization: $1" \
     -H "Content-Type: application/json" \
     -d "@einSong.json" \
     -v "http://localhost:8080/songsRX/rest/songs"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- POSTING A XML SONG ------------------"
curl -X POST \
     -H "Authorization: $1" \
     -H "Content-Type: application/xml" \
     -d "@einSong.xml" \
     -v "http://localhost:8080/songsRX/rest/songs"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING NEW SONG 11 IN XML--------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs/11"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING NEW SONG 12 IN JSON--------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/json" \
     -v "http://localhost:8080/songsRX/rest/songs/12"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- UPDATING JSON-SONG 9 ------------------"
curl -X PUT \
     -H "Authorization: $1" \
     -H "Content-Type: application/json" \
     -d "@updatedSong.json" \
     -v "http://localhost:8080/songsRX/rest/songs/9"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING UPDATED SONG 9 ------------------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/json" \
     -v "http://localhost:8080/songsRX/rest/songs/9"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- UPDATING XML-SONG 10 ------------------"
curl -X PUT \
     -H "Authorization: $1" \
     -H "Content-Type: application/xml" \
     -d "@updatedSong.xml" \
     -v "http://localhost:8080/songsRX/rest/songs/10"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING UPDATED SONG 10 ------------------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs/10"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- UPDATING NON-EXISTING SONG 22 WITH PAYLOAD SONG 10 -----------------"
curl -X PUT \
     -H "Authorization: $1" \
     -H "Content-Type: application/xml" \
     -d "@updatedSong.xml" \
     -v "http://localhost:8080/songsRX/rest/songs/22"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING UPDATED SONG 10: IT SHOULD NOT BE UPDATED ------------------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs/10"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING UPDATED SONG 22: SHOULD GIVE 404 ------------------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs/22"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- UPDATING SONG 7 WITH PAYLOAD SONG 10 -----------------"
curl -X PUT \
     -H "Authorization: $1" \
     -H "Content-Type: application/xml" \
     -d "@updatedSong.xml" \
     -v "http://localhost:8080/songsRX/rest/songs/7"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING UPDATED SONG 7 ------------------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs/7"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- UPDATING SONG 7 WITH BAD PAYLOAD -----------------"
curl -X PUT \
     -H "Authorization: $1" \
     -H "Content-Type: application/xml" \
     -d "@notALoveSong.txt" \
     -v "http://localhost:8080/songsRX/rest/songs/7"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING SONG 7: ------------------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs/7"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- DELETING SONG 8 --------"
curl -X DELETE \
     -H "Authorization: $1" \
     -H "Accept: text/plain" \
     -v "http://localhost:8080/songsRX/rest/songs/8"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- DELETING SONG 8 AGAIN: SHOULD PRODUCE 404 OR 400 OR 410 --------"
curl -X DELETE \
     -H "Authorization: $1" \
     -H "Accept: text/plain" \
     -v "http://localhost:8080/songsRX/rest/songs/8"
echo " "
echo "-------------------------------------------------------------------------------------------------"

