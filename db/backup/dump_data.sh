set PGPASSWORD=password
pg_dump -U mochidb_owner --data-only --inserts  --schema "mochi" mochidb > ../../be/src/main/resources/database/mochi_data.sql
