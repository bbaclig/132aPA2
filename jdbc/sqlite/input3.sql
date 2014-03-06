PRAGMA foreign_keys = OFF;
.mode columns
.headers on
.width 20 20 20
DROP TABLE IF EXISTS Core; 
DROP TABLE IF EXISTS Elective;
DROP TABLE IF EXISTS Prerequisite;
DROP TABLE IF EXISTS Record;
DROP VIEW IF EXISTS Core; 
DROP VIEW IF EXISTS Elective;
DROP VIEW IF EXISTS Prerequisite;
DROP VIEW IF EXISTS Record;

CREATE TABLE Core (Course CHAR(32));
CREATE TABLE Elective (Course CHAR(32));
CREATE TABLE Prerequisite (Course CHAR(32), Prereq CHAR(32));
CREATE TABLE Record (
	Student CHAR(32), 
	Course CHAR(32),
	PRIMARY KEY (Student, Course)
);

PRAGMA foreign_keys = ON;

INSERT INTO Core VALUES ('core_1');
INSERT INTO Core VALUES ('core_2');
INSERT INTO Core VALUES ('core_3');
INSERT INTO Core VALUES ('core_4');
INSERT INTO Core VALUES ('core_5');
INSERT INTO Core VALUES ('core_6');
INSERT INTO Core VALUES ('core_7');
INSERT INTO Core VALUES ('core_8');
INSERT INTO Core VALUES ('core_9');
INSERT INTO Core VALUES ('core_10');

INSERT INTO Elective VALUES ('elective_1'); 
INSERT INTO Elective VALUES ('elective_2'); 
INSERT INTO Elective VALUES ('elective_3'); 
INSERT INTO Elective VALUES ('elective_4'); 
INSERT INTO Elective VALUES ('elective_5');

INSERT INTO Prerequisite VALUES ('core_1', 'core_0');
INSERT INTO Prerequisite VALUES ('core_2', 'core_1');
INSERT INTO Prerequisite VALUES ('core_3', 'core_2');
INSERT INTO Prerequisite VALUES ('core_4', 'core_3');
INSERT INTO Prerequisite VALUES ('core_5', 'core_4');
INSERT INTO Prerequisite VALUES ('core_6', 'core_5');
INSERT INTO Prerequisite VALUES ('core_7', 'core_6');
INSERT INTO Prerequisite VALUES ('core_8', 'core_7');
INSERT INTO Prerequisite VALUES ('core_9', 'core_8');
INSERT INTO Prerequisite VALUES ('core_10', 'core_9');
INSERT INTO Prerequisite VALUES ('elective_1', 'core_0');
INSERT INTO Prerequisite VALUES ('elective_2', 'core_0');
INSERT INTO Prerequisite VALUES ('elective_3', 'core_0');
INSERT INTO Prerequisite VALUES ('elective_4', 'core_0');
INSERT INTO Prerequisite VALUES ('elective_5', 'core_0');

INSERT INTO Record VALUES ('student_1', 'core_0');
