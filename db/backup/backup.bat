chcp 1252
set PGPASSWORD=password
pg_dump -U mochidb_owner -E UTF8 mochidb > mochidb.backup
call ./dump_schema.bat
call ./dump_data.bat 
