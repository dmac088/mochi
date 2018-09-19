#!/usr/bin/env bash
pg_dump -n mochi -E UTF8 mochidb > mochidb.backup
