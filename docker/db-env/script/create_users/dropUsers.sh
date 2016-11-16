sqlplus "/as sysdba" << EOF
@drop_dev_users.sql 12 DEV
EOF
