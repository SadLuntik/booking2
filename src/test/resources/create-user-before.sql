delete
from users_roles;
delete
from users;
delete
from role;

insert into "users"(id, login, password)
values (1, 'adminTest', '$2a$10$N28B/6xSa1rVsH0p6l4Q8erkTN.IF0bLLlYoPYcKz36bZzhuo3Ywu'),
       (2, 'userTest', '$2a$10$N28B/6xSa1rVsH0p6l4Q8erkTN.IF0bLLlYoPYcKz36bZzhuo3Ywu');

insert into role(id, name)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

insert into users_roles(user_id, role_id)
values (1, 1),
       (2, 2);

