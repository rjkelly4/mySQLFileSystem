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

INSERT INTO UserGroup
VALUES ('4e935332-8fd5-11ee-9fdd-0242ac120003', 'root');

INSERT INTO User
VALUES ('0a5e53ea-8fd7-11ee-9fdd-0242ac120003', 'root', 'kmbui2@wisc.edu', 'notguest', '4e935332-8fd5-11ee-9fdd-0242ac120003');

INSERT INTO Directory
VALUES ('57d201cf-7f08-11ee-a0eb-0242ac1b0003', 'root', NULL, '000', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 19);

INSERT INTO Directory
VALUES ('93f0cac9-7f08-11ee-a0eb-0242ac1b0003', 'companyA', '57d201cf-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 10);
INSERT INTO Directory
VALUES ('9427a8a3-7f08-11ee-a0eb-0242ac1b0003', 'companyB', '57d201cf-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 9);

INSERT INTO Directory
VALUES ('d43aa901-7f08-11ee-a0eb-0242ac1b0003', 'Staff', '93f0cac9-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 6);
INSERT INTO Directory
VALUES ('d46e2d39-7f08-11ee-a0eb-0242ac1b0003', 'Staff', '9427a8a3-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 7);
INSERT INTO Directory
VALUES ('d49eeb08-7f08-11ee-a0eb-0242ac1b0003', 'Clients', '93f0cac9-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 4);
INSERT INTO Directory
VALUES ('d4cfe7e3-7f08-11ee-a0eb-0242ac1b0003', 'Clients', '9427a8a3-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1);
INSERT INTO Directory
VALUES (UUID(), 'Misc1', '9427a8a3-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 0);
INSERT INTO Directory
VALUES ('98898faf-835c-11ee-9fdd-0242ac120003', 'Misc2', '9427a8a3-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1);
INSERT INTO Directory
VALUES (UUID(), 'Misc3', '9427a8a3-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 0);

INSERT INTO Directory
VALUES ('2cb9136c-7f09-11ee-a0eb-0242ac1b0003', 'Employees', 'd43aa901-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 5);
INSERT INTO Directory
VALUES ('994af418-835c-11ee-9fdd-0242ac120003', 'Managers', 'd43aa901-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1);
INSERT INTO Directory
VALUES ('2d0c84b3-7f09-11ee-a0eb-0242ac1b0003', 'Employees', 'd46e2d39-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 3);
INSERT INTO Directory
VALUES ('99eca125-835c-11ee-9fdd-0242ac120003', 'Managers', 'd46e2d39-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 4);
INSERT INTO Directory
VALUES ('9a2b4f75-835c-11ee-9fdd-0242ac120003', 'History', 'd49eeb08-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1);
INSERT INTO Directory
VALUES ('9a6a5397-835c-11ee-9fdd-0242ac120003', 'History', 'd4cfe7e3-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1);

INSERT INTO Directory
VALUES ('eb83eba4-7f09-11ee-a0eb-0242ac1b0003', 'Team1', '2cb9136c-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 2);
INSERT INTO Directory
VALUES ('eba0de0b-7f09-11ee-a0eb-0242ac1b0003', 'Team2', '2cb9136c-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1);
INSERT INTO Directory
VALUES (UUID(), 'Team1', '2d0c84b3-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 0);
INSERT INTO Directory
VALUES ('ebde4de7-7f09-11ee-a0eb-0242ac1b0003', 'Team2', '2d0c84b3-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1);

INSERT INTO File
VALUES (UUID(), 'e1', 'eb83eba4-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'A employee 1');
INSERT INTO File
VALUES (UUID(), 'e2', 'eb83eba4-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'A employee 2');

INSERT INTO File
VALUES (UUID(), 'e3', 'eba0de0b-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'A employee 3');

INSERT INTO File
VALUES (UUID(), 'e1', 'ebde4de7-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'B employee 1');

INSERT INTO File
VALUES (UUID(), 'duties', '2cb9136c-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'A employee duties');
INSERT INTO File
VALUES (UUID(), 'schedule', '2cb9136c-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'A employee schedule');
INSERT INTO File
VALUES (UUID(), 'duties', '2d0c84b3-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'B employee duties');
INSERT INTO File
VALUES (UUID(), 'schedule', '2d0c84b3-7f09-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'B employee schedule');

INSERT INTO File
VALUES (UUID(), 'm1', '994af418-835c-11ee-9fdd-0242ac120003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'A manager 1');
INSERT INTO File
VALUES (UUID(), 'm1', '99eca125-835c-11ee-9fdd-0242ac120003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'B manager 1');
INSERT INTO File
VALUES (UUID(), 'm2', '99eca125-835c-11ee-9fdd-0242ac120003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'B manager 2');
INSERT INTO File
VALUES (UUID(), 'm3', '99eca125-835c-11ee-9fdd-0242ac120003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'B manager 3');
INSERT INTO File
VALUES (UUID(), 'm4', '99eca125-835c-11ee-9fdd-0242ac120003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'B manager 4');

INSERT INTO File
VALUES (UUID(), 'clientA', 'd49eeb08-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'A clientA');
INSERT INTO File
VALUES (UUID(), 'clientB', 'd49eeb08-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'A clientB');
INSERT INTO File
VALUES (UUID(), 'clientC', 'd49eeb08-7f08-11ee-a0eb-0242ac1b0003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'A clientC');

INSERT INTO File
VALUES (UUID(), 'history', '9a2b4f75-835c-11ee-9fdd-0242ac120003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'A history');
INSERT INTO File
VALUES (UUID(), 'history', '9a6a5397-835c-11ee-9fdd-0242ac120003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'B history');

INSERT INTO File
VALUES (UUID(), 'secret', '98898faf-835c-11ee-9fdd-0242ac120003', '777', '0a5e53ea-8fd7-11ee-9fdd-0242ac120003', '4e935332-8fd5-11ee-9fdd-0242ac120003', 1, 1, 'Admin secret');