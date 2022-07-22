insert into public."users"(id, login, password)
values (2, 'userTest', '$2a$10$N28B/6xSa1rVsH0p6l4Q8erkTN.IF0bLLlYoPYcKz36bZzhuo3Ywu');

insert into public.users_roles(user_id, role_id)
values (2, 1),
       (2, 2);