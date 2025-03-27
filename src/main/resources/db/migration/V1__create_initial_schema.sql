-- Instructors table
CREATE TABLE instructors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    bio TEXT,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(50),
    avatar VARCHAR(255),
    specialty VARCHAR(100),
    linkedin VARCHAR(255),
    github VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Instructor skills table
CREATE TABLE instructor_skills (
    instructor_id BIGINT NOT NULL,
    skill VARCHAR(100) NOT NULL,
    PRIMARY KEY (instructor_id, skill),
    FOREIGN KEY (instructor_id) REFERENCES instructors(id) ON DELETE CASCADE
);

-- Courses table
CREATE TABLE courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    thumbnail VARCHAR(255),
    duration INT,
    level VARCHAR(50),
    instructor_id BIGINT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (instructor_id) REFERENCES instructors(id)
);

-- Course chapters table
CREATE TABLE course_chapters (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    position INT,
    course_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Course lessons table
CREATE TABLE course_lessons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    duration INT,
    position INT,
    chapter_id BIGINT NOT NULL,
    content TEXT,
    video_url VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (chapter_id) REFERENCES course_chapters(id) ON DELETE CASCADE
);

-- Students table
CREATE TABLE students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(50) NOT NULL,
    avatar VARCHAR(255),
    course_id BIGINT,
    status VARCHAR(20),
    enrollment_date DATE,
    notes TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- Blog posts table
CREATE TABLE blog_posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL UNIQUE,
    content TEXT NOT NULL,
    thumbnail VARCHAR(255),
    author_id BIGINT,
    published BOOLEAN DEFAULT FALSE,
    published_at TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES instructors(id)
);

-- Blog post tags table
CREATE TABLE blog_post_tags (
    blog_post_id BIGINT NOT NULL,
    tag VARCHAR(100) NOT NULL,
    PRIMARY KEY (blog_post_id, tag),
    FOREIGN KEY (blog_post_id) REFERENCES blog_posts(id) ON DELETE CASCADE
); 