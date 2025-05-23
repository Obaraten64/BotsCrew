INSERT INTO department(name) VALUES ('IT'), ('MONEY'), ('DIRECTORS');

INSERT INTO lector(full_name, department, salary, position)
    VALUES ('Ivan Petrenko', 1, 24.4, 0);
INSERT INTO lector(full_name, department, salary, position)
    VALUES ('Petro Ivanov', 2, 26.6, 1);
INSERT INTO lector(full_name, department, salary, position)
    VALUES ('Vadim Liakh', 3, 26.6, 2);
INSERT INTO lector(full_name, department, salary, position)
    VALUES ('Taras Liakh', 3, 26.6, 1);
INSERT INTO lector(full_name, department, salary, position)
    VALUES ('Natalia Liakh', 1, 26.6, 1);

UPDATE department SET head = 5 WHERE id = 1;
UPDATE department SET head = 3 WHERE id = 3;
UPDATE department SET head = 2 WHERE id = 2;