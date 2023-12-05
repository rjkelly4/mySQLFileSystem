# id name parentDir permission userId groupId size | fileType content
#
# Root 19
# 	/CompanyA 10
# 		/Staff 6
# 			/Employees 5
# 				/Team1 2
# 					e1
# 					e2
# 				/Team2 1
# 					e3
# 				duties
# 				schedule
# 			/Managers 1
# 				m1
# 		/Clients 4
# 			clientA
# 			clientB
# 			clientC
# 			/History 1
# 				history
# 	/CompanyB 9
# 		/Staff 7
# 			/Employees 3
# 				/Team1 0
# 				/Team2 1
# 					e1
# 				duties
# 				schedule
# 			/Managers 4
# 				m1
# 				m2
# 				m3
# 				m4
# 		/Clients 1
# 			/History 1
# 				history
# 		/Misc1 0
# 		/Misc2 1
# 			secret
# 		/Misc3 0

INSERT INTO Directory
VALUES ('57d201cf-7f08-11ee-a0eb-0242ac1b0003', 'root', NULL, '000', 1, 1, 19);

INSERT INTO Directory
VALUES ('93f0cac9-7f08-11ee-a0eb-0242ac1b0003', 'companyA', '57d201cf-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 10);
INSERT INTO Directory
VALUES ('9427a8a3-7f08-11ee-a0eb-0242ac1b0003', 'companyB', '57d201cf-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 9);

INSERT INTO Directory
VALUES ('d43aa901-7f08-11ee-a0eb-0242ac1b0003', 'Staff', '93f0cac9-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 6);
INSERT INTO Directory
VALUES ('d46e2d39-7f08-11ee-a0eb-0242ac1b0003', 'Staff', '9427a8a3-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 7);
INSERT INTO Directory
VALUES ('d49eeb08-7f08-11ee-a0eb-0242ac1b0003', 'Clients', '93f0cac9-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 4);
INSERT INTO Directory
VALUES ('d4cfe7e3-7f08-11ee-a0eb-0242ac1b0003', 'Clients', '9427a8a3-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1);
INSERT INTO Directory
VALUES (UUID(), 'Misc1', '9427a8a3-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 0);
INSERT INTO Directory
VALUES (UUID(), 'Misc2', '9427a8a3-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1);
INSERT INTO Directory
VALUES (UUID(), 'Misc3', '9427a8a3-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 0);

INSERT INTO Directory
VALUES ('2cb9136c-7f09-11ee-a0eb-0242ac1b0003', 'Employees', 'd43aa901-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 5);
INSERT INTO Directory
VALUES (UUID(), 'Managers', 'd43aa901-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1);
INSERT INTO Directory
VALUES ('2d0c84b3-7f09-11ee-a0eb-0242ac1b0003', 'Employees', 'd46e2d39-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 3);
INSERT INTO Directory
VALUES (UUID(), 'Managers', 'd46e2d39-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 4);
INSERT INTO Directory
VALUES (UUID(), 'History', 'd49eeb08-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1);
INSERT INTO Directory
VALUES (UUID(), 'History', 'd4cfe7e3-7f08-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1);

INSERT INTO Directory
VALUES ('eb83eba4-7f09-11ee-a0eb-0242ac1b0003', 'Team1', '2cb9136c-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 2);
INSERT INTO Directory
VALUES ('eba0de0b-7f09-11ee-a0eb-0242ac1b0003', 'Team2', '2cb9136c-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1);
INSERT INTO Directory
VALUES (UUID(), 'Team1', '2d0c84b3-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 0);
INSERT INTO Directory
VALUES ('ebde4de7-7f09-11ee-a0eb-0242ac1b0003', 'Team2', '2d0c84b3-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1);

INSERT INTO File
VALUES (UUID(), 'e1', 'eb83eba4-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1, 1, 'A employee 1');
INSERT INTO File
VALUES (UUID(), 'e2', 'eb83eba4-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1, 1, 'A employee 2');

INSERT INTO File
VALUES (UUID(), 'e3', 'eba0de0b-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1, 1, 'A employee 3');

INSERT INTO File
VALUES (UUID(), 'e1', 'ebde4de7-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1, 1, 'B employee 1');

INSERT INTO File
VALUES (UUID(), 'duties', '2cb9136c-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1, 1, 'A employee duties');
INSERT INTO File
VALUES (UUID(), 'schedule', '2cb9136c-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1, 1, 'A employee schedule');
INSERT INTO File
VALUES (UUID(), 'duties', '2d0c84b3-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1, 1, 'B employee duties');
INSERT INTO File
VALUES (UUID(), 'schedule', '2d0c84b3-7f09-11ee-a0eb-0242ac1b0003', '777', 1, 1, 1, 1, 'B employee schedule');