DO
'
DECLARE
BEGIN
IF NOT EXISTS (SELECT FROM "administrator") THEN
    INSERT INTO "administrator"
        (uuid, first_name, identifier, identifier_type, last_name, status, email, image_url)
    VALUES (gen_random_uuid(),
        ''Jezer'',
        ''C0298234'',
        ''PASSPORT'',
        ''Mejía'',
        ''ACTIVE'',
        ''00543224@uca.edu.sv'',
        NULL);
    END IF;
END;
' LANGUAGE PLPGSQL;

DO
'
DECLARE
BEGIN
IF NOT EXISTS (SELECT FROM "terminal") THEN
    INSERT INTO "terminal"
        (uuid, password, type)
    VALUES
        (gen_random_uuid(),
            ''Usuario123.'',
            ''BARRIER''),
        (gen_random_uuid(),
            ''Usuario123.'',
            ''DOOR''),
        (gen_random_uuid(),
            ''Usuario123.'',
            ''MANUAL'');
    END IF;
END;
' LANGUAGE PLPGSQL;
