#!/usr/bin/env bash
pg_dumpall -U mochidb_owner --clean --database=mochidb > mochidb.backup
tar -czvf archive/"mochidb.backup-$(date +"%Y%m%dT%H%M").gz" mochidb.backup
