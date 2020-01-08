set PGPASSWORD=password
pg_dumpall -U mochidb_owner --schema-only --clean --database=mochidb > ../../be/src/main/resources/database/mochidb_schema.sql
