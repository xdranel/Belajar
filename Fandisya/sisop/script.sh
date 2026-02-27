#!/bin/bash

folder_path="$HOME/xdx-1o1/Belajar/Sisop/script.sh"
archive_output="backup_$(date +%Y-%m-%d) $(date +%H-%M-%S).zip"

if [ -d "$folder_path" ]; then
  zip -r "$archive_output" "$folder_path"/*
  echo "Backup Berhasil di simpan di $folder_path/$archive_output"
else
  echo "Folder $folder_path Tidak Ditemukan"
fi
