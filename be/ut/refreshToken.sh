curl --request POST \
  --url https://localhost:8090/oauth/token \
  --header 'authorization: Basic c3ByaW5nLXNlY3VyaXR5LW9hdXRoMi1yZWFkLXdyaXRlLWNsaWVudDpzcHJpbmctc2VjdXJpdHktb2F1dGgyLXJlYWQtd3JpdGUtY2xpZW50LXBhc3N3b3JkMTIzNA==' \
  --header 'cache-control: no-cache' \
  --header 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  --header 'postman-token: ddc7416c-0363-1a8d-d676-7b66b0690ec0' \
  --form grant_type=refresh_token \
  --form refresh_token=460702e4-80ad-48c5-9dbf-f13f15e214fe
