#!/bin/bash

echo 'using parameter' $1 

curl -k --location --request POST 'https://localhost:8090/api/Product/Upload/' \
--header "authorization: Bearer $1" \
--header 'Content-Type: multipart/form-data' \
--form 'file=@product_master.tsv'
