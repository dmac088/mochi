set PGPASSWORD=password
pg_dumpall  -U mochidb_owner --data-only --database=mochidb > ../../be/src/main/resources/database/mochidb_data.sql