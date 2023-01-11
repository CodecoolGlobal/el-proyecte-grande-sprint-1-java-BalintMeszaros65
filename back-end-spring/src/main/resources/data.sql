INSERT INTO app_user (id, account_non_expired, account_non_locked, credentials_non_expired, email, enabled,
                      git_profile, journey_profile, password, user_name)
VALUES ('68229fa9-e9bc-4c8a-94c4-25aca83a30a9', true, true, true, 'testemail@gmail.com', true, 'testgitprofile', 'testjourneyprofile',
        'testpassword', 'testuser');
INSERT INTO app_user (id, account_non_expired, account_non_locked, credentials_non_expired, email, enabled,
                      git_profile, journey_profile, password, user_name)
VALUES ('68229fa9-e9bc-4c8a-94c4-25aca83a30a1', true, true, true, 'testemail2@gmail.com', true, 'testgitprofile2', 'testjourneyprofile2',
        'testpassword2', 'testuser2');

INSERT INTO app_user_authorities(app_user_id, authorities)
VALUES ('68229fa9-e9bc-4c8a-94c4-25aca83a30a9', decode('ACED0005737200426F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E617574686F726974792E53696D706C654772616E746564417574686F72697479000000000000023A0200014C0004726F6C657400124C6A6176612F6C616E672F537472696E673B7870740009524F4C455F55534552', 'hex'));
INSERT INTO app_user_authorities(app_user_id, authorities)
VALUES ('68229fa9-e9bc-4c8a-94c4-25aca83a30a1', decode('ACED0005737200426F72672E737072696E676672616D65776F726B2E73656375726974792E636F72652E617574686F726974792E53696D706C654772616E746564417574686F72697479000000000000023A0200014C0004726F6C657400124C6A6176612F6C616E672F537472696E673B7870740009524F4C455F55534552', 'hex'));
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
