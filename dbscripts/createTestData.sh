#!/bin/sh
DATABASE_NAME=${1-smartpool}
HOST=${2-localhost}
echo "******************* START: Creating test data ${DATABASE_NAME} *******************"
sed "s:\`smartpool\`:\`$DATABASE_NAME\`:" < dbscripts/test_data.sql | mysql -u root -h $HOST
echo "******************* END: Creating test data ${DATABASE_NAME} *******************"