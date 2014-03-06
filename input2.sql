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

INSERT INTO Core VALUES ('Java Programming');
INSERT INTO Core VALUES ('Data Structure'); 
INSERT INTO Core VALUES ('Discrete Math'); 
INSERT INTO Core VALUES ('Operating System'); 
INSERT INTO Core VALUES ('Database'); 
INSERT INTO Core VALUES ('Architecture'); 
INSERT INTO Core VALUES ('Artificial Intelligence');

INSERT INTO Elective VALUES ('Computer Vision');
INSERT INTO Elective VALUES ('Abstract Algebra');
INSERT INTO Elective VALUES ('Complex Analysis');
INSERT INTO Elective VALUES ('Intro. Psychology');
INSERT INTO Elective VALUES ('Phonetics');
INSERT INTO Elective VALUES ('Phonology');
INSERT INTO Elective VALUES ('Web Programming');

INSERT INTO Prerequisite VALUES ('Data Structure', 'Java Programming');
INSERT INTO Prerequisite VALUES ('Operating System', 'Architecture');
INSERT INTO Prerequisite VALUES ('Architecture', 'Java Programming');
INSERT INTO Prerequisite VALUES ('Architecture', 'Data Structure');
INSERT INTO Prerequisite VALUES ('Database', 'Discrete Math');
INSERT INTO Prerequisite VALUES ('Database', 'Operating System');
INSERT INTO Prerequisite VALUES ('Artificial Intelligence', 'Discrete Math');
INSERT INTO Prerequisite VALUES ('Web Programming', 'Database');
INSERT INTO Prerequisite VALUES ('Computer Vision', 'Artificial Intelligence');

INSERT INTO Record VALUES ('John', 'Java Programming');
INSERT INTO Record VALUES ('Jane', 'Discrete Math');
