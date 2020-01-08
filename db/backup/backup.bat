chcp 1252
set PGPASSWORD=password
pg_dumpall -U mochidb_owner --clean --database=mochidb > mochidb.backup

