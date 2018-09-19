#!/usr/bin/env bash
psql -h localhost -U mochidb_owner mochidb <  mochidb.backup
