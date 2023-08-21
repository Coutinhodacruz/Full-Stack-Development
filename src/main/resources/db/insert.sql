SET FOREIGN_KEY_CHECKS = 0;

truncate table student;

INSERT INTO student(`id`, `department`, `email`, `firstName`, `lastName`, `password`) VALUES
(500, 'Soft Engineering', 'Coutinho@gmail.com', 'Coutinho', 'Dacruz', 'password');
-- (501, 'Bobby', 'Firmino', 'Bobby@gmail.com', 'password', 'Engineering'),
-- (502, 'Trent', 'Arnold', 'Trent@gmail.com', 'password', 'Developer'),
-- (503, 'Robertson', 'Andy', 'Robertson@gmail.com', 'password', 'Back End'),
-- (504, 'Luiz', 'Diogo', 'Diogo@gmail.com', 'password', 'Front End'),
-- (505, 'Nunez', 'Cody', 'Cody@gmail.com', 'password', 'Engineering');
--
