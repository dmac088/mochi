#!/usr/bin/env bash
pg_dumpall -U mochidb_owner --clean --database=mochidb > mochidb.backup
