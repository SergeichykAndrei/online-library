create sequence account_id_seq;

create sequence author_id_seq;

create sequence genre_id_seq;

create sequence role_id_seq;

create sequence book_id_seq;

create sequence comment_id_seq;

create sequence mark_book_id_seq;

create sequence account_book_id_seq;

SET SEARCH_PATH TO library_storage;

create table author
(
  id   bigserial    not null
    constraint author_pkey
    primary key,
  name varchar(255) not null
);

create unique index author_name_uindex
  on author (name);

create table genre
(
  id   bigserial not null
    constraint genre_pkey
    primary key,
  name varchar(255)
);

create unique index genre_name_uindex
  on genre (name);

create table role
(
  id   serial       not null
    constraint role_pkey
    primary key,
  name varchar(255) not null
);

create table account
(
  id       bigserial    not null
    constraint account_pkey
    primary key,
  email    varchar(255),
  phone    varchar(255),
  password varchar(255),
  username varchar(255) not null,
  role_id  integer
    constraint fkd4vb66o896tay3yy52oqxr9w0
    references role
);

create unique index account_username_uindex
  on account (username);

create unique index role_name_uindex
  on role (name);

create table book
(
  id            bigserial not null
    constraint book_pkey
    primary key,
  description   text,
  image         varchar(255),
  name          varchar(255),
  number_copies integer,
  author_id     bigint
    constraint fkklnrv3weler2ftkweewlky958
    references author,
  genre_id      bigint
    constraint fkm1t3yvw5i7olwdf32cwuul7ta
    references genre,
  version       bigint default 1
);

create table comment
(
  id         bigserial not null
    constraint comment_pkey
    primary key,
  comment    varchar(255),
  account_id bigint
    constraint fkp41h5al2ajp1q0u6ox3i68w61
    references account,
  book_id    bigint
    constraint fkkko96rdq8d82wm91vh2jsfak7
    references book
);

create table mark_book
(
  id      bigserial not null
    constraint mark_book_pkey
    primary key,
  value   integer,
  book_id bigint
    constraint fkl3s11bfu681a400mb0x00crdm
    references book
);

create table account_book
(
  id          bigserial not null
    constraint account_book_pkey
    primary key,
  account_id  bigint    not null
    constraint fk2h7rw8t5m0kao83h9pibt7hxs
    references account,
  date_issue  date,
  date_return date,
  book_id     bigint    not null
    constraint fk3urkjo83dg7chbrj112i4sjj9
    references book
);

INSERT INTO role (name) VALUES
  ('USER'),
  ('ADMIN');

INSERT INTO account (email, phone, password, username, role_id) VALUES
  ('admin@mail.com', '+375(29)224-32-65', '$2a$10$kHc.I/7A4cNzQ6OylBdZkuE7B0hiD3MbXgiTjxidCdCQe6tN0ulnC', 'admin',
   (SELECT role.id
    FROM role
    WHERE role.name = 'ADMIN')),
  ('user@mail.com', '+375(29)114-52-95', '$2a$10$SNDYj/qtwGsfOEq298b79.70I1FxWUqzsnYjNtRvhPdfgy5EKGBOq', 'user',
   (SELECT role.id
    FROM role
    WHERE role.name = 'USER'));

INSERT INTO genre (name) VALUES
  ('Наука'),
  ('Учеба'),
  ('Комедия'),
  ('Драмма'),
  ('Фантастика');

INSERT INTO author (name) VALUES
  ('Антон Павлович Чехов'),
  ('Р. Лафоре'),
  ('Джордж Р.Р.Мартин'),
  ('Тюрин Ю.И.'),
  ('Брюс Эккель'),
  ('Уильям Шекспир');

INSERT INTO book (description, image, name, number_copies, author_id, genre_id) VALUES
  (' роман в жанре темное фэнтези американского писателя Джорджа Р. Р. Мартина, ' ||
   'первая книга из серии «Песнь Льда и Огня». Впервые произведение было опубликовано ' ||
   'в 1996 году издательством Bantam Spectra. Действие романа происходит в вымышленной ' ||
   'вселенной. В центре произведения три основные сюжетные линии — события, предшествующие' ||
   ' началу династических войн за власть над континентом Вестерос, напоминающим Европу времён ' ||
   'Высокого Средневековья; надвигающаяся угроза наступления племён одичалых и демонической ' ||
   'расы Иных; а также путешествие дочери свергнутого короля в попытках вернуть Железный трон. ' ||
   'Повествование ведётся от третьего лица, попеременно с точки зрения разных персонажей. ',
   '../../resource/images/Игра престолов.jpg', 'Игра престолов', 10, (SELECT author.id
                                                                      FROM author
                                                                      WHERE author.name = 'Джордж Р.Р.Мартин'),
   (SELECT genre.id
    FROM genre
    WHERE genre.name = 'Фантастика')),
  ('Предлагаем вашему вниманию книгу из серии «Библиотека Златоуста». Серия включает адаптированные ' ||
   'тексты для 5 уровней владения русским языком: произведения классиков русской литературы, современных ' ||
   'писателей, публицистов, журналистов, а также киносценарии. Уровни ориентируются на лексические минимумы, ' ||
   'разработанные для Российской государственной системы тестирования по русскому языку. Каждый выпуск снабжён' ||
   ' вопросами, заданиями и словарём, в который вошли слова, выходящие за пределы минимума.',
   '../../resource/images/Драмма на охоте.jpg', 'Драмма на охоте', 10, (SELECT author.id
                                                                        FROM author
                                                                        WHERE author.name = 'Антон Павлович Чехов'),
   (SELECT genre.id
    FROM genre
    WHERE genre.name = 'Драмма')),
  ('Впервые читатель может познакомиться с полной версией этого классического труда, который ранее на русском языке ' ||
   'печатался в сокращении. Книга, выдержавшая в оригинале не одно переиздание, за глубокое и поистине философское ' ||
   'изложение тонкостей языка Java считается одним из лучших пособий для программистов. Чтобы по-настоящему понять язык Java, '
   ||
   'необходимо рассматривать его не просто как набор неких команд и операторов, а понять его «философию», подход к решению задач,'
   ||
   ' в сравнении с таковыми в других языках программирования. На этих страницах автор рассказывает об основных проблемах написания кода:'
   ||
   ' в чем их природа и какой подход использует Java в их разрешении. Поэтому обсуждаемые в каждой главе черты языка неразрывно связаны с тем,'
   ||
   ' как они используются для решения определенных задач.', '../../resource/images/Философия java.jpg',
   'Философия java', 10, (SELECT author.id
                          FROM author
                          WHERE author.name = 'Брюс Эккель'), (SELECT genre.id
                                                               FROM genre
                                                               WHERE genre.name = 'Учеба')),
  ('Если вы хотите писать качественные приложения, с хорошей архитектурой и переиспользуемыми компонентами, ' ||
   'вам необходимо изучить принципы объектно-ориентированного программирования, и язык С++ для этого подходит как нельзя лучше.'
   ||
   ' В настоящем руководстве автор познакомит вас с основами ООП и на многочисленных примерах продемонстрирует его пользу для разработки.',
   '../../resource/images/С++.jpg', 'Объектно-ориентированное программирование на C++', 10, (SELECT author.id
                                                                                             FROM author
                                                                                             WHERE author.name =
                                                                                                   'Р. Лафоре'),
   (SELECT genre.id
    FROM genre
    WHERE genre.name = 'Учеба')),
  ('Созданные три с лишним века назад трагедии, исторические хроники и комедии Шекспира живут до сих пор, волнуют' ||
   ' и потрясают воображение зрителей. Лучшие театры мира и выдающиеся актеры поныне считают для себя экзаменом\' ||
   ' и счастьем поставить и сыграть шекспировский спектакль. Шекспир - автор 37 пьес, 2 поэм , а также 154 сонетов, ' ||
   'отличающихся горячим чувством, насыщенных мыслью. ', '../../resource/images/Комедии.jpg', 'Комедии', 10,
   (SELECT author.id
    FROM author
    WHERE author.name =
          'Уильям Шекспир'),
   (SELECT genre.id
    FROM genre
    WHERE genre.name = 'Комедия'));

INSERT INTO mark_book (value, book_id) VALUES
  (4, (SELECT book.id
       FROM book
       WHERE book.name = 'Игра престолов')),
  (5, (SELECT book.id
       FROM book
       WHERE book.name = 'Игра престолов')),
  (3, (SELECT book.id
       FROM book
       WHERE book.name = 'Драмма на охоте')),
  (5, (SELECT book.id
       FROM book
       WHERE book.name = 'Философия java')),
  (4, (SELECT book.id
       FROM book
       WHERE book.name = 'Философия java')),
  (2, (SELECT book.id
       FROM book
       WHERE book.name = 'Объектно-ориентированное программирование на C++')),
  (5, (SELECT book.id
       FROM book
       WHERE book.name = 'Комедии'));

INSERT INTO comment (comment, account_id, book_id) VALUES
  ('my comment', (SELECT account.id
                  FROM account
                  WHERE account.username = 'admin'),
   (SELECT book.id
    FROM book
    WHERE book.name = 'Игра престолов')),
  ('my comment', (SELECT account.id
                  FROM account
                  WHERE account.username = 'user'),
   (SELECT book.id
    FROM book
    WHERE book.name = 'Игра престолов'));

INSERT INTO account_book (account_id, date_issue, date_return, book_id) VALUES
  ((SELECT account.id
    FROM account
    WHERE account.username = 'admin'), '17-05-2019', '27-05-2019', (SELECT book.id
                                                                    FROM book
                                                                    WHERE book.name = 'Игра престолов')),
  ((SELECT account.id
    FROM account
    WHERE account.username = 'user'), '17-05-2019', '27-05-2019', (SELECT book.id
                                                                   FROM book
                                                                   WHERE book.name = 'Философия java'));