#!/bin/bash

echo -n "Enter the URL: "
read -r URL

if [[ $URL != http* ]]; then
  URL="https://${URL}"
fi

if curl -s -f -o /dev/null -w "%{http_code}" "$URL" >/dev/null; then
  HTTP_STATUS=$(curl -s -f -o /dev/null -w "%{http_code}" "$URL")
  echo "Website dapat diakses. HTTP Status: $HTTP_STATUS"
else
  echo "Website tidak dapat diakses."
fi
