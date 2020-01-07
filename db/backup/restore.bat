chcp 1252
set PGPASSWORD=password
psql -h localhost -U postgres -f ..\..\be\src\main\resources\database\mochidb_schema.sql
psql -h localhost -U postgres -f ..\..\be\src\main\resources\database\mochidb_data.sql