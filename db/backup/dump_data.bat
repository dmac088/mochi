pg_dump -U mochidb_owner --data-only --schema "mochi" mochidb > ../../be/src/main/resources/database/mochi_data.sql
pg_dump -U security_owner --data-only --schema "security" mochidb > ../../be/src/main/resources/database/security_data.sql
