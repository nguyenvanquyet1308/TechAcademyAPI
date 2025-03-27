-- Insert Instructors
INSERT INTO instructors (id, name, title, bio, email, phone, avatar, specialty, linkedin, github, created_at, updated_at) 
VALUES 
(1, 'Nguyễn Văn A', 'Senior Frontend Developer', 'Chuyên gia phát triển frontend với hơn 8 năm kinh nghiệm. Đã tham gia phát triển nhiều ứng dụng web lớn và phức tạp.', 'nguyenvana@techzen.edu.vn', '0901234567', 'https://randomuser.me/api/portraits/men/32.jpg', 'Web Development', 'https://linkedin.com/in/nguyenvana', 'https://github.com/nguyenvana', NOW(), NOW()),
(2, 'Trần Thị B', 'Mobile App Developer', 'Chuyên gia phát triển ứng dụng di động với 5 năm kinh nghiệm trong các dự án React Native và Flutter.', 'tranthib@techzen.edu.vn', '0901234568', 'https://randomuser.me/api/portraits/women/44.jpg', 'Mobile Development', 'https://linkedin.com/in/tranthib', 'https://github.com/tranthib', NOW(), NOW()),
(3, 'Lê Văn C', 'Database Architect', 'Chuyên gia về cơ sở dữ liệu với hơn 10 năm kinh nghiệm. Thành thạo cả SQL và NoSQL, cũng như các kỹ thuật tối ưu hóa hiệu suất.', 'levanc@techzen.edu.vn', '0901234569', 'https://randomuser.me/api/portraits/men/68.jpg', 'Database Management', 'https://linkedin.com/in/levanc', 'https://github.com/levanc', NOW(), NOW()),
(4, 'Phạm Thị D', 'DevOps Engineer', 'Chuyên gia về DevOps với hơn 6 năm kinh nghiệm. Thành thạo các công cụ CI/CD, container và orchestration.', 'phamthid@techzen.edu.vn', '0901234570', 'https://randomuser.me/api/portraits/women/65.jpg', 'DevOps', 'https://linkedin.com/in/phamthid', 'https://github.com/phamthid', NOW(), NOW());

-- Insert Instructor Skills
INSERT INTO instructor_skills (instructor_id, skill) VALUES 
(1, 'HTML/CSS'), (1, 'JavaScript'), (1, 'Vue.js'), (1, 'React'), (1, 'Angular'),
(2, 'React Native'), (2, 'Flutter'), (2, 'JavaScript'), (2, 'TypeScript'), (2, 'Firebase'),
(3, 'SQL'), (3, 'MongoDB'), (3, 'PostgreSQL'), (3, 'Redis'), (3, 'Database Design'),
(4, 'Docker'), (4, 'Kubernetes'), (4, 'Jenkins'), (4, 'AWS'), (4, 'CI/CD');

-- Insert Courses
INSERT INTO courses (id, name, description, price, thumbnail, duration, level, instructor_id, is_active, created_at, updated_at) 
VALUES 
(1, 'React Native cơ bản', 'Khóa học từ cơ bản đến nâng cao về React Native, giúp bạn xây dựng ứng dụng di động đa nền tảng một cách nhanh chóng và hiệu quả.', 1500000.00, 'https://example.com/images/react-native.jpg', 0, 'Beginner', 2, true, NOW(), NOW()),
(2, 'Vue.js Development', 'Khóa học toàn diện về Vue.js, giúp bạn xây dựng các ứng dụng web hiện đại với kiến trúc component.', 1200000.00, 'https://example.com/images/vuejs.jpg', 0, 'Intermediate', 1, true, NOW(), NOW()),
(3, 'SQL và Quản trị cơ sở dữ liệu', 'Khóa học dành cho những ai muốn trở thành chuyên gia về cơ sở dữ liệu. Bao gồm các kỹ thuật tối ưu hóa, thiết kế schema và quản lý hiệu suất.', 1800000.00, 'https://example.com/images/sql.jpg', 0, 'Advanced', 3, true, NOW(), NOW()),
(4, 'DevOps với Docker và Kubernetes', 'Khóa học thực hành về DevOps, tập trung vào container hóa ứng dụng với Docker và triển khai với Kubernetes.', 2000000.00, 'https://example.com/images/devops.jpg', 0, 'Intermediate', 4, true, NOW(), NOW());

-- Insert Course Chapters and Lessons for React Native course
-- Chapter 1: Cơ bản về React Native
INSERT INTO course_chapters (id, title, position, course_id, created_at, updated_at)
VALUES (1, 'Cơ bản về React Native', 1, 1, NOW(), NOW());

INSERT INTO course_lessons (title, duration, position, chapter_id, content, video_url, created_at, updated_at)
VALUES 
('Giới thiệu React Native', 15, 1, 1, 'Giới thiệu về React Native, lịch sử phát triển và lợi ích của nó.', 'https://example.com/videos/intro-rn.mp4', NOW(), NOW()),
('Cài đặt môi trường phát triển', 25, 2, 1, 'Hướng dẫn cài đặt Node.js, npm, React Native CLI và các công cụ cần thiết.', 'https://example.com/videos/setup-rn.mp4', NOW(), NOW()),
('Ứng dụng React Native đầu tiên', 20, 3, 1, 'Tạo và chạy ứng dụng React Native đầu tiên trên iOS và Android.', 'https://example.com/videos/first-app-rn.mp4', NOW(), NOW());

-- Chapter 2: UI Components
INSERT INTO course_chapters (id, title, position, course_id, created_at, updated_at)
VALUES (2, 'UI Components', 2, 1, NOW(), NOW());

INSERT INTO course_lessons (title, duration, position, chapter_id, content, video_url, created_at, updated_at)
VALUES 
('React Native Core Components', 30, 1, 2, 'Tìm hiểu về các core components: View, Text, Image, ScrollView, TextInput.', 'https://example.com/videos/core-components.mp4', NOW(), NOW()),
('Styling trong React Native', 25, 2, 2, 'Học cách sử dụng StyleSheet và styling components trong React Native.', 'https://example.com/videos/styling-rn.mp4', NOW(), NOW()),
('FlexBox Layout', 20, 3, 2, 'Hiểu và áp dụng FlexBox layout trong React Native apps.', 'https://example.com/videos/flexbox-rn.mp4', NOW(), NOW());

-- Insert Students
INSERT INTO students (id, name, email, phone, avatar, course_id, status, enrollment_date, notes, created_at, updated_at) 
VALUES 
(1, 'Nguyễn Văn X', 'nguyenvanx@example.com', '0901234567', 'https://randomuser.me/api/portraits/men/32.jpg', 1, 'ACTIVE', '2023-01-15', 'Học viên có tiềm năng cao', NOW(), NOW()),
(2, 'Trần Thị Y', 'tranthiy@example.com', '0901234568', 'https://randomuser.me/api/portraits/women/44.jpg', 2, 'COMPLETED', '2022-12-01', 'Đã hoàn thành xuất sắc khóa học', NOW(), NOW()),
(3, 'Lê Văn Z', 'levanz@example.com', '0901234569', 'https://randomuser.me/api/portraits/men/68.jpg', 3, 'PENDING', '2023-02-20', 'Chờ xác nhận thanh toán', NOW(), NOW()),
(4, 'Phạm Thị K', 'phamthik@example.com', '0901234570', 'https://randomuser.me/api/portraits/women/65.jpg', 4, 'DROPPED', '2023-01-10', 'Đã nghỉ học do lý do cá nhân', NOW(), NOW());

-- Insert Blog Posts
INSERT INTO blog_posts (id, title, slug, content, thumbnail, author_id, published, published_at, created_at, updated_at) 
VALUES 
(1, 'Tương lai của React Native trong năm 2023', 'tuong-lai-cua-react-native-trong-nam-2023', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam euismod, nisl eget ultricies aliquam, nunc nisl aliquet nunc, vitae aliquam nisl nunc vitae nisl. Nullam euismod, nisl eget ultricies aliquam, nunc nisl aliquet nunc, vitae aliquam nisl nunc vitae nisl.', 'https://example.com/images/blog-rn.jpg', 2, true, NOW(), NOW(), NOW()),
(2, 'So sánh Vue.js với React: Đâu là sự lựa chọn tốt nhất?', 'so-sanh-vuejs-voi-react', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam euismod, nisl eget ultricies aliquam, nunc nisl aliquet nunc, vitae aliquam nisl nunc vitae nisl. Nullam euismod, nisl eget ultricies aliquam, nunc nisl aliquet nunc, vitae aliquam nisl nunc vitae nisl.', 'https://example.com/images/blog-vue-react.jpg', 1, true, NOW(), NOW(), NOW()),
(3, 'Các xu hướng DevOps trong năm 2023', 'cac-xu-huong-devops-trong-nam-2023', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam euismod, nisl eget ultricies aliquam, nunc nisl aliquet nunc, vitae aliquam nisl nunc vitae nisl. Nullam euismod, nisl eget ultricies aliquam, nunc nisl aliquet nunc, vitae aliquam nisl nunc vitae nisl.', 'https://example.com/images/blog-devops.jpg', 4, false, NULL, NOW(), NOW());

-- Insert Blog Post Tags
INSERT INTO blog_post_tags (blog_post_id, tag) VALUES 
(1, 'React Native'), (1, 'Mobile Development'), (1, '2023'),
(2, 'Vue.js'), (2, 'React'), (2, 'JavaScript'), (2, 'Framework'),
(3, 'DevOps'), (3, 'Docker'), (3, 'Kubernetes'), (3, 'CI/CD');

-- Update course durations based on lesson durations
UPDATE courses c
SET c.duration = (
    SELECT SUM(l.duration)
    FROM course_lessons l
    JOIN course_chapters ch ON l.chapter_id = ch.id
    WHERE ch.course_id = c.id
)
WHERE c.id = 1; 