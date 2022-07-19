CREATE TABLE IF NOT EXISTS participant
(
    id                  BIGINT PRIMARY KEY ,
    participant_name    VARCHAR(200) NOT NULL ,
    age                 INTEGER NOT NULL ,
    city                VARCHAR(20)  NOT NULL
);

CREATE TABLE IF NOT EXISTS winner_data
(
    id                  BIGINT PRIMARY KEY ,
    participant_id      BIGINT NOT NULL ,
    lottery_money       INTEGER NOT NULL,
    constraint fk_participant
    foreign key (participant_id)
    references participant(id)
);



CREATE SEQUENCE IF NOT EXISTS HIBERNATE_SEQUENCE
minvalue 100000
maxvalue 9999999999999999
start with 100060
increment by 1
cache 20;