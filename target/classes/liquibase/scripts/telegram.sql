-- liquibase formatted sql

-- changeset asharipov: create a table for notifications
CREATE TABLE notification_task (
    id SERIAL PRIMARY KEY,
    chat_id INT NOT NULL,
    message TEXT NOT NULL,
    scheduled_time TIMESTAMP NOT NULL
);
