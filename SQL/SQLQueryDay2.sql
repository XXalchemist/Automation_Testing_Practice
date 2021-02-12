/* Using employee database and creating a table */
use employee;

CREATE TABLE employeeDetails(
 id INT,
 firstName VARCHAR(20),
 lastName VARCHAR(20)
);

/* Inserting values into table */
INSERT into employeeDetails(id, firstname, lastname) 
VALUES(
	1001,
	'Narayan',
	'Krishna'
);

INSERT into employeeDetails(id, firstname, lastname) 
VALUES(
	1002,
	'ABC',
	'XYZ'
);

INSERT into employeeDetails(firstName, lastName, employeeId)
VALUES(
	'newEmployee',
	'xxx',
	1234
);

/* Adding new column in table */
ALTER TABLE employeeDetails
ADD branch VARCHAR(3);

ALTER TABLE employeeDetails
ADD employeeKey VARCHAR(3);

/* Dropping branch column */
ALTER TABLE employeeDetails DROP COLUMN branch;

/* Truncate and Drop */
TRUNCATE TABLE employeeDetails;
DROP TABLE employeeDetails;

/* Seeing the table details */
SELECT * 
FROM employeeDetails;

/* Rename Column name */

/* Updating name value */
UPDATE employeeDetails 
SET employeeKey = 'abc'
WHERE employeeId = 1001;

UPDATE employeeDetails 
SET employeeKey = 'def'
WHERE employeeId = 1002;

/* Seeing the table details */
SELECT * 
FROM employeeDetails;

/* Practice on table */

--- Rename the id column name to studentId ---
sp_Rename 'employeeDetails.studentId', 'employeeId','COLUMN'; 

SELECT * 
FROM employeeDetails;

---  Delete record for id = 1234 ---
DELETE FROM employeeDetails
WHERE employeeId = 1234;

SELECT * 
FROM employeeDetails;

--- Use of Like Clause ---
SELECT firstName
FROM employeeDetails
WHERE firstName LIKE 'a%';

SELECT firstName
FROM employeeDetails
WHERE firstName LIKE 'a_%';

--- use of Order By clause --
SELECT * 
FROM employeeDetails
ORDER BY employeeID ASC;

SELECT * 
FROM employeeDetails
ORDER BY employeeID DESC;

--- use of Group By clause ---


--- use of Distinct ---
SELECT
DISTINCT firstName 
FROM employeeDetails;

/* Constraints */

--- Unique ---
ALTER TABLE employeeDetails
ADD UNIQUE(employeeKey);

SELECT * 
FROM employeeDetails;

--- Primary ---

/* Use pof join */

CREATE TABLE newEmployeeDetails(
	empId INT PRIMARY KEY,
	city VARCHAR(20),
	mobile INT

);

CREATE TABLE newEmployeeName(
	empId INT PRIMARY KEY,
	firstName VARCHAR(20)
);

INSERT into newEmployeeDetails 
VALUES(
	1001,
	'Patna',
    1234567890
);

INSERT into newEmployeeDetails 
VALUES(
	1002,
	'NY',
    1234567880
);

INSERT into newEmployeeName 
VALUES(
	1001,
    'abc'
);

INSERT into newEmployeeName
VALUES(
	1002,
	'xyz'
);

SELECT *
FROM newEmployeeDetails;

SELECT *
FROM newEmployeeName;

-- Inner join ---
SELECT *
FROM newEmployeeDetails
INNER JOIN newEmployeeName
ON newEmployeeDetails.empId = newEmployeeName.empId;


/* Set Operations */

-- Union ---
SELECT *
FROM newEmployeeDetails
UNION
SELECT * FROM employeeDetails;



---Procedure ---
CREATE PROCEDURE SelectAllEmployee
AS 
SELECT * FROM employeeDetails
GO;