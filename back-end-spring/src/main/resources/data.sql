INSERT INTO app_user (id, email, git_profile, journey_profile, password, user_name)
VALUES ('68229fa9-e9bc-4c8a-94c4-25aca83a30a9', 'testemail@gmail.com', 'testgitprofile', 'testjourneyprofile',
        'testpassword', 'testuser');
INSERT INTO app_user (id, email, git_profile, journey_profile, password, user_name)
VALUES ('68229fa9-e9bc-4c8a-94c4-25aca83a30a1', 'testemail2@gmail.com', 'testgitprofile2', 'testjourneyprofile2',
        'testpassword2', 'testuser2');
INSERT INTO project (id, finished, git_repo, project_name, team_name)
VALUES ('68229fa9-e9bc-4c8a-94c4-20aca83a30a9', false, 'testgitrepo', 'testproject', 'testteam');
INSERT INTO project (id, finished, git_repo, project_name, team_name)
VALUES ('68229fa9-e9bc-4c8a-94c4-21aca83a30a9', false, 'testgitrepo2', 'testproject2', 'testteam2');
INSERT INTO project_message (id, message, timestamp, app_user_id, project_id)
VALUES ('68229fa9-e9bc-4c8a-94c4-22aca83a30a9', 'message1', '2022-12-01 11:28:05',
        '68229fa9-e9bc-4c8a-94c4-25aca83a30a9', '68229fa9-e9bc-4c8a-94c4-20aca83a30a9');
INSERT INTO project_message (id, message, timestamp, app_user_id, project_id)
VALUES ('68229fa9-e9bc-4c8a-94c4-23aca83a30a9', 'message2', '2022-12-01 11:29:05',
        '68229fa9-e9bc-4c8a-94c4-25aca83a30a1', '68229fa9-e9bc-4c8a-94c4-20aca83a30a9');
INSERT INTO project_message (id, message, timestamp, app_user_id, project_id)
VALUES ('68229fa9-e9bc-4c8a-94c4-27aca83a30a9', 'message4', '2022-12-01 11:15:05',
        '68229fa9-e9bc-4c8a-94c4-25aca83a30a1', '68229fa9-e9bc-4c8a-94c4-20aca83a30a9');
INSERT INTO project_message (id, message, timestamp, app_user_id, project_id)
VALUES ('68229fa9-e9bc-4c8a-94c4-24aca83a30a9', 'message3', '2022-11-01 11:25:05',
        '68229fa9-e9bc-4c8a-94c4-25aca83a30a1', '68229fa9-e9bc-4c8a-94c4-21aca83a30a9');