set PGPASSWORD=password
pg_dump -U mochidb_owner -E UTF8 mochidb > mochidb.backup
