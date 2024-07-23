DROP TABLE IF EXISTS student ;

CREATE TABLE student (
    id BIGSERIAL PRIMARY KEY,
    student_id  BIGINT NOT NULL,
    first_name VARCHAR(30),
    last_name  VARCHAR(60),
    first_score DECIMAL(4,2),
    second_score DECIMAL(4,2),
    third_score DECIMAL(4,2),
    fourth_score DECIMAL(4,2)
);

CREATE TABLE approved_student (
                         id BIGSERIAL PRIMARY KEY,
                         student_id  BIGINT NOT NULL,
                         first_name VARCHAR(30),
                         last_name  VARCHAR(60),
                         final_score DECIMAL(4,2),
                         approved_on_score_rounding BOOLEAN
);

CREATE TABLE reproved_student (
                                  id BIGSERIAL PRIMARY KEY,
                                  student_id  BIGINT NOT NULL,
                                  first_name VARCHAR(30),
                                  last_name  VARCHAR(60),
                                  final_score DECIMAL(4,2)
);