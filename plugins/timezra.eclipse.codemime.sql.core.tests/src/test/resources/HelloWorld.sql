/*
 * Block comment
 */
-- single line comment
CREATE TABLE salutation(id int, greeting varchar, person varchar);

INSERT INTO tab values(1, 'Hello', 'World');

SELECT greeting AS "MY_GREETING", person AS "MY_PERSON" FROM salutation;