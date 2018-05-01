/*
    Database initialization script that runs on every web-application redeployment.
*/
DROP TABLE IF EXISTS works;
DROP TABLE IF EXISTS poets;

CREATE TABLE poets (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE works (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    published_date INT NOT NULL,
    poet_id INT NOT NULL,
	FOREIGN KEY (poet_id) REFERENCES poets(id)
);

INSERT INTO poets (name, email, password) VALUES
	('poet1','poet1@poet1', 'poet1'), -- 1
	('poet2','poet2@user2', 'poet2'), -- 2
	('poet3','poet3@poet3', 'poet3'); -- 3

INSERT INTO works (title, content, published_date, poet_id) VALUES
	('title1','content1',1995,1),   -- 1
	('title2','content2',2000,1),  -- 2
	('title3','content3',2005,2), -- 3
	('title4','content4',2010,2),   -- 4
	('title5','content5',2015,3);   -- 5
