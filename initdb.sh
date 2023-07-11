#!/bin/bash

# install postgresql
sudo apt-get update
sudo apt-get install -y postgresql postgresql-contrib
sudo systemctl start postgresql.service

# creating user and database
sudo -i -u postgres

# in postgres shell
# username = irembo
createuser --interactive --pwprompt
createdb irembo

# using PGAdmin
# restore database using dump file


