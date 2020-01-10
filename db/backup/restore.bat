chcp 1252
set PGPASSWORD=password
psql -h localhost -U postgres -c "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE  datname = 'mochidb'"
psql -h localhost -U postgres -f mochidb.backup
