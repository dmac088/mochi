set PGPASSWORD=password
pg_dump -U mochidb_owner --data-only --inserts  --schema "mochi" mochidb > ../../be/src/main/resources/database/mochi_data.sql
pg_dump -U security_owner --data-only --inserts  --schema "security" mochidb > ../../be/src/main/resources/database/security_data.sql
