#!/bin/sh
DATABASE_NAME=${1-smartpool}
HOST=${2-localhost}
echo "******************* START: Creating schema ${DATABASE_NAME} *******************"
sed "s:\`smartpool\`:\`$DATABASE_NAME\`:" < dbscripts/db_schema.sql | mysql -u root -h $HOST
echo "******************* END: Creating schema ${DATABASE_NAME} *******************"