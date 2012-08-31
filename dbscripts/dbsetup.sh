#!/bin/sh
DATABASE_NAME=${1-smartpool}
HOST=${2-localhost}
echo "Creating database ${DATABASE_NAME}"
sed "s:\`smartpool\`:\`$DATABASE_NAME\`:" < dbscripts/SmartPoolDB.sql | mysql -u root -h $HOST