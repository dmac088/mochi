#!/usr/bin/env bash
pg_dump -c -n mochi -E UTF8 mochidb > mochidb.backup
