/* MYSQL

mysql -u root -p
--> Starting

show engines;
--> show all engines

/**  Basic Commands  **/
create database database_name; 
--> create new database

show databases;
--> show all databases

use database_name;
--> use test database

drop database database_name;
--> drop database


/**  Basic Data Types  **/

/**  Operator  **/
/*
>, <, >=, <=, !=, =,
AND, OR, NOT, LIKE/NOT LIKE,
BETWEEN/NOT BETWEEN, IN/NOT IN,
EXISTS/NOT EXISTS, REGEXP/NOT REGEXP,
IS NULL/NOT NULL,

use bracket to prioritize values
*/

/**  Tables  **/
show tables;
--> show tables in database_name

create table table_name (
  column_name data_type, 
  column_name data_type not null, 
  column_name data_type not null default value, 
  ...
  primary key (column_name)
  OR
  primary key (column_name, column_name2, ...),
) engine = 'engine_name';
--> create table with engine
--> not null means that the column is required or cannot be empty
--> default value means that the column will have a default value

describe table_name;
OR
desc table_name;
--> show table structure

show create table table_name;
--> show table creation command

truncate table_name;
--> delete all data in table and create a new table
OR
drop table table_name;
--> permanently delete table

--> alter table
alter table table_name
  add COLUMN column_name data_type;
  --> add new column
  add COLUMN column_name data_type AFTER column_name;
  --> add new column and put it after another column
  add column_name timestamp not null default current_timestamp;
  --> add new column and set it to current timestamp
  add primary key (column_name);
  --> set column as primary key, you can use this if you froget setting primary key
  --> or you can use this to set multiple primary keys

  drop COLUMN column_name;
  --> drop column

  rename COLUMN column_name to new_column_name;
  --> rename column

  modify column_name data_type AFTER column_name;
  --> modify column and put it after another column
  modify column_name data_type FIRST;
  --> modify column and put it first
  modify column_name data_type NOT NULL;
  --> modify column and make it not null

/** Insert/Select/Where/Delete Data **/

--> insert data
insert into table_name (column_name, column_name)
values(value, value);

OR inputing multiple values

insert into table_name (column_name, column_name)
values(value1, value2),
      (value3, value4),

--> Select all data
SELECT * FROM table_name;
--> select spesific data
SELECT id, name, price, quantity FROM products;
--> the order of the data will be the order of the columns that shown
SELECT * FROM table_name
WHERE column_name = value;
--> you can also use different operators

--> Update data
UPDATE table_name
SET column_name = value
WHERE column_name = value;
--> Update multiple data
UPDATE table_name
SET column_name = value1,
    column_name2 = value2
WHERE column_name = value;

--> lets say its INT data
UPDATE table_name
SET column_name = column_name + 1
WHERE column_name = value;

--> Delete data
DELETE FROM table_name
WHERE column_name = value;
/*
Becarefull with Delete command make sure
WHERE point to the correct data
*/

/**  Alias  **/
--> Alias
SELECT column_name AS new_column_name
FROM table_name;

--> you can also give the table_name Alias
SELECT column_name AS new_column_name
FROM table_name AS new_table_name;

/**  Order by clause  **/
--> Order by
SELECT column_name
FROM table_name
ORDER BY column_name ASC/DESC,
         column_name ASC/DESC;

/**  Distinct Data  **/
SELECT DISTINCT column_name FROM table_name;


/**  Arithemic Operator / Mathematical Func  **/
--> Arithemic
%/MOD, *, +, -, -(in front of), /, DIV
SELECT 10, 10, 10 * 10 as Hasil;

--> Mathematical
SELECT id, COS(column_name), SIN(column_name)
FROM table_name;
WHERE column_name DIV value >< values

/**  AutoIncrement  **/
--> having id that auto increment
CREATE TABLE admin
(
    id         INT          NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

/**  String Func  **/
SELECT id,
       LOWER(name) as 'Lowercase Name',
       UPPER(name) as 'Uppercase Name',
       LENGTH(name) as 'Length Name'
FROM products;

/**  Date/Time Func  **/
SELECT id, created_at,
       YEAR(created_at) as 'Year',
       MONTH(created_at) as 'Month'
FROM products;

/**  Flow Control Func  **/
--> CASE
SELECT DISTINCT category 
       CASE category
            WHEN 'makanan' THEN 'enak'
            WHEN 'minuman' THEN 'seger'
            ELSE 'lainnya'
            END AS 'Category'
FROM products;

--> IF
SELECT id,
       price,
       IF(price <= 15000, 'Murah',
          IF(price <= 20000, 'Mahal', 'Mahal Banget')) AS 'Harga'
FROM products;

--> IFNULL
SELECT id,
       name,
       IFNULL(description, 'Deskripsi Kosong') AS 'Harga'
FROM products;

/**  Aggregate Func  **/
SELECT AVG(price) AS 'Rata-Rata Harga'
FROM products;

SELECT MAX(price) AS 'Harga Tertinggi'
FROM products;

SELECT MIN(price) AS 'Harga Terendah'
FROM products;

SELECT COUNT(id) as 'Total Produk'
FROM products;

/**  Group By or Grouping  
     you can use any aggregate func
     with GROUP BY
**/
SELECT category,
       SUM(quantity) AS 'Jumlah Stok'
FROM products
GROUP BY category;

/**  Having Clause
     you cant use WHERE because it is used with GROUP BY
     because WHERE is an aggregate func
**/

SELECT category, SUM(quantity) AS 'Total'
FROM products
GROUP BY category
HAVING Total > 50;



/**  Constraint  **/
-- So that you can't have duplicate value or data

-- Add Constraint on Table
UNIQUE KEY constraint_name (column_name)

-- How to make one if you forget to make it unique
ALTER TABLE table_name
ADD CONSTRAINT constraint_name UNIQUE (column_name);

-- How to drop one
ALTER TABLE table_name
DROP CONSTRAINT constraint_name;

--> Check Constraint Key
-- so that you can't have value/data that not in the table
-- that get constraint from Check Constraint
-- for example a products quantity that cant be negative

CONSTRAINT constraint_name CHECK (column_name = value)

-- How to make one if you forget to make Check
ALTER TABLE table_name
ADD CONSTRAINT constraint_name CHECK (column_name = value);

-- How to drop one
ALTER TABLE table_name
DROP CONSTRAINT constraint_name;


/** Index **/

-- for faster search
-- be careful with adding index
-- PRIMARY and UNIQUE key by default is index

--> Add Index on Table
INDEX index_name (column_name)
-- Multiple Index
INDEX index_name (column_name1, column_name2)

-- How To Select
SELECT * FROM table_name
WHERE column_name = value
-- IF its have Multiple Index then add AND
WHERE column_name1 = value1 AND column_name2 = value2

-- How to make one if you forget to make it Index
ALTER TABLE table_name
ADD INDEX index_name (column_name);

-- How to drop one
ALTER TABLE table_name
DROP INDEX index_name;


/** Full-Text Search  **/

-- Add Full-Text Search on Table
FULLTEXT index_name (column_name)
-- How to make one if you forget to make it Full-Text Search
ALTER TABLE table_name
ADD FULLTEXT index_name (column_name);
-- How to drop one
ALTER TABLE table_name
DROP INDEX index_name;

-- The Order Result of Full-Text Search Depend On Your Keyword/Databse

-- How To Use Full-Text Search
SELECT * FROM table_name
WHERE MATCH (column_name) AGAINST ('keyword')

SELECT * FROM table_name
WHERE MATCH (column_name) AGAINST ('+keyword -keyword' IN BOOLEAN MODE)

SELECT * FROM table_name
WHERE MATCH (column_name) AGAINST ('keyword' WITH QUERY EXPANSION)

/** Key **/

--> Primary Key
PRIMARY KEY (column_name)
-- How to make one if you forget to make it Primary Key
ALTER TABLE table_name
ADD PRIMARY KEY (column_name);
-- How to drop one
ALTER TABLE table_name
DROP PRIMARY KEY;

--> Foreign Key
CONSTRAINT constraint_name -- Its ok if you dont use this constraint, but pratically use it
FOREIGN KEY (column_name) REFERENCES table_name (column_name)

-- How to make one if you forget to make it Foreign Key
ALTER TABLE table_name
ADD CONSTRAINT constraint_name
FOREIGN KEY (column_name) REFERENCES table_name (column_name);

-- How to drop one
ALTER TABLE table_name
DROP FOREIGN KEY constraint_name;

-- If You Try To Delete For Example
DELETE FROM table_name
WHERE column_name = value
-- You Will Get Error, Because You Have Foreign Key
-- You Need To Delete Data From Table That Use Foreign Key First

/* Foreign Key Behavior
BEHAVIOR : RESTRICT, CASCADE, NO ACTION, SET NULL

ON DELETE : REJECTED, DATA DELETED, LET IT BE, TURN INTO NULL
ON UPDATE : REJECTED, DATA UPDATED, LET IT BE, TURN INTO NULL
*/

-- How To Use Foreign Key Behavior
CONSTRAINT constraint_name
FOREIGN KEY (column_name) REFERENCES table_name (column_name)
ON DELETE BEHAVIOR
ON UPDATE BEHAVIOR

-- How To Make One If You Forget To Make It
ALTER TABLE table_name
ADD CONSTRAINT constraint_name
FOREIGN KEY (column_name) REFERENCES table_name (column_name)
ON DELETE BEHAVIOR
ON UPDATE BEHAVIOR;

-- How To Drop One
ALTER TABLE table_name
DROP CONSTRAINT constraint_name;

/** Join **/

SELECT *
FROM table_name1
JOIN table_name2 ON (table_name1.column_name = table_name2.column_name);

-- How To Select Only Some Of IT
SELECT table_name1.column_name, table_name2.column_name
FROM table_name1
JOIN table_name2 ON (table_name1.column_name = table_name2.column_name);

-- You can join multiple tables but first you need to also make the Foreign Key of it

/** Relation **/

--> One To One : Make Both Side Foreign Key And Unique Ex : Customer, Wallet
--> One To Many : Make One Side Foreign Key And Unique Ex : Category, Product
--> Many To Many : Make Both Side Foreign Key Without Unique/Constraint But Using Extra Table For Relation (Asociative Entity)
--                 Ex : Product --> Order_details <-- Order















*/
