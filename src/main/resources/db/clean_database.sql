-- Drop tables in correct order to avoid foreign key constraints
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS blog_post_tags;
DROP TABLE IF EXISTS blog_posts;
DROP TABLE IF EXISTS instructor_skills;
DROP TABLE IF EXISTS course_lessons;
DROP TABLE IF EXISTS course_chapters;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS instructors;
DROP TABLE IF EXISTS flyway_schema_history;

SET FOREIGN_KEY_CHECKS = 1; 