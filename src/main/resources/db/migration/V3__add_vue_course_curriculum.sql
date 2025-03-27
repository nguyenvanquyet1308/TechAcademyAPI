-- Add Chapters and Lessons for Vue.js course (Course ID 2)

-- Chapter 1: Giới thiệu Vue.js
INSERT INTO course_chapters (id, title, position, course_id, created_at, updated_at)
VALUES (3, 'Giới thiệu Vue.js', 1, 2, NOW(), NOW());

INSERT INTO course_lessons (title, duration, position, chapter_id, content, video_url, created_at, updated_at)
VALUES 
('Giới thiệu về Vue.js', 15, 1, 3, 'Giới thiệu tổng quan về Vue.js, lịch sử phát triển và các ưu điểm.', 'https://example.com/videos/intro-vue.mp4', NOW(), NOW()),
('Cài đặt và khởi tạo dự án Vue', 20, 2, 3, 'Cài đặt Vue CLI và tạo dự án Vue.js đầu tiên.', 'https://example.com/videos/setup-vue.mp4', NOW(), NOW()),
('Cấu trúc dự án Vue', 15, 3, 3, 'Tìm hiểu về cấu trúc thư mục và cách tổ chức mã trong dự án Vue.', 'https://example.com/videos/vue-structure.mp4', NOW(), NOW());

-- Chapter 2: Cơ bản về Vue.js
INSERT INTO course_chapters (id, title, position, course_id, created_at, updated_at)
VALUES (4, 'Cơ bản về Vue.js', 2, 2, NOW(), NOW());

INSERT INTO course_lessons (title, duration, position, chapter_id, content, video_url, created_at, updated_at)
VALUES 
('Làm việc với Template', 25, 1, 4, 'Tìm hiểu về cú pháp template và binding dữ liệu trong Vue.js.', 'https://example.com/videos/vue-template.mp4', NOW(), NOW()),
('Components trong Vue', 30, 2, 4, 'Xây dựng và sử dụng components trong Vue.js.', 'https://example.com/videos/vue-components.mp4', NOW(), NOW()),
('Props và Events', 25, 3, 4, 'Truyền dữ liệu giữa các components qua props và events.', 'https://example.com/videos/vue-props-events.mp4', NOW(), NOW());

-- Chapter 3: State Management
INSERT INTO course_chapters (id, title, position, course_id, created_at, updated_at)
VALUES (5, 'State Management', 3, 2, NOW(), NOW());

INSERT INTO course_lessons (title, duration, position, chapter_id, content, video_url, created_at, updated_at)
VALUES 
('Quản lý State với Vuex', 35, 1, 5, 'Tìm hiểu về Vuex và cách quản lý state trong ứng dụng Vue.js.', 'https://example.com/videos/vuex-intro.mp4', NOW(), NOW()),
('Actions và Mutations', 30, 2, 5, 'Thực hành với Actions và Mutations trong Vuex.', 'https://example.com/videos/vuex-actions.mp4', NOW(), NOW()),
('Modules trong Vuex', 25, 3, 5, 'Tổ chức state phức tạp với Vuex modules.', 'https://example.com/videos/vuex-modules.mp4', NOW(), NOW());

-- Update Vue.js course duration
UPDATE courses c
SET c.duration = (
    SELECT SUM(l.duration)
    FROM course_lessons l
    JOIN course_chapters ch ON l.chapter_id = ch.id
    WHERE ch.course_id = c.id
)
WHERE c.id = 2; 