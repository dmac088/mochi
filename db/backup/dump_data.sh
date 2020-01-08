set PGPASSWORD=password
pg_dump -U mochidb_owner --data-only --schema "mochi" mochidb > ../../be/src/main/resources/database/mochi_schema.sql
